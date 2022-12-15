#ifndef REGEX_UTILS_H
#define REGEX_UTILS_H

typedef enum regex_type {
    ROUND_BRACKETS,
    SQUARE_BRACKETS,
    POINT,
    LETTER
} Regex_type;

typedef struct round_brackets {
    char* str1;
    char* str2;
} Round_brackets;

typedef struct square_Brackets {
    char first_letter;
    char last_letter;
} Square_brackets;

typedef union data {
    Square_brackets square_brackets;
    Round_brackets round_brackets;
    char letter;
} Data;

typedef struct regex_node 
{
    Regex_type type;
    Data data;
    struct regex_node *next;
} Regex_node;

Regex_node* expression_to_regex_linked_list(char* expression);
void free_regex(Regex_node* head);
void print_regex(Regex_node* head);
#endif