using Structures;

namespace Events {
    public class Ready(DiscordClient discord) {
        private readonly DiscordClient discord = discord;

        #pragma warning disable CS1998
        public async Task OnReady() {
            Console.WriteLine($"Logged in as {discord.CurrentUser.Username}!");
        }
    }
}