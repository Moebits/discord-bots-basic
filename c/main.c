#include <stdio.h>
#include <stdlib.h>
#include <dotenv.h>
#include "discord.h"

void on_ready(struct discord *client, const struct discord_ready *event) {
    printf("Logged in as %s!", event->user->username);
}

int main(int argc, char *argv[]) {
    env_load("../.env", true);
    const char* token = getenv("TOKEN");

    struct discord *client = discord_init(token);
    discord_add_intents(client, DISCORD_GATEWAY_MESSAGE_CONTENT);

    discord_set_on_ready(client, &on_ready);

    discord_run(client);
}
