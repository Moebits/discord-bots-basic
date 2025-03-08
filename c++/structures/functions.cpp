#include "functions.h"

std::string functions::trim(const std::string& s) {
    size_t start = s.find_first_not_of(" \t"), end = s.find_last_not_of(" \t");
    return (start == std::string::npos) ? "" : s.substr(start, end - start + 1);
}