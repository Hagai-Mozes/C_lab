#! /usr/bin/env tcsh

./my_grep -x -E '(ab|a).(d|cd)[x-z]' ./grep_tests/in18 | diff ./grep_tests/out18 -
valgrind --quiet --leak-check=yes ./my_grep -x -E '(ab|a).(d|cd)[x-z]' ./grep_tests/in18 | diff ./grep_tests/out18 -
