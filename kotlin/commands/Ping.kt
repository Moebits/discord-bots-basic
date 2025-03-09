package commands

import dev.kord.core.behavior.reply
import dev.kord.core.entity.Message
import structures.Discord
import structures.Command

class Ping(discord: Discord, message: Message) : Command(discord, message) {
    override suspend fun run(args: List<String>) {
        message.reply {
            content = "Pong!"
        }
    }
}