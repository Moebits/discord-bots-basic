#include <stdio.h>
#include <stdlib.h>
#include <dotenv.h>
#include <discord.h>
#include "events/on_ready.h"
#include "events/on_message.h"

int main(int argc, char *argv[]) {
    env_load("../.env", true);
    const char *token = getenv("TOKEN");

    struct discord *client = discord_init(token);
    discord_add_intents(client, DISCORD_GATEWAY_MESSAGE_CONTENT);

    load_commands();

    discord_set_on_ready(client, &on_ready);
    discord_set_on_message_create(client, &on_message);

    discord_run(client);
}