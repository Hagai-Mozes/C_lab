#! /usr/bin/env tcsh

./my_grep -b -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out7 -

valgrind --quiet --leak-check=yes ./my_grep -b -i -E 'o\.pdf' ./grep_tests/2013.html | diff ./grep_tests/out7 -
