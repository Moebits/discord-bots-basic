#pragma once
#include "structures/command.h"
#include <dpp/dpp.h>

class Ping : public Command {
public:
    void run(const dpp::message_create_t& event, const std::vector<std::string>& args) override;
};