import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.io.File
import structures.Discord
import kotlin.io.path.nameWithoutExtension

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val token = Dotenv.configure().load()["TOKEN"]

        val jda = JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .build()

        val discord = Discord(jda)
        loadCommands(discord)
        jda.addEventListener(*loadEvents(discord).toTypedArray())

        jda.awaitReady()
    }

    fun loadCommands(discord: Discord) {
        val commandFolder = File("commands")
        val commandFiles = commandFolder.listFiles{_, name -> name.endsWith(".kt")} ?: return

        for (file in commandFiles) {
            try {
                val className = "commands.${file.nameWithoutExtension}"
                val cls = Class.forName(className)

                val commandName = file.nameWithoutExtension.lowercase()
                discord.commands[commandName] = cls
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadEvents(discord: Discord): List<ListenerAdapter> {
        val eventFolder = File("events")
        val eventFiles = eventFolder.listFiles{_, name -> name.endsWith(".kt")} ?: return emptyList()
    
        return eventFiles.mapNotNull{file ->
            val className = "events.${file.nameWithoutExtension}"
            runCatching {
                val cls = Class.forName(className)
                if (ListenerAdapter::class.java.isAssignableFrom(cls)) {
                    cls.getConstructor(Discord::class.java).newInstance(discord) as ListenerAdapter
                } else null
            }.getOrElse { 
                it.printStackTrace()
                null 
            }
        }
    }    
}