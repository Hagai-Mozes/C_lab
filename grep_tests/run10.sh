#! /usr/bin/env tcsh

./my_grep -x bla ./grep_tests/in10 | diff ./grep_tests/out10 -

valgrind --quiet --leak-check=yes ./my_grep -x bla ./grep_tests/in10 | diff ./grep_tests/out10 -
