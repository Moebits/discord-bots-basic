#include "ping.h"
using std::string;
using std::vector;

void Ping::run(const dpp::message_create_t& event, const vector<string>& args) {
    event.reply("Pong!", true);
}