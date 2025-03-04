#include "message_create.h"

std::unordered_map<std::string, Command*> commands;

void message_create::load_commands() {
    commands["ping"] = new Ping();
}
	 
void message_create::on_message_create(const dpp::message_create_t &event) {
    std::string prefix = "!";
    std::string content = functions::trim(event.msg.content);

    if (content.rfind(prefix, 0) != 0 || content == prefix) return;
    
    std::istringstream iss(content.substr(prefix.length()));
    std::vector<std::string> args;
    for (std::string arg; iss >> arg;) args.push_back(arg);

    std::string command_name = args[0];

    if (commands.find(command_name) != commands.end()) {
        commands[command_name]->run(event, args);
    }
}