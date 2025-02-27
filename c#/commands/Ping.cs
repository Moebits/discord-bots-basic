using Discord;
using Discord.WebSocket;
using Structures;

namespace Commands {
    public class Ping(DiscordClient discord, SocketUserMessage message) : ICommand {
        private readonly DiscordClient discord = discord;
        private readonly SocketUserMessage message = message;

        public async Task Run(string[] args) {
            await message.ReplyAsync("Pong");
        }
    }
}