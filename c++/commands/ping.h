#pragma once
#include "structures/command.h"
#include <dpp/dpp.h>
using std::string;
using std::vector;

class Ping : public Command {
public:
    void run(const dpp::message_create_t& event, const vector<string>& args) override;
};