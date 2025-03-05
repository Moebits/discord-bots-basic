#pragma once
#include <dpp/dpp.h>
#include <string>
#include <sstream>
#include <vector>
#include <unordered_map>
#include <typeindex>
#include "structures/functions.h"
#include "structures/command.h"
#include "commands/ping.h"
 
class message_create {
public:
    static void load_commands();
    static void on_message_create(const dpp::message_create_t& event);
};