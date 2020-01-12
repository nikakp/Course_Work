// Nika Prairie
// ID: 260843183
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#include "debug.h"
#include "linked.h"
#include "ssv.h"

// Test code to assist in debugging my routines
// Not run as part of the main code
void test(void) {
    debug_print("----- Starting Test of findUpdate\n");
    findUpdate(1, 10);
    findUpdate(1,20.0);
    findUpdate(1,-20.50);
    findUpdate(3,-10);
    findUpdate(5,100000);
    findUpdate(12345,100000);
    debug_print("----- End Test of findUpdate\n");
    prettyPrint();
    
    
}

int has_one_nonspace(const char *line) {
   int ret_val = 0;
   if (NULL == line) {
      return 0;
   }
   while ( (*line) && (isspace(*line) ) ) {
      line++;
   }
   if (*line) {
      return(!isspace(*line));
   }
   return 0;
}

int main(int argc, char *argv[]) {
    // Turn the test code on or off
    const int doTest = 0;
    if (doTest) {
        test();
    } else {
        const char *file_name = "./students.ssv";
        int account;
        float amount;
        // Open the file for reading
        FILE *fp = fopen(file_name, "r");
        // Exit if we have not been able to open the file
        // output an error message
        if (NULL == fp) {
            fprintf(stderr, "Unable to open the file [%s]",
                    file_name);
            exit(1);
        }
        
        // getline allocates space to store an entire
        // line, and if needed re-allocates more space
        char *line = NULL;
        // Need to let getline know that we don't have
        // allocated buffer
        size_t line_buffer_size =  0;
        while(getline(&line, &line_buffer_size, fp) != -1) {
            debug_print("Line: [%s]", line);
            // parse the line and store the
            // data in account and amount
            // skip blank lines
            if (has_one_nonspace(line)) {
               parse(line, &account, &amount);
            // Update the linked list of accounts
               findUpdate(account, amount);
            } else {
               debug_print("Skipping line [%s]\n",line);
            }
        }
        // release the memory that was being used by getline
        free(line);
        // we are done with the file, close it
        fclose(fp);
        // printout the linked list
        prettyPrint();
    }
    return 0;
}

