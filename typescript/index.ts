import "dotenv/config"
import {fileURLToPath} from "url"
import {dirname} from "path"
import {GatewayIntentBits} from "discord.js"
import {Discord} from "./structures/Discord.ts"
import fs from "fs"
import path from "path"
const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)

const discord = new Discord({intents: [
    GatewayIntentBits.Guilds,
    GatewayIntentBits.GuildMessages,
    GatewayIntentBits.MessageContent
]})

const commands = fs.readdirSync(path.join(__dirname, "commands"))
for (const command of commands) {
    const commandName = command.replace(".ts", "").replace(".js", "")
    const Command = await import(path.join(__dirname, "commands", command)).then((r) => r.default)
    discord.commands.set(commandName, Command)
}

const events = fs.readdirSync(path.join(__dirname, "events"))
for (const event of events) {
    const eventName = event.replace(".ts", "").replace(".js", "")
    const Event = await import(path.join(__dirname, "events", event)).then((r) => r.default)
    const evt = new Event(discord)
    discord.on(eventName, (...args: any) => evt.run(...args))
}

await discord.login(process.env.TOKEN)