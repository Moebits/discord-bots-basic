#include "functions.h"
using std::string;

string functions::trim(const string& s) {
    size_t start = s.find_first_not_of(" \t"), end = s.find_last_not_of(" \t");
    return (start == string::npos) ? "" : s.substr(start, end - start + 1);
}