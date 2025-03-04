#include "command.h"

Command::~Command() = default;
void Command::run(const dpp::message_create_t &event, const std::vector<std::string>& args) {}