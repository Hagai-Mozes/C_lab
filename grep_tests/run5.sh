#! /usr/bin/env tcsh

./my_grep -n -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out5 -

valgrind --quiet --leak-check=yes ./my_grep -n -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out5 -
