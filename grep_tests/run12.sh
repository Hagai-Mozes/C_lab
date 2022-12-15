#! /usr/bin/env tcsh

./my_grep -E '(bla|li)' ./grep_tests/3lines | diff ./grep_tests/out11 -

valgrind --quiet --leak-check=yes ./my_grep -E '(bla|li)' ./grep_tests/3lines | diff ./grep_tests/out11 -
