using Discord.WebSocket;
using Structures;

namespace Events {
    public class MessageReceived(DiscordClient discord) {
        private readonly DiscordClient discord = discord;

        public async Task OnMessageReceived(SocketMessage message) {
            var prefix = "!";
            if (!message.Content.Trim().StartsWith(prefix)) return;
            if (message.Content.Trim() == prefix) return;

            var args = message.Content.Trim()[prefix.Length..].Split([" "], StringSplitOptions.RemoveEmptyEntries);

            var commandName = args[0].ToLower();

            if (discord.commands.TryGetValue(commandName, out Type? commandType)) {
                if (Activator.CreateInstance(commandType, discord, message) is ICommand commandInstance) {
                    await commandInstance.Run(args);
                }
            }
        }
    }
}