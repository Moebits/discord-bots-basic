package structures

import structures.Discord

interface Event {
    suspend fun handle(discord: Discord)
}