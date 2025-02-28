import Foundation
import SwiftDotenv
import DiscordBM

@main
struct Main {
    static func main() async throws {
        try Dotenv.configure()

        let token = ProcessInfo.processInfo.environment["TOKEN"]

        let discord = await BotGatewayManager(
            token: token!,
            intents: Gateway.Intent.allCases
        )

        await CommandHandler.handle()

        await withTaskGroup(of: Void.self) { taskGroup in
            taskGroup.addTask {
                await discord.connect()
            }

            taskGroup.addTask {
                for await event in await discord.events {
                    await EventHandler.handle(event: event, discord: discord.client)
                }
            }
        }
    }
}

struct CommandHandler {
    static func handle() async {
        await commands.register(Ping.self, for: "ping")
    }
}

struct EventHandler {
    static func handle(event: Gateway.Event, discord: any DiscordClient) async {
        do {
            switch event.data {
            case .ready(let payload):
                try await ReadyEvent.handle(payload: payload, discord: discord)
            case .messageCreate(let payload):
                try await MessageCreateEvent.handle(payload: payload, discord: discord)
            default:
                break
            }
        } catch {
            print("Error handling event: \(error)")
        }
    }
}