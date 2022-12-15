#ifndef grep_search_H
#define grep_search_H

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "regex_utils.h"

// switches names are from "grep --help" command
typedef struct command_switches
{
    FILE* input;
    Regex_node* expression;
    int after_context;
    bool regular_expression;
    bool byte_offset;
    bool count;
    bool ignore_case;
    bool line_number;
    bool invert_match;
    bool line_regexp;
} Command_switches;

bool is_match_in_line(Regex_node*, char*, Command_switches);

#endif