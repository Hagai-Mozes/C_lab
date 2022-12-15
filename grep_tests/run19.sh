#! /usr/bin/env tcsh

./my_grep -i -E '(Ab|a).(d|cD)[X-Z]' ./grep_tests/in19 | diff ./grep_tests/out19 -
valgrind --quiet --leak-check=yes ./my_grep -i -E '(Ab|a).(d|cD)[X-Z]' ./grep_tests/in19 | diff ./grep_tests/out19 -
