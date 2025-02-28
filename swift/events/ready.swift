import DiscordBM

struct ReadyEvent {
    static func handle(payload: Gateway.Ready, discord: any DiscordClient) async throws {
        let rawUser = try await discord.getOwnUser()
        let user = try rawUser.decode()
        print("Logging in as \(user.username)")
    }
}