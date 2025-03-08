#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "on_message.h"
#include "../structures/functions.h"
#include "../commands/ping.h"

#define INITIAL_CAPACITY 10

struct Command *commands = NULL;
int cmd_capacity = INITIAL_CAPACITY;
int cmd_count = 0;

void add_command(const char *name, cmd_func func) {
    if (commands == NULL) {
        commands = malloc(cmd_capacity * sizeof(struct Command));
    }
    if (cmd_count >= cmd_capacity) {
        cmd_capacity *= 2;
        commands = realloc(commands, cmd_capacity * sizeof(struct Command));
    }
    commands[cmd_count].name = strdup(name);
    commands[cmd_count].func = func;
    cmd_count++;
}

void load_commands() {
    add_command("ping", ping_command_run);
}

void on_message(struct discord *client, const struct discord_message *event) {
    const char *prefix = "!";
    size_t prefix_len = strlen(prefix);

    char *content = trim(event->content);
    if (!content) return;

    if (strncmp(content, prefix, prefix_len) != 0) {
        free(content);
        return;
    }

    if (strcmp(content, prefix) == 0) {
        free(content);
        return;
    }

    char **args = malloc(INITIAL_CAPACITY * sizeof(char*));
    int capacity = INITIAL_CAPACITY;
    int argc = 0;

    char *arg = strtok(content + prefix_len, " ");
    while (arg) {
        if (argc >= capacity) {
            capacity *= 2;
            args = realloc(args, capacity * sizeof(char*));
        }
        args[argc++] = arg;
        arg = strtok(NULL, " ");
    }

    if (argc > 0) {
        for (int i = 0; i < cmd_count; i++) {
            if (strcmp(commands[i].name, args[0]) == 0) {
                commands[i].func(client, event, args, argc);
                break;
            }
        }
    }

    free(content);
    free(args);
}