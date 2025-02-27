using Discord.WebSocket;
using Structures;

namespace Commands {
    public class Ping : ICommand {
        private DiscordClient discord;
        private SocketMessage message;

        public Ping(DiscordClient discord, SocketMessage message) {
            this.discord = discord;
            this.message = message;
        }

        public async Task Run(string[] args) {
            await message.Channel.SendMessageAsync("Pong");
        }
    }
}