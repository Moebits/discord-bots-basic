package structures

import dev.kord.core.Kord
import dev.kord.core.entity.Message

class Discord(val kord: Kord) {
    val commands = hashMapOf<String, Class<*>>()
}