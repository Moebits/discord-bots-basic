import DiscordBM

protocol Command {
    init(discord: any DiscordClient, message: Gateway.MessageCreate)
    func run(args: [String]) async throws
}

actor CommandsActor {
    private var commands: [String: Command.Type] = [:]
    
    func register(_ command: Command.Type, for name: String) {
        commands[name.lowercased()] = command
    }
    
    func get(for name: String) -> Command.Type? {
        return commands[name.lowercased()]
    }
}

let commands = CommandsActor()
