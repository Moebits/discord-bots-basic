from dotenv import load_dotenv
from typing import Dict, Any
import discord
import importlib
import os

load_dotenv()
discord.utils.setup_logging()
intents = discord.Intents.default()
intents.message_content = True

client = discord.Client(intents=intents)
client.commands: Dict[str, Any] = {}

for filename in os.listdir("commands"):
    if filename.endswith(".py"):
        module_name = f"commands.{filename[:-3]}"
        module = importlib.import_module(module_name)
        client.commands[filename[:-3]] = module

for filename in os.listdir("events"):
    if filename.endswith(".py"):
        module_name = f"events.{filename[:-3]}"
        module = importlib.import_module(module_name)
        if hasattr(module, "attach"):
            module.attach(client)

client.run(os.getenv("TOKEN"))