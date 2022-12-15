#! /usr/bin/env tcsh

cat ./grep_tests/in14 | ./my_grep -A 2 my_grep | diff ./grep_tests/out14 -

cat ./grep_tests/in14 | valgrind --quiet --leak-check=yes ./my_grep -A 2 my_grep | diff ./grep_tests/out14 -
