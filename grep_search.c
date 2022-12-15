#include <ctype.h>
#include "grep_search.h"

typedef struct compare_result{
    int str1_matching_length;
    int str2_matching_length;
} Compare_result;

bool compare_characters(char character_1, char character_2, bool ignore_case){
    if (ignore_case){
        return (tolower(character_1) == tolower(character_2));
    }
    else{
        return (character_1 == character_2);
    }
}

bool is_character_in_range(char character, char first_letter, char last_letter, bool ignore_case){
    if (ignore_case){
        character=tolower(character);
        first_letter=tolower(first_letter);
        last_letter=tolower(last_letter);
    }
    if ((first_letter <= character) &&
            (character <= last_letter)){
            return true;
        }
    return false;
}

int get_string_length(char* str){
    int string_length;
    string_length = strlen(str);
    if(string_length==0){
        return 0;
    }
    if (str[string_length-1] == '\n'){
        string_length --;
    }
    return string_length;
}

int length_of_matching_strings(char* str1, char* str2, int str2_index, bool ignore_case){
    int i;
    for (i=0; i<get_string_length(str1); i++){
        if (!compare_characters(str1[i], str2[str2_index+i], ignore_case)){
            return -1;
        }
    return get_string_length(str1);
    }
}

Compare_result compare_substring_to_regex_node(Regex_node *regex_node, char *str, int str_index, Command_switches switches){
    Compare_result compare_result;
    compare_result.str1_matching_length = -1;
    compare_result.str2_matching_length = -1;
    if (regex_node->type==LETTER){
        if (compare_characters(str[str_index],
            regex_node->data.letter, switches.ignore_case)){
                compare_result.str1_matching_length = 1;                
            }
    }
    else if (regex_node->type == SQUARE_BRACKETS){
        if(is_character_in_range(str[str_index],
            regex_node->data.square_brackets.first_letter, regex_node->data.square_brackets.last_letter, switches.ignore_case)){
                compare_result.str1_matching_length = 1;            
            }
    }
    else if (regex_node->type == POINT){
        compare_result.str1_matching_length = 1;        
    }
    else if (regex_node->type == ROUND_BRACKETS){
        compare_result.str1_matching_length = length_of_matching_strings(regex_node->data.round_brackets.str1, str, str_index, switches.ignore_case);
        compare_result.str2_matching_length = length_of_matching_strings(regex_node->data.round_brackets.str2, str, str_index, switches.ignore_case);
    }
    return compare_result;
}

bool is_line_match_regex(Regex_node *regex_head, Regex_node *regex_node, char *str, int str_index, Command_switches switches){
    Compare_result compare_substring_result;
    bool recursive_result;
    int str_length=get_string_length(str);
    if(regex_node==NULL){
        if(switches.line_regexp && str_index != str_length){
            return false;
        }
        return true;
    }
    else if (str_index == str_length){
        return false;
    }
    compare_substring_result = compare_substring_to_regex_node(regex_node, str, str_index, switches);
    recursive_result = false;
    if(compare_substring_result.str1_matching_length >= 0){
        recursive_result |= is_line_match_regex(regex_head, regex_node->next, str,
            str_index+compare_substring_result.str1_matching_length, switches);
    }
    if(compare_substring_result.str2_matching_length >= 0){
        recursive_result |= is_line_match_regex(regex_head, regex_node->next, str,
            str_index+compare_substring_result.str2_matching_length, switches);
    }
    if(switches.line_regexp==false){
        recursive_result |= is_line_match_regex(regex_head, regex_head, str, str_index+1, switches);
    }
    return recursive_result;
}

bool is_match_in_line(Regex_node *regex_list_head, char *line, Command_switches switches){
    int expression_index=0;
    int line_length=0, last_comparable_line_index=0;
    line_length=get_string_length(line);
    if (is_line_match_regex(regex_list_head, regex_list_head, line, 0, switches)){
        return !switches.invert_match;
    }
    return switches.invert_match;
}