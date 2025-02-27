using Discord.WebSocket;

namespace Structures {
    public class DiscordClient : DiscordSocketClient {
        public Dictionary<string, Type> commands = new();

        public DiscordClient(DiscordSocketConfig config) : base(config) {}
    }
}