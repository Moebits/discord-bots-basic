package commands

import net.dv8tion.jda.api.entities.Message;
import structures.Discord
import structures.Command

class Ping(discord: Discord, message: Message) : Command(discord, message) {
    override fun run(args: Array<String>) {
        message.reply("Pong").queue()
    }
}