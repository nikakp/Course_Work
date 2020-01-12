
//  answer.c
//  Ass7
//
// Nika Prairie
// ID: 260843183
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEBUG 0
#define debug_print(fmt, ...)                        \
do { if (DEBUG) fprintf(fp, "addgold[%d]:"fmt, __LINE__, ##__VA_ARGS__); } while (0)

FILE *fp = NULL;

void init_debug() {
    fp =fopen("./debug.log", "a");
    if (NULL == fp) {
        fprintf(stderr, "Unable to init debugging!!!\n");
        exit(1);
    }
}

char *get_var(const char *query_string,
              const char *var_name) {
    
    // Allocate enough memory to store the
    // variable's name along with the = symbol plus the
    // nul character
    fprintf(stderr, "%d\n", __LINE__);
    char *var_and_equal = malloc(strlen(var_name) + 2);
    // If we don't have enough memory return NULL
    if (NULL == var_and_equal) {
        fprintf(stderr, "Insufficient memory to search for variable name");
        return NULL;
    }
    fprintf(stderr, "%d\n", __LINE__);
    snprintf(var_and_equal,
             strlen(var_name) + 2,
             "%s=", // ex: gold=
             var_name);
    debug_print("var_and_equal: [%s]\n",
                var_and_equal);
    fprintf(stderr, "%d\n", __LINE__);
    char *start = strstr(query_string,var_and_equal);
    fprintf(stderr, "%d\n", __LINE__);
    if (NULL == start) {
        debug_print("Did not find [%s] in [%s]\n",
                    var_name,
                    query_string);
        return NULL;
    }
    fprintf(stderr, "%d\n", __LINE__);
    // We no longre need the string consisting of
    // <var>= so let's release the memory
    free(var_and_equal);
    debug_print("start: [%s]\n", start);
    fprintf(stderr, "%d\n", __LINE__);
    // okay, so we found out where <var>= starts, now let's
    // find the position of the ==
    char *equal = strstr(start, "=");
    // Okay, we have where the equal is
    // we are interested in the characters that
    // come after. So we make a copy of the characters
    // that follow after so that we can manipulate it
    // and place a NIL character where the field ends
    char *val_start = strdup(equal+1);
    fprintf(stderr, "%d\n", __LINE__);
    if (NULL == val_start) {
        fprintf(stderr, "Insufficient memory for dup string!");
        return NULL;
    }
    fprintf(stderr, "%d\n", __LINE__);
    // QUERY_STRING="inputstr=WEST&gold=10"
    // QUERY_STRING="gold=10&inputstr=WEST"
    fprintf(stderr, "%d: val_start:[%s]\n", __LINE__, val_start);
    char *val_end = val_start;
    fprintf(stderr, "%d\n", __LINE__);
    while ( ('\0' != *val_end) &&
           (*val_end != '&')) {
        val_end++;
        fprintf(stderr, "%d\n", __LINE__);
    }
    fprintf(stderr, "%d\n", __LINE__);
    *val_end = '\0';
    return val_start;
}


int main(int argc, char *argv[]) {
    const char *file_name = "/home/2019/nprair/public_html/Comp206/index.html";
    fprintf(stderr, "%d\n", __LINE__);
    init_debug();
    // Open the file for reading
    FILE *fp_idx = fopen(file_name, "r");
    fprintf(stderr, "%d\n", __LINE__);
    // Exit if we have not been able to open the file
    // output an error message
    if (NULL == fp_idx) {
        debug_print("Unable to open the file [%s]\n", file_name);
        fprintf(stderr, "Unable to open the file [%s]",
                file_name);
        return 1;
    }
    fprintf(stderr, "%d\n", __LINE__);
    // Get how much gold was passed to us
    char *query_string=getenv("QUERY_STRING");
    fprintf(stderr, "%d\n", __LINE__);
    debug_print("QUERY_STRING=[%s]\n",
                query_string);
    fprintf(stderr, "%d\n", __LINE__);
    char *gold_str = get_var(query_string,
                             "gold");
    fprintf(stderr, "%d\n", __LINE__);
    int gold_amount = 0;
    // If there is no gold string
    // we assume they have 10 gold
    if (NULL != gold_str) {
        gold_amount = atoi(gold_str);
    } else {
        gold_amount = 10;
    }
    debug_print("gold_amount:[%d]\n",
                gold_amount);
    // getline allocates space to store an entire
    // line, and if needed re-allocates more space
    char *line = NULL;
    // Need to let getline know that we don't have
    // allocated buffer
    size_t line_buffer_size =  0;
    printf("Content-Type: text/html\n\n");
    while(getline(&line, &line_buffer_size, fp_idx) != -1) {
        debug_print("Line: [%s]", line);
        printf("%s\n", line);
        if (strstr(line,"<form")) {
            debug_print("found <form\n");
            printf("<input type=\"hidden\" name=\"gold\" value=\"%d\">", gold_amount);
        }
    }
    // release the memory that was being used by getline
    free(line);
    // we are done with the file, close it
    fclose(fp);
}
