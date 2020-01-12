#Nika Prairie
# ID: 260843183
CC=gcc
#CFLAGS=-DDBG
DEPS = linked.h ssv.h debug.h
OBJ = main.o linked.o ssv.o

# How to build the object files
# from the source files and header
# files
%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

# How to build thee exectuable from
# the object files
bank: $(OBJ)
	$(CC) -o $@ $^ $(CFLAGS)
	
# The clean rule allows me to remove
# the executable and object files
clean:
	rm bank $(OBJ)

