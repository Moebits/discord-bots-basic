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

file(GLOB SRC_FILES "*.cpp" "commands/*.cpp" "events/*.cpp" "structures/*.cpp")
add_executable(${PROJECT_NAME} ${SRC_FILES})

include(FetchContent)

FetchContent_Declare(
  dpp
  GIT_REPOSITORY https://github.com/brainboxdotcc/DPP
  GIT_TAG        v10.1.0
)
FetchContent_MakeAvailable(dpp)

target_include_directories(${PROJECT_NAME} PRIVATE ${CMAKE_CURRENT_SOURCE_DIR})

target_link_libraries(${PROJECT_NAME} PRIVATE dpp)