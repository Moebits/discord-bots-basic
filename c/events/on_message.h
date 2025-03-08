#pragma once
#include <discord.h>

typedef void (*cmd_func)(struct discord *client, const struct discord_message *event, char **args, int argc);

struct Command {
    char *name;
    cmd_func func;
};

extern struct Command *commands;
extern int cmd_capacity;
extern int cmd_count;

void add_command(const char *name, cmd_func func);

void load_commands();

void on_message(struct discord *client, const struct discord_message *event);