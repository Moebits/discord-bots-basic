using Discord;
using Discord.WebSocket;
using dotenv.net;
using System.Reflection;
using Structures;

public class Program {
    private static Task Log(LogMessage msg) {
        Console.WriteLine(msg.ToString());
        return Task.CompletedTask;
    }

    public static async Task Main() {
        DotEnv.Load();

        var config = new DiscordSocketConfig {
            GatewayIntents = GatewayIntents.Guilds | GatewayIntents.GuildMessages | GatewayIntents.MessageContent
        };

        var discord = new DiscordClient(config);
        discord.Log += Log;

        var token = Environment.GetEnvironmentVariable("TOKEN");

        await discord.LoginAsync(TokenType.Bot, token);
        await discord.StartAsync();

        LoadEventHandlers(discord);
        LoadCommands(discord);

        await Task.Delay(-1);
    }

    public static void LoadEventHandlers(DiscordClient discord) {
        var eventsFolder = "events";
        if (!Directory.Exists(eventsFolder)) return;

        var eventFiles = Directory.GetFiles(eventsFolder, "*.cs");

        foreach (var file in eventFiles) {
            var filename = Path.GetFileNameWithoutExtension(file);
            var assembly = Assembly.GetExecutingAssembly();

            var fullClassName = $"Events.{filename}";

            var type = assembly.GetTypes().FirstOrDefault((t) => t.FullName == fullClassName);
            if (type == null) continue;

            var methods = type.GetMethods(BindingFlags.Public | BindingFlags.Instance).ToList();

            foreach (var method in methods) {
                var instance = Activator.CreateInstance(type, discord);
                if (instance == null) continue;

                foreach (var evt in discord.GetType().GetEvents()) {
                    if (method.Name == $"On{evt.Name}") {
                        var handlerType = evt.EventHandlerType;
                        
                        if (handlerType != null) {
                            var evtDelegate = Delegate.CreateDelegate(handlerType, instance, method);
                            evt.AddEventHandler(discord, evtDelegate);
                            Console.WriteLine($"Loaded event: {filename}");
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void LoadCommands(DiscordClient discord) {
        var commandsFolder = "commands";
        if (!Directory.Exists(commandsFolder)) return;

        var commandFiles = Directory.GetFiles(commandsFolder, "*.cs");

        foreach (var file in commandFiles) {
            var filename = Path.GetFileNameWithoutExtension(file);
            var assembly = Assembly.GetExecutingAssembly();

            var fullClassName = $"Commands.{filename}";

            var type = assembly.GetTypes().FirstOrDefault((t) => t.FullName == fullClassName);
            if (type == null) continue;

            var commandInstance = Activator.CreateInstance(type, discord, null);

            if (commandInstance != null) {
                var commandName = filename.ToLower();
                discord.commands.Add(commandName, type);
                Console.WriteLine($"Loaded command: {filename}");
            }
        }
    }
}