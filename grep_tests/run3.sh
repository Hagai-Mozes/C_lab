#! /usr/bin/env tcsh

./my_grep bla ./grep_tests/3lines | diff ./grep_tests/out3 -

valgrind --quiet --leak-check=yes ./my_grep bla ./grep_tests/3lines | diff ./grep_tests/out3 -

