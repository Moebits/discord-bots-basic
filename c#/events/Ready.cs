using Discord.WebSocket;
using Structures;

namespace Events {
    public class Ready {
        private DiscordClient discord;

        public Ready(DiscordClient discord) {
            this.discord = discord;
        }

        #pragma warning disable CS1998
        public async Task OnReady() {
            Console.WriteLine($"Logged in as {discord.CurrentUser.Username}!");
        }
    }
}