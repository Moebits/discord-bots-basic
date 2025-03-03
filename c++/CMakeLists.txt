cmake_minimum_required(VERSION 3.22)
set(CMAKE_CXX_STANDARD 23)

if (APPLE)
    set(CMAKE_OSX_ARCHITECTURES "arm64" CACHE INTERNAL "")
    set(OPENSSL_ROOT_DIR "/opt/homebrew/opt/openssl@3")
    set(OPENSSL_INCLUDE_DIR "/opt/homebrew/opt/openssl@3/include")
    set(OPENSSL_LIBRARIES "/opt/homebrew/opt/openssl@3/lib")
    set(OPENSSL_CRYPTO_LIBRARY "/opt/homebrew/opt/openssl@3/lib/libcrypto.dylib")
    set(OPENSSL_SSL_LIBRARY "/opt/homebrew/opt/openssl@3/lib/libssl.dylib")
endif()

project(DiscordBot)
set(PROJECT_NAME main)
add_executable(${PROJECT_NAME} main.cpp)

include(CPM.txt)

set(LIB_DIR ${CMAKE_CURRENT_SOURCE_DIR}/deps)

CPMAddPackage(
    NAME dpp
    GITHUB_REPOSITORY brainboxdotcc/DPP
    VERSION 10.1.0
    SOURCE_DIR ${LIB_DIR}/dpp
)

CPMAddPackage(
    NAME dotenv
    GITHUB_REPOSITORY harshfeudal/dotenv
    GIT_TAG 1.2.1
    VERSION 1.2.1
    SOURCE_DIR ${LIB_DIR}/dotenv
)

find_package(dpp CONFIG REQUIRED)

target_include_directories(${PROJECT_NAME} PRIVATE ${LIB_DIR}/dotenv/include)

target_link_libraries(${PROJECT_NAME} PRIVATE dpp)