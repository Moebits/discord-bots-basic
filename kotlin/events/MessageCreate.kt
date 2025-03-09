package events

import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.core.entity.Message
import structures.Discord
import structures.Event
import structures.Command

class MessageCreate : Event {
    override suspend fun handle(discord: Discord) {
        discord.kord.on<MessageCreateEvent> {
            val content = message.content.trim()
            val prefix = "!"
            if (!content.startsWith(prefix)) return@on
            if (content == prefix) return@on

            val args = content.substring(prefix.length).split("\\s+".toRegex())
            val commandName = args[0].lowercase()

            try {
                val cls = discord.commands[commandName]
                if (cls != null) {
                    val command = cls.getConstructor(Discord::class.java, Message::class.java).newInstance(discord, message) as Command
                    command.run(args)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            
        }
    }
}