import io.github.cdimascio.dotenv.Dotenv
import dev.kord.core.Kord
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.runBlocking
import java.io.File
import structures.Event
import structures.Command
import structures.Discord

object Main {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val token = Dotenv.configure().load()["TOKEN"]
        val kord = Kord(token)
        val discord = Discord(kord)

        loadCommands(discord)

        val eventListeners = loadEvents()
        eventListeners.forEach{it.handle(discord)}

        discord.kord.login {
            @OptIn(PrivilegedIntent::class)
            intents += Intent.MessageContent
        }
    }

    fun loadCommands(discord: Discord): HashMap<String, Class<*>> {
        val commands = hashMapOf<String, Class<*>>()
        val commandFolder = File("commands")
        val commandFiles = commandFolder.listFiles{_, name -> name.endsWith(".kt")}
        if (commandFiles == null) return commands

        for (file in commandFiles) {
            try {
                val className = "commands.${file.nameWithoutExtension}"
                val cls = Class.forName(className)
                var commandName = file.getName().replace(".kt", "").lowercase()

                discord.commands.put(commandName, cls);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return commands
    }

    fun loadEvents(): List<Event> {
        val eventListeners = mutableListOf<Event>()
        val eventFolder = File("events")
        val eventFiles = eventFolder.listFiles{_, name -> name.endsWith(".kt")}
        if (eventFiles == null) return eventListeners

        for (file in eventFiles) {
            try {
                val className = "events.${file.nameWithoutExtension}"
                val cls = Class.forName(className)
                if (Event::class.java.isAssignableFrom(cls) && !cls.isInterface) {
                    val eventListener = cls.getConstructor().newInstance() as Event
                    eventListeners.add(eventListener)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return eventListeners
    }
}