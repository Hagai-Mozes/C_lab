#include <getopt.h>
#include <sys/types.h>
#include "grep_search.h"
#include "regex_utils.h"

typedef struct printed_line
{
    char* line;
    bool is_match;
    int line_number;
    int bytes_read;
} Printed_line;

FILE* open_file(char *path_to_file){
    FILE* f=NULL;
    f = fopen(path_to_file, "r");
    if (f == NULL){
        fprintf(stderr, "-E- Failed to open file in path: %s\n", path_to_file);
    }
    return f;
}

void print_line(Command_switches switches, Printed_line line_properties){
    char separator=0;
    int line_length = strlen(line_properties.line);
    if (switches.count){
        return;
    }
    if (line_properties.is_match){
        separator = ':';
    }
    else{
        separator = '-';
    }
    if (switches.line_number){
        printf("%d%c",line_properties.line_number, separator);
    }
    if (switches.byte_offset){
        printf("%d%c",line_properties.bytes_read, separator);
    }
    printf("%s", line_properties.line);
    if (line_properties.line[line_length-1]!='\n'){
        printf("\n");
    }
}

void process_grep_command(Command_switches* switches, int number_of_arguments, char **arguments){
    int argument=0;
    while ((argument = getopt(number_of_arguments, arguments, "bcinvxA:E:")) != -1){
        switch (argument)
        {
        case 'b':
            switches->byte_offset = true;
        break;
        case 'c':
            switches->count = true;
        break;
        case 'i':
            switches->ignore_case = true;
        break;
        case 'n':
            switches->line_number = true;
        break;
        case 'v':
            switches->invert_match = true;
        break;
        case 'x':
            switches->line_regexp = true;
        break;
        case 'A':
            switches->after_context = atoi(optarg);
        break;
        case 'E':
            switches->regular_expression = true;
            switches->expression = expression_to_regex_linked_list(optarg);
        break;
        }
    }
    if (switches->regular_expression == false){
        switches->expression = expression_to_regex_linked_list(arguments[optind++]);
    }
    if (optind < number_of_arguments){
        switches->input = open_file(arguments[optind]);
    }
    else {
        switches->input = stdin;
    }
    // print_regex(switches->expression);
}

int main(int argc, char *argv[]){
    Command_switches switches = {0};
    Printed_line line_properties = {0};
    size_t buffer_size=0;
    ssize_t read_length=0;
    char *line_buffer = NULL;
    int after_context_counter=0;
    int bytes_counter=0, line_counter=1, match_counter=0;
    bool print_separator=false;
    process_grep_command(&switches, argc, argv);
    read_length = getline(&line_buffer, &buffer_size, switches.input);
    bytes_counter = read_length;
    while (read_length >= 0){
        line_properties.line = line_buffer;
        line_properties.line_number = line_counter;
        line_properties.bytes_read = bytes_counter;
        if (is_match_in_line(switches.expression, line_buffer, switches)){
            if(print_separator==true){
                printf("--\n");
                print_separator=false;
            }
            after_context_counter = switches.after_context;
            line_properties.is_match = true;
            match_counter++;
            print_line(switches, line_properties);
        }
        else if (after_context_counter > 0){
            after_context_counter--;
            line_properties.is_match = false;
            print_line(switches, line_properties);
        }
        else {
            print_separator = true & (switches.after_context > 0) & (match_counter > 0);
        }
        bytes_counter+=read_length;
        line_counter++;
        free(line_buffer);
        line_buffer=NULL;
        read_length = getline(&line_buffer, &buffer_size, switches.input);
    }
    free_regex(switches.expression);
    if (switches.count){
        printf("%d\n", match_counter);
    }
    if (line_buffer != NULL){
        free(line_buffer);
    }
    if (switches.input==NULL){
        fclose(switches.input);
    }
    return 0;
}