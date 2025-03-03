package structures

import net.dv8tion.jda.api.entities.Message

abstract class Command(val discord: Discord, val message: Message) {
    abstract fun run(args: Array<String>)
}