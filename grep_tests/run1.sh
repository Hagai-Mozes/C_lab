#! /usr/bin/env tcsh

./my_grep a ./grep_tests/emptyfile | diff ./grep_tests/emptyfile -

valgrind --quiet --leak-check=yes ./my_grep a ./grep_tests/emptyfile | diff ./grep_tests/emptyfile -

