import discord

def attach(client: discord.Client):
    return Ready(client)

class Ready:
    def __init__(self, client: discord.Client):
        self.client = client
        self.client.event(self.on_ready)

    async def on_ready(self) -> None:
        print(f"Logged in as {self.client.user.name}!")