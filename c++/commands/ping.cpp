#include "ping.h"

void Ping::run(const dpp::message_create_t& event, const std::vector<std::string>& args) {
    event.reply("Pong!", true);
}