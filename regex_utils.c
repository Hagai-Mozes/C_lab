#include <stdio.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "regex_utils.h"

Regex_node* create_regex_node(Regex_type type, Data data){
    Regex_node* new_node = (Regex_node*)malloc(sizeof(Regex_node));
    if (new_node != NULL){
        new_node->type=type;
        new_node->data=data;
        new_node->next=NULL;
    }
    return new_node;
}

Regex_node* enqueue(Regex_node* new_node, Regex_node* head){
    Regex_node* curr_node;
    if(head==NULL){
        head=new_node;
        return head;
    }
    curr_node=head;
    while(curr_node->next != NULL){
        curr_node=curr_node->next;
    }
    curr_node->next=new_node;
    return head;
}

Round_brackets split_round_brackets_str(char *expression_to_split, int start_index){
    int i=0, string_cursor=0, str1_length=0, str2_length=0;
    Round_brackets round_brackets;
    for(i=start_index; expression_to_split[i]!='|'; i++){
        str1_length++;
    }
    str1_length++;
    for(i=start_index+str1_length; expression_to_split[i]!=')'; i++){
        str2_length++;
    }
    str2_length++;
    round_brackets.str1=(char*) malloc(sizeof(char)*str1_length);
    round_brackets.str2=(char*) malloc(sizeof(char)*str2_length);
    string_cursor=0;
    for(i=start_index; expression_to_split[i] != '|'; i++){
        round_brackets.str1[string_cursor++]=expression_to_split[i];
    }
    round_brackets.str1[string_cursor]='\0';
    string_cursor=0;
    for(i=start_index+str1_length; expression_to_split[i] != ')'; i++){
        round_brackets.str2[string_cursor++]=expression_to_split[i];
    }       
    round_brackets.str2[string_cursor]='\0';
    return round_brackets;
}

Regex_node* expression_to_regex_linked_list(char* expression){
    Regex_node *head=NULL;
    int experession_length = strlen(expression), i=0;
    Data data;
    Regex_type type;
    Regex_node *new_node;
    for (i = 0; i < experession_length; i++){
        if (expression[i] == '\\'){
            i++;
            if (i < experession_length){
                type=LETTER;
                data.letter = expression[i];
            }
        }
        else if (expression[i] == '['){
            type=SQUARE_BRACKETS;
            char first_letter = expression[++i];
            i++;
            char last_letter = expression[++i];
            Square_brackets square_brackets = {first_letter, last_letter};
            data.square_brackets = square_brackets;
            i++;
        }
        else if (expression[i] == '('){
            Round_brackets round_brackets={NULL, NULL};
            round_brackets=split_round_brackets_str(expression, i+1);
            i+=strlen(round_brackets.str1)+strlen(round_brackets.str2)+2;
            data.round_brackets=round_brackets;
            type=ROUND_BRACKETS;
        }
        else if (expression[i] == '.'){
            type=POINT;
        }
        else{
            type=LETTER;
            data.letter = expression[i];
        }
        new_node=create_regex_node(type, data);
        head=enqueue(new_node, head);
    }
    return head;
}

void free_regex(Regex_node* head) {
	Regex_node* temp = head;
	while (head != NULL) {
		temp = head;
		head = head->next;
		free(temp);
	}
}

void print_regex(Regex_node* head) {
	while (head != NULL) {
		if (head->type == SQUARE_BRACKETS){
            printf("SQUARE_BRACKETS: [%c-%c]\t", head->data.square_brackets.first_letter, head->data.square_brackets.last_letter);
        }
        else if (head->type == ROUND_BRACKETS){
            printf("ROUND_BRACKETS: (%s|%s)\t", head->data.round_brackets.str1, head->data.round_brackets.str2);
        }
        else if (head->type == POINT){
            printf("POINT\t");
        }
        else{
            printf("LETTER: %c\t", head->data.letter);
        }
        head=head->next;
	}
    printf("\n");
}