#! /usr/bin/env tcsh

./my_grep -E '\[(1|)[0-9]\]' ./grep_tests/in16 | diff ./grep_tests/out16 -
valgrind --quiet --leak-check=yes ./my_grep -E '\[(1|)[0-9]\]' ./grep_tests/in16 | diff ./grep_tests/out16 -
