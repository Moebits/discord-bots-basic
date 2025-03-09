#include "message_create.h"
using std::string;
using std::unordered_map;
using std::istringstream;
using std::vector;

unordered_map<string, Command*> commands;

void message_create::load_commands() {
    commands["ping"] = new Ping();
}
	 
void message_create::on_message_create(const dpp::message_create_t& event) {
    string prefix = "!";
    string content = functions::trim(event.msg.content);

    if (content.rfind(prefix, 0) != 0 || content == prefix) return;
    
    istringstream iss(content.substr(prefix.length()));
    vector<string> args;
    for (string arg; iss >> arg;) args.push_back(arg);

    string command_name = args[0];

    if (commands.find(command_name) != commands.end()) {
        commands[command_name]->run(event, args);
    }
}