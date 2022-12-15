#! /usr/bin/env tcsh

./my_grep bla ./grep_tests/bla | diff ./grep_tests/bla -

valgrind --quiet --leak-check=yes ./my_grep bla ./grep_tests/bla | diff ./grep_tests/bla -

