#pragma once
#include <iostream>
#include <format>
#include <dpp/dpp.h>

class ready {
public:
    static void on_ready(const dpp::ready_t& event);
};