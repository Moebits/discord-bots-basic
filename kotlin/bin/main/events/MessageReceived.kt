package events

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.entities.Message;
import structures.Discord
import structures.Command

class MessageReceived(private val discord: Discord) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message
        val content = message.contentRaw.trim()
        val prefix = "!"
        
        if (!content.startsWith(prefix) || content == prefix) return

        val args = content.substring(prefix.length).split("\\s+".toRegex()).toTypedArray()
        val commandName = args[0].lowercase()

        try {
            val cls = discord.commands[commandName]
            cls?.let {
                val command = it.getConstructor(Discord::class.java, Message::class.java).newInstance(discord, message) as Command
                command.run(args)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
