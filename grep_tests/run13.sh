#! /usr/bin/env tcsh

./my_grep -E 'r(i|A).[y-z]\.' ./grep_tests/2013.html | diff ./grep_tests/out13 -

valgrind --quiet --leak-check=yes ./my_grep -E 'r(i|A).[y-z]\.' ./grep_tests/2013.html | diff ./grep_tests/out13 -
