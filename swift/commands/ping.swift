import DiscordBM

struct Ping: Command {
    let discord: any DiscordClient
    let message: Gateway.MessageCreate

    init(discord: any DiscordClient, message: Gateway.MessageCreate) {
        self.discord = discord
        self.message = message
    }

    func run(args: [String]) async throws {
        let _ = try await discord.createMessage(
            channelId: message.channel_id, 
            payload: .init(content: "Pong", message_reference: .init(message_id: message.id))
        )
    }
}