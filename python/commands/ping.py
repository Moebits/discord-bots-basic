import discord
from typing import List

def attach(client: discord.Client, message: discord.Message):
    return Ping(client, message)

class Ping:
    def __init__(self, client: discord.Client, message: discord.Message):
        self.client = client
        self.message = message

    async def run(self, args: List[str]) -> None:
        client: discord.Client = self.client
        message: discord.Message = self.message
        await message.reply("Pong!")