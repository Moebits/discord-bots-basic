import {Message} from "discord.js"
import {Discord} from "../structures/Discord.ts"

export default class PingCommand {
    private readonly discord: Discord
    private readonly message: Message

    public constructor(discord: Discord, message: Message) {
        this.discord = discord
        this.message = message
    }

    public run = async (args: string[]) => {
        const discord = this.discord
        const message = this.message

        return message.reply("Pong!")
    }
}