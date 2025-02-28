import DiscordBM

struct MessageCreateEvent {
    static func handle(payload: Gateway.MessageCreate, discord: any DiscordClient) async throws {
        let prefix = "!"
        let content = payload.content.trimmingCharacters(in: .whitespacesAndNewlines)
        if !content.starts(with: prefix) || content.count <= prefix.count { return }

        let args = content.dropFirst(prefix.count).split(separator: " ").map(String.init)

        let commandName = args[0].lowercased()

        if let Command = await commands.get(for: commandName) {
            let command = Command.init(discord: discord, message: payload)
            try await command.run(args: args)
        }
    }
}