#! /usr/bin/env tcsh

./my_grep -v -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out9 -

valgrind --quiet --leak-check=yes ./my_grep -v -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out9 -
