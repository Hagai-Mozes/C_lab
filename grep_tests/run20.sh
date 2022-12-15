#! /usr/bin/env tcsh

./my_grep -x -i -E '(Ab|a).(d|cD)[X-Z]' ./grep_tests/in20 | diff ./grep_tests/out20 -
valgrind --quiet --leak-check=yes ./my_grep -x -i -E '(Ab|a).(d|cD)[X-Z]' ./grep_tests/in20 | diff ./grep_tests/out20 -
