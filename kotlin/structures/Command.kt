package structures

import dev.kord.core.entity.Message
import structures.Discord

abstract class Command(val discord: Discord, val message: Message) {
    abstract suspend fun run(args: List<String>)
}