import discord

def attach(client: discord.Client):
    return MessageCreate(client)

class MessageCreate:
    def __init__(self, client: discord.Client):
        self.client = client
        self.client.event(self.on_message)

    async def on_message(self, message: discord.Message) -> None:
        client: discord.Client = self.client
        prefix = "!"
        if not message.content.strip().startswith(prefix): return
        if message.content.strip() == prefix: return
        args = message.content.strip()[len(prefix):].split()

        command = args[0].lower()
        if command in client.commands:
            cmd = client.commands[command].attach(client, message)
            await cmd.run(args)