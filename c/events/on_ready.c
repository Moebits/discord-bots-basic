#include <stdio.h>
#include "on_ready.h"

void on_ready(struct discord *client, const struct discord_ready *event) {
    printf("Logged in as %s!\n", event->user->username);
}