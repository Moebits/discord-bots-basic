#include <fstream>
#include <iostream>
#include <format>
#include <dpp/dpp.h>
#include "events/ready.h"
#include "events/message_create.h"
using std::string;
using std::ifstream;
using std::getline;

void loadEnv() {
    ifstream file("../.env");

    string line;
    while (getline(file, line)) {
        line.erase(0, line.find_first_not_of(" \t\r\n"));
        line.erase(line.find_last_not_of(" \t\r\n") + 1);

        if (line.empty() || line[0] == '#') continue;

        size_t pos = line.find('=');
        if (pos == string::npos) continue;

        string key = line.substr(0, pos);
        string value = line.substr(pos + 1);

        key.erase(0, key.find_first_not_of(" \t"));
        key.erase(key.find_last_not_of(" \t") + 1);
        value.erase(0, value.find_first_not_of(" \t"));
        value.erase(value.find_last_not_of(" \t") + 1);

        setenv(key.c_str(), value.c_str(), 1);
    }
    file.close();
}

int main() {
	loadEnv();
    string token = getenv("TOKEN");

    auto intents = dpp::i_default_intents | dpp::i_message_content;
    dpp::cluster discord(token, intents);

    discord.on_log(dpp::utility::cout_logger());

    message_create::load_commands();

    discord.on_message_create(&message_create::on_message_create);
    discord.on_ready(&ready::on_ready);

    discord.start(dpp::st_wait);
}