#! /usr/bin/env tcsh

./my_grep -E '(ab|a).(d|cd)[x-z]' ./grep_tests/in15 | diff ./grep_tests/out15 -
valgrind --quiet --leak-check=yes ./my_grep -E '(ab|a).(d|cd)[x-z]' ./grep_tests/in15 | diff ./grep_tests/out15 -
