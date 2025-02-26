import {Discord} from "../structures/Discord.ts"

export default class Ready {
    constructor(private readonly discord: Discord) {}

    public run = async () => {
        const discord = this.discord
        console.log(`Logged in as ${discord.user!.username}!`)
    }
}
