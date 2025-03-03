#include <dotenv/dotenv.h>
#include <iostream>
using std::string;

int main() {
	dotenv::load(".env");

    string token = std::getenv("TOKEN");

    std::cout << token << std::endl;
}