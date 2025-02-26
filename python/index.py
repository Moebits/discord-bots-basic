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

for filename in os.listdir(os.path.join(os.getcwd(), "commands")):
    if filename.endswith(".py"):
        command_name = filename[:-3]
        module_name = f"commands.{command_name}"
        module = importlib.import_module(module_name)
        client.commands[command_name] = module


for filename in os.listdir(os.path.join(os.getcwd(), "events")):
    if filename.endswith(".py"):
        event_name = filename[:-3]
        module_name = f"events.{event_name}"
        module = importlib.import_module(module_name)
        if hasattr(module, "attach"):
            module.attach(client)

client.run(os.getenv("TOKEN"))