package structures

import net.dv8tion.jda.api.JDA

class Discord(val jda: JDA) {
    val commands: MutableMap<String, Class<*>> = mutableMapOf()
}
