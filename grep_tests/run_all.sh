#! /usr/bin/env bash

./socket_ex/verify_readme.sh

make --silent clean
./grep_tests/test_permissions.sh
make --silent

./grep_tests/check_line_lengths.sh
python2.7 ./grep_tests/check_private_functions.py ./my_grep
./header_guards/check_header_guards.sh
./header_guards/check_unnecessary_includes.sh
./grep_tests/check_defines.py
./grep_tests/check_magic_constants.sh

./grep_tests/run1.sh
./grep_tests/run2.sh
./grep_tests/run3.sh
./grep_tests/run4.sh
./grep_tests/run5.sh
./grep_tests/run6.sh
./grep_tests/run7.sh
./grep_tests/run8.sh
./grep_tests/run9.sh
./grep_tests/run10.sh
./grep_tests/run11.sh
./grep_tests/run12.sh
./grep_tests/run13.sh
./grep_tests/run14.sh
./grep_tests/run15.sh
./grep_tests/run16.sh
./grep_tests/run17.sh
./grep_tests/run18.sh
./grep_tests/run19.sh
./grep_tests/run20.sh

./lint/lint.sh

cd ../beta1
./grep_tests/beta1.sh
cd ../final_version

cd ../beta2
./grep_tests/beta2.sh
cd ../final_version

cat SDP | grep -v -i SDP

cat COPYING_WILL_BE_REPORTED | grep -v -i COPYING_WILL_BE_REPORTED

cat UNIFORM_MATCHING | grep -v -i UNIFORM_MATCHING

if [ ! -f DESIGN ]; then
    echo "DESIGN File not found!"
fi

if [[ -f SVN ]]; then
    ./svn/svn_check.sh
fi
