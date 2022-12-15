#! /usr/bin/env tcsh

./my_grep -c -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out8 -

valgrind --quiet --leak-check=yes ./my_grep -c -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out8 -
