import {Client, Collection} from "discord.js"

export class Discord extends Client {
    public commands = new Collection<string, any>()
    
    constructor(options: any) {
        super(options)
    }
}