using Discord.WebSocket;
using Structures;

namespace Events {
    public class MessageReceived {
        private DiscordClient discord;

        public MessageReceived(DiscordClient discord) {
            this.discord = discord;
        }

        public async Task OnMessageReceived(SocketMessage message) {
            var prefix = "!";
            if (!message.Content.Trim().StartsWith(prefix)) return;
            if (message.Content.Trim() == prefix) return;

            var args = message.Content.Trim().Substring(prefix.Length).Split(new[] {" "}, StringSplitOptions.RemoveEmptyEntries);

            var commandName = args[0].ToLower();

            if (discord.commands.ContainsKey(commandName)) {
                var commandType = discord.commands[commandName];
                var commandInstance = Activator.CreateInstance(commandType, discord, message) as ICommand;
                if (commandInstance != null) {
                    await commandInstance.Run(args);
                }
            }
        }
    }
}