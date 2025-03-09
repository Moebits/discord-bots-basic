#pragma once
#include <vector>
#include <string>
#include <dpp/dpp.h>
using std::string;
using std::vector;

class Command {
public:
    virtual ~Command();
    virtual void run(const dpp::message_create_t& event, const vector<string>& args);
};