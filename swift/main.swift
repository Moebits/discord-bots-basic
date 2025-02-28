import Foundation
import SwiftDotenv
import DiscordBM
import AsyncHTTPClient
import NIO

@main
struct Main {
    static func main() async throws {
        try Dotenv.configure()

        let token = ProcessInfo.processInfo.environment["TOKEN"]

        let discord = await BotGatewayManager(
            token: token!,
            intents: Gateway.Intent.allCases
        )

        await withTaskGroup(of: Void.self) { taskGroup in
            taskGroup.addTask {
                await discord.connect()
            }

            taskGroup.addTask {
                for await event in await discord.events {
                    await EventHandler(event: event, discord: discord.client).handleAsync()
                }
            }
        }
    }
}

struct EventHandler: GatewayEventHandler {
    let event: Gateway.Event
    let discord: any DiscordClient

    func onReady(_ payload: Gateway.Ready) async throws {
        let rawUser = try await discord.getOwnUser()
        let user = try rawUser.decode()

        print("Logging in as \(user.username)")
    }

    func onMessageCreate(_ payload: Gateway.MessageCreate) async throws {
        print(payload)
    }
}