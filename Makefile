.PHONY: all test clean

all: main.o grep_search.o
	gcc -std=gnu99 -Wall -Wextra -o my_grep main.o grep_search.o regex_utils.o

main.o: grep_search.o grep_search.h

grep_search.o: regex_utils.o grep_search.h

regex_utils.o: regex_utils.h

test:
	@echo "TODO: Complete"

clean:
	rm my_grep *.o
