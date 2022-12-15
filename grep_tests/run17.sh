#! /usr/bin/env tcsh

./my_grep -E '\[(1|)[0-9]\\\]' ./grep_tests/in17 | diff ./grep_tests/out17 -
valgrind --quiet --leak-check=yes ./my_grep -E '\[(1|)[0-9]\\\]' ./grep_tests/in17 | diff ./grep_tests/out17 -
