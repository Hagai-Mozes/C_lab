#! /usr/bin/env tcsh

cat ./grep_tests/3lines | ./my_grep bla | diff ./grep_tests/out3 -

cat ./grep_tests/3lines | valgrind --quiet --leak-check=yes ./my_grep bla | diff ./grep_tests/out3 -
