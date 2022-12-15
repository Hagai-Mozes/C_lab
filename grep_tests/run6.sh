#! /usr/bin/env tcsh

#./my_grep -A 2 -n -i io ./grep_tests/2013.html | diff ./grep_tests/out6 -

valgrind --quiet --leak-check=yes ./my_grep -A 2 -n -i io ./grep_tests/2013.html | diff ./grep_tests/out6 -
