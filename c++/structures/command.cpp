#include "command.h"
using std::string;
using std::vector;

Command::~Command() = default;
void Command::run(const dpp::message_create_t& event, const vector<string>& args) {}