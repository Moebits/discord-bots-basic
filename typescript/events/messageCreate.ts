import {Message} from "discord.js"
import {Discord} from "../structures/Discord.ts"

export default class MessageCreate {
    private readonly discord: Discord

    public constructor(discord: Discord) {
        this.discord = discord
    }

    public run = async (message: Message) => {
        const discord = this.discord
        let prefix = "!"
        if (!message.content.trim().startsWith(prefix)) return
        if (message.content.trim() === prefix) return
        const args = message.content.trim().slice(prefix.length).split(/ +/g)

        const Command = discord.commands.get(args[0].toLowerCase())
        if (Command) {
            const cmd = new Command(discord, message)
            await cmd.run(...args)
        }
    }
}