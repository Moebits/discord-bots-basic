cmake_minimum_required(VERSION 3.22)
set(CMAKE_C_STANDARD 23)

set(CMAKE_C_COMPILER "/opt/homebrew/bin/gcc-14")

project(DiscordBot C)
set(PROJECT_NAME main)

file(GLOB SRC_FILES "*.c")
add_executable(${PROJECT_NAME} ${SRC_FILES})

include(ExternalProject)

ExternalProject_Add(
    concord
    GIT_REPOSITORY https://github.com/Cogmasters/concord
    GIT_TAG v2.3.0
    UPDATE_COMMAND git submodule update --init --recursive
    CONFIGURE_COMMAND ""
    BUILD_IN_SOURCE 1
    BUILD_COMMAND make
    INSTALL_COMMAND ""
    SOURCE_DIR ${CMAKE_BINARY_DIR}/_deps/concord-src
)

add_library(concord_lib STATIC IMPORTED)
set_target_properties(concord_lib PROPERTIES
    IMPORTED_LOCATION ${CMAKE_BINARY_DIR}/_deps/concord-src/lib/libdiscord.a
    INTERFACE_INCLUDE_DIRECTORIES ${CMAKE_BINARY_DIR}/_deps/concord-src/include
)

add_dependencies(concord_lib concord)

include(FetchContent)

FetchContent_Declare(
  dotenv
  GIT_REPOSITORY https://github.com/Isty001/dotenv-c
  GIT_TAG        master
)
FetchContent_MakeAvailable(dotenv)

find_package(CURL REQUIRED)

target_include_directories(${PROJECT_NAME} PRIVATE
    ${CMAKE_BINARY_DIR}/_deps/concord-src/include
    ${CMAKE_BINARY_DIR}/_deps/concord-src/core
    ${CMAKE_BINARY_DIR}/_deps/concord-src/gencodecs
    ${CMAKE_BINARY_DIR}/_deps/dotenv-src/src
    ${CMAKE_CURRENT_SOURCE_DIR}
)

target_link_libraries(${PROJECT_NAME} PRIVATE curl concord_lib dotenv)