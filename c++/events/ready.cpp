#include "ready.h"

void ready::on_ready(const dpp::ready_t& event) {
    std::cout << std::format("Logged in as {}!\n", event.owner->me.username);   
}