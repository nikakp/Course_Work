// Nika Prairie
// ID: 260843183
#include <stdio.h>
//#define DBG
#ifdef DBG
#define DEBUG 1
#else
#define DEBUG 0
#endif

#define debug_print(fmt, ...)                        \
do { if (DEBUG) fprintf(stderr, "dbg[%d]:"fmt, __LINE__, ##__VA_ARGS__); } while (0)
