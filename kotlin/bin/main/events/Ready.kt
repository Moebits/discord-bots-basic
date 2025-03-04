package events

import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.session.ReadyEvent
import structures.Discord

class Ready(private val discord: Discord) : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        val selfUser = event.jda.selfUser
        println("Logged in as ${selfUser.name}!")
    }
}