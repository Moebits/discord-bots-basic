#include "ping.h"

void ping_command_run(struct discord *client, const struct discord_message *event, char **args, int argc) {
    struct discord_message_reference ref = {.message_id = event->id, .channel_id = event->channel_id, .guild_id = event->guild_id};
    struct discord_create_message params = {.content = "Pong!", .message_reference = &ref};
    discord_create_message(client, event->channel_id, &params, NULL);
}