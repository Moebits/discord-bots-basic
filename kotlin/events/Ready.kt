package events

import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import structures.Event
import structures.Discord

class Ready : Event {
    override suspend fun handle(discord: Discord) {
        discord.kord.on<ReadyEvent> {
            val username = discord.kord.getSelf().username
            println("Logged in as $username!")
        }
    }
}