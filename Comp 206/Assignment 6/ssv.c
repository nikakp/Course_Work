// Nika Prairie
// ID: 260843183
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "debug.h"

void parse(char record[], int *acct, float *amnt)
{
    // scan the string passed in to find a decimal
    // and float and then store these values using
    // the pointers passed in
    if(sscanf(record, "%d %f", acct, amnt)!= 2){
	// We should be reading 2 records from the line
	// If we didn't return an account with an ID
	// of 0 and an amount of 0
	// 
        debug_print("Unable to read 2 records for line [%s]",
                     record);
        *acct = 0;
        *amnt = 0;
        return;
    }
    return;
}
