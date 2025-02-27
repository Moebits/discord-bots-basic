using Discord.WebSocket;

namespace Structures {
    public class DiscordClient(DiscordSocketConfig config) : DiscordSocketClient(config) {
        public Dictionary<string, Type> commands = [];
    }
}