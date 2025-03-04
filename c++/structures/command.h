#pragma once
#include <vector>
#include <string>
#include <dpp/dpp.h>

class Command {
public:
    virtual ~Command();
    virtual void run(const dpp::message_create_t &event, const std::vector<std::string>& args);
};