#include "ready.h"
using std::cout;
using std::format;

void ready::on_ready(const dpp::ready_t& event) {
    cout << format("Logged in as {}!\n", event.owner->me.username);   
}