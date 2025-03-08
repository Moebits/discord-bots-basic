#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "functions.h"

char* trim(char *s) {
    size_t start = 0;
    size_t end = strlen(s) - 1;

    while (isspace(s[start])) start++;
    while (end > start && isspace(s[end])) end--;

    size_t length = end >= start ? end - start + 1 : 0;
    char* result = (char*) malloc(length + 1);
    if (result) {
        strncpy(result, s + start, length);
        result[length] = '\0';
    }
    return result;
}