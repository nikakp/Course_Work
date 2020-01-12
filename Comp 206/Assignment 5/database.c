//
//  main.c
//  database
//
//  Created by Nika Prairie on 2019-11-11.
// ID: 260843183
//  Copyright © 2019 Nika Prairie. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <ctype.h>


//#define DBG
#ifdef DBG
#define DEBUG 1
#else
#define DEBUG 0
#endif

#define debug_print(fmt, ...)                        \
do { if (DEBUG) fprintf(stderr, "dbg[%d]:"fmt, __LINE__, ##__VA_ARGS__); } while (0)

// Create the right filename to use
// slightly different at home from mimi
const char *src_file_name = "./database.csv";
//const char *src_file_name = "/Users/nikakp/Assignments/database.csv";
const char *tmp_file_name ="./database.tmp";
//const char *tmp_file_name ="/Users/nikakp/Assignments/database.tmp";

// string_to_upper
// converts the input string to all upper case
void string_to_upper(char *str) {
	// loop until we reach the end
	// of the string
    while (*str != '\0') {
	    // replace the char with it
	    // capital version
        *str = toupper(*str);
        str++;
    }
    return;
}


// Read in the input file specificied by file name
// returns the number of bytes read in the len
// int passed through a pointer
// /returns a string with the contents of the file
//
char *readin_file(const char *file_name, long *len) {
    char *file_contents;
    
    FILE *fp = fopen(file_name, "r");
    if (NULL == fp) {
        printf("Unable to read file [%s]", file_name);
        exit(1);
    }
    fseek(fp, 0L, SEEK_END);
    // get the postion, which is the size of the file
    // given that we have moved to the end
    long int file_size = ftell(fp);
    // Go back to the start of the file
    rewind(fp);
    // now allocate a char array to store the contents of the file so
    // that we can retreive the requested info
    file_contents = malloc(file_size+1);
    long int num_read = fread(file_contents,sizeof(file_contents[0]), file_size, fp);
    if (num_read != file_size) {
        printf("Wow, we didn't read all the data, only %ld characters, this should not happen \n"
               "unless someone has changed the file while the program was running, exiting\n", num_read);
        fclose(fp);
        exit(1);
    }
    // Boy, really have to make sure I don't 
    // // forget to close this as it was a realy
    // // pain to debug
    fclose(fp);
    *len = file_size;
    return file_contents;
}

// takes a string and replaces all the new lines
// with null terminations for the strings
//
// it returns in num_lines the number of lines processed
// it returns a pointer to an array of strings
char **null_term_lines(char *str, int *num_lines) {
    int n =0;
    char *loc = str;
    char **line_array;
    
    while ( (loc = strchr(loc, '\n')) != NULL) {
        *loc = '\0';
        loc++;
        n++;
    }
    line_array = calloc(sizeof(char *), n);
    *num_lines = n;
    loc = str;
    for(int i = 0; i < n; i++) {
        line_array[i] = loc;
        loc += strlen(loc)+1;
    }
    for(int i=0; i< n; i++) {
        debug_print("%s\n", line_array[i]);
    }
    return line_array;
    
}

// This function receives the command line arguments
// an determines that valid commands are provided
// along with the appropriate number of parameters
// NOTE: It DOES NOT check if a valid integer or flot
// was passed in, this is done later
char *checkcommand(const int argc, const char * argv[]){
    
    //checks theres at least 1 arg, argc >= 2
    if (argc < 2) {
        puts("You did not provide any arguments. Please enter: ./database CMD OPT1 OPT2 OPT3 OPT4");
        return NULL;
    }
    debug_print("argv[1]: [%s]\n", argv[1]);
    char *command = strdup(argv[1]);
    string_to_upper(command);
    if (0 == strcmp(command, "SHOW")) {
        debug_print("SHOW command identified\n");
        return command;
    } else if (0 == strcmp(command, "DELETE")) {
        // check that 3 more arguments are available, i.e
        // argc >= 3
        if (argc < 3) {
            debug_print("Missing parameters for delete command\n");
            printf("Name of record to delete is missing\n");
            exit(1);
        }
        return command;
    } else if    (0 == strcmp(command, "ADD") ) {
        // check that 4 more arguments are available, i.e
        // argc >= 6
        if (argc < 6) {
            debug_print("Missing parameters for add command\n");
            printf("Missing ID, Name, AGE, and GPA arguments\n");
            exit(1);
        }
        return command;
    } else {
        puts("The command you requested in invalid. Please select from one of these: SHOW, DELETE, ADD");
    }
    
    return NULL;
}
void show() {
    
    long len;
    
    
    char *file_contents = readin_file(src_file_name, &len);
    
    debug_print("%s\n", file_contents);
    
    int num_lines;
    
    char **array_of_lines = null_term_lines(file_contents, &num_lines);
   // If debug is enabled print out the lines 
    for(int i = 0; i < num_lines; i++){
        
        debug_print("%s\n", array_of_lines[i]);
        
    }
    
    
   // Go through each line, parse the strings
   // // and store them. If they are valid print them out
    for(int i = 0; i < num_lines; i++){
       // allocate memory to store each field
       // since we don't know the lenghts of the fields
       // use the line lenght to ensure we have enough space 
        char *field1 = (char *)malloc(strlen(array_of_lines[i]));
        char *field2 = (char *)malloc(strlen(array_of_lines[i]));
        char *field3 = (char *)malloc(strlen(array_of_lines[i]));
        char *field4 = (char *)malloc(strlen(array_of_lines[i]));
        
        int num_fields_read = sscanf(array_of_lines[i], "%[^,],%[^,],%[^,],%[^,]",
                                     field1,
                                     field2,
                                     field3,
                                     field4);
        if(num_fields_read != 4){
            printf("Error: not proper amount of OPT, %s\n", array_of_lines[i]);
	    // If an error happens we need to free up allocated memory
            free(field1);
            free(field2);
            free(field3);
            free(field4);
            continue;
            
        } else{
            debug_print("%s\n", field1);
            debug_print("%s\n", field2);
            debug_print("%s\n", field3);
            debug_print("%s\n", field4);
            errno = 0;
            unsigned long id =  strtoul(field1, NULL, 10);
            // if there was a problem convertion it
	    // report it and continue. i.e. fail gracefully
            if(errno != 0){
                printf("We reject this line and gracefully move onto the next, line error with id: %s\n", field1);
                free(field1);
                free(field2);
                free(field3);
                free(field4);
                continue;
            }
            
            debug_print("ID: [%lu]\n", id);
	    //Get rid of the leading spaces
            char *temp_name = field2;
            while(*temp_name && isspace(*temp_name)){
                temp_name++;
            }
            
            char *name = strdup(temp_name);
            char *end = name +strlen(name)-1;
            
            debug_print("name:[%s]\n", name);
            debug_print("end:[%s]\n",end);
            debug_print("name: [%p]\n", name);
            debug_print("end: [%p]\n", end);
	    //Get rid of trailing spaces
            while((end > name) && isspace(*end)){
                debug_print("end: [%p]\n", end);
                debug_print("[%c]\n",*end);
                end--;
                
            }
            *(end+1) = '\0';
            debug_print("NAME:[%s]\n", name);
            
            errno = 0;
            unsigned long age =  strtoul(field3, NULL, 10);
            
            if(errno != 0){
                printf("We reject this line and gracefully move onto the next, line error with name: %s\n", field3);
                free(field1);
                free(field2);
                free(field3);
                free(field4);
                continue;
            }
            debug_print("AGE: [%lu]\n", age);
            
            errno = 0;
            float gpa = strtof(field4, NULL);
            
            if(errno != 0){
                printf("We reject this line and gracefully move onto the next, line error with gpa: %s\n", field4);
                free(field1);
                free(field2);
                free(field3);
                free(field4);
                continue;
            }
            debug_print("GPA: [%f]\n", gpa);
            
            printf("Record %4d: ID=%-6lu NAME=%-40s AGE=%-3lu GPA=%3.2f\n", i+1, id, name, age, gpa);
            
             // free up the memory before we move to the next line            
            free(field1);
            free(field2);
            free(field3);
            free(field4);
        }
    }
}
// Delete the first record which matches
// the string passed in
int delete(const char *id_delete){
    
   //Make sure the record is valid 
    errno = 0;
    unsigned long id_to_delete =  strtoul(id_delete, NULL, 10);
    if(errno != 0){
        printf("Name of record to delete is invalid: [%s]\n",id_delete);
        exit(1);
    }
    long len;
    
    int found = 0;
   // Read the file into a string 
    char *file_contents = readin_file(src_file_name, &len);
    
    debug_print("%s\n", file_contents);
    
    int num_lines;
   // Split the string into individual lines 
    char **array_of_lines = null_term_lines(file_contents, &num_lines);
    
    for(int i = 0; i < num_lines; i++){
        
        debug_print("%s\n", array_of_lines[i]);
        
    }
    FILE *temp_file = fopen(tmp_file_name,"w");
    if (!temp_file) {
        printf("Unable to create file%s\n",tmp_file_name);
        exit(1);
    }
    
    for(int i = 0; i < num_lines; i++){
        char *field1 = (char *)malloc(strlen(array_of_lines[i]));
        char *field2 = (char *)malloc(strlen(array_of_lines[i]));
        char *field3 = (char *)malloc(strlen(array_of_lines[i]));
        char *field4 = (char *)malloc(strlen(array_of_lines[i]));
        
        int num_fields_read = sscanf(array_of_lines[i], "%[^,],%[^,],%[^,],%[^,]",
                                     field1,
                                     field2,
                                     field3,
                                     field4);
        if(num_fields_read != 4){
            printf("Error: not proper amount of OPT, %s", array_of_lines[i]);
            
            free(field1);
            free(field2);
            free(field3);
            free(field4);
            continue;
            
        } else{
            debug_print("%s\n", field1);
            debug_print("%s\n", field2);
            debug_print("%s\n", field3);
            debug_print("%s\n", field4);
            errno = 0;
            unsigned long id =  strtoul(field1, NULL, 10);
            
            if(errno != 0){
                printf("We reject this line and gracefully move onto the next, line error with id: %s\n", field1);
                
                free(field1);
                free(field2);
                free(field3);
                free(field4);
                continue;
            }
            
            debug_print("ID: [%lu]\n", id);
            char *temp_name = field2;
            while(*temp_name && isspace(*temp_name)){
                temp_name++;
            }
            
            char *name = strdup(temp_name);
            char *end = name +strlen(name)-1;
            
            debug_print("name:[%s]\n", name);
            debug_print("end:[%s]\n",end);
            debug_print("name: [%p]\n", name);
            debug_print("end: [%p]\n", end);
            while((end > name) && isspace(*end)){
                debug_print("end: [%p]\n", end);
                debug_print("[%c]\n",*end);
                end--;
                
            }
            *(end+1) = '\0';
            debug_print("NAME:[%s]\n", name);
            
            errno = 0;
            unsigned long age =  strtoul(field3, NULL, 10);
            
            if(errno != 0){
                printf("We reject this line and gracefully move onto the next, line error with name: %s\n", field3);
                
                free(field1);
                free(field2);
                free(field3);
                free(field4);
		free(name);
                continue;
            }
            debug_print("AGE: [%lu]\n", age);
            
            errno = 0;
            float gpa = strtof(field4, NULL);
            
            if(errno != 0){
                printf("We reject this line and gracefully move onto the next, line error with gpa: %s\n", field4);
                
                free(field1);
                free(field2);
                free(field3);
                free(field4);
		free(name);
                continue;
            }
            debug_print("GPA: [%f]\n", gpa);
            
            //printf("Record %d: ID=%-6lu NAME=%-40s AGE=%-3lu GPA=%3.2f\n", i+1, id, name, age, gpa);
            
            
           // If we have not found the entry and it matches
	   // skip adding it to the temp database
	   // // and remember that we found it so that we
	   // // don't delete the next one 
            if(!found && (id == id_to_delete)){
                found = 1;
                //skip ID and dont add it
                debug_print("ID to delete: %lu\n", id_to_delete);
                debug_print("Record %d: ID=%-6lu NAME=%-40s AGE=%-3lu GPA=%3.2f\n", i+1, id, name, age, gpa);
            } else{
                
                debug_print("ID to add to database.tmp: %lu", id);
                fprintf(temp_file, "%lu,%s,%lu,%f\n", id, name, age, gpa);
                
            }
            
            free(field1);
            free(field2);
            free(field3);
            free(field4);
	    free(name);
            
        }
    }
    // Boy oh boy, forgot to close
    // this and was scratching my head why there was no output
    // close the file to make sure all is well 
    fclose(temp_file);
   // If we didn't find an entry to delete just
   // delete the tmp file 
    if (!found) {
        printf("Sorry, that user was not found. Nothing was deleted.\n");
        char rm_cmd[1024];
        snprintf(rm_cmd, 1023, "rm %s", tmp_file_name);
        debug_print("rm_cmd: [%s]\n", rm_cmd);
        system(rm_cmd);
    } else {
        // Delete the old database.csv file
        char rm_cmd[1024];
        snprintf(rm_cmd, 1023, "rm %s", src_file_name);
        debug_print("rm_cmd: [%s]\n", rm_cmd);
        system(rm_cmd);
        
        char mv_cmd[1024];
        snprintf(mv_cmd, 1023, "mv %s %s",tmp_file_name, src_file_name);
        debug_print("mv_cmd: [%s]\n", mv_cmd);
        system(mv_cmd);
    }
    return 0;
}
// Add an entry to the database using
// the string passed in
int add(const char *id_str,
        const char *name_str,
        const char *age_str,
        const char *gpa_str){
   
      // For each field we make sure it's valid
      // convert it, and for name remove the leading
      // and trailing spaces	
    errno = 0;
    unsigned long id =  strtoul(id_str, NULL, 10);
    
    if(errno != 0){
        printf("Invalid ID option for id: %s\n", id_str);
        exit(1);
    }
    
    debug_print("ID: [%lu]\n", id);
    
    while(*name_str && isspace(*name_str)){
        name_str++;
    }
    
    char *name = strdup(name_str);
    char *end = name +strlen(name)-1;
    
    debug_print("name:[%s]\n", name);
    debug_print("end:[%s]\n",end);
    debug_print("name: [%p]\n", name);
    debug_print("end: [%p]\n", end);
    while((end > name) && isspace(*end)){
        debug_print("end: [%p]\n", end);
        debug_print("[%c]\n",*end);
        end--;
        
    }
    *(end+1) = '\0';
    debug_print("NAME:[%s]\n", name);
    
    errno = 0;
    unsigned long age =  strtoul(age_str, NULL, 10);
    
    if(errno != 0){
        printf("Invalid age for add, age=[%s]\n", age_str);
        exit(1);
    }
    debug_print("AGE: [%lu]\n", age);
    
    errno = 0;
    float gpa = strtof(gpa_str, NULL);
    
    if(errno != 0){
        printf("Invalid GPA for ADD, where gpa=[%s]\n", gpa_str);
        exit(1);
    }
   // Open the file in append mode so we can add
   // to the end of it 
    FILE *fp=fopen(src_file_name,"a");
    if (NULL == fp) {
        printf("Unable to create file [%s] to append data to it", src_file_name);
        exit(1);
    }
    // print out to the end of the file
    debug_print("ID to add to database.tmp: %lu", id);
    fprintf(fp, "%lu,%s,%lu,%f\n", id, name, age, gpa);
    fclose(fp);
    return 1;
}



int main(int argc, const char * argv[]) {
    
    char *command = checkcommand(argc, argv);
    if (NULL == command) {
        return 1;
    }
    if (0 == strcmp(command, "SHOW")) {
        show();
        //return command;
        return 0;
    } else if (0 == strcmp(command, "DELETE")) {
        delete(argv[2]);
        return 0;
    } else if    (0 == strcmp(command, "ADD")) {
        add(argv[2], argv[3], argv[4], argv[5]) ;
        return 0;
    }
    
    return 1;
    
}
