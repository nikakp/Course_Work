//  answer.c
//  Ass7
//
// Nika Prairie
// ID: 260843183
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

enum command {east, west, north, south, gold, answer};

char *urls[4] = {
    "<a href=\"https://www.cs.mcgill.ca/~mriend10/cgi-bin/addgold.cgi?gold=%d\">Press HERE to go East<a>",
    "<a href=\"https://www.cs.mcgill.ca/~acoza/cgi-bin/addgold.cgi?gold=%d\">Press HERE to go West<a>",
    "<a href=\"https://www.cs.mcgill.ca/~wbouch4/cgi-bin/addgold.cgi?gold=%d\">Press HERE to go North<a>",
    "<a href=\"https://www.cs.mcgill.ca/~mmoham57/cgi-bin/addgold.cgi?gold=%d\">Press HERE to go South<a>"
};

#define DEBUG 0
#define debug_print(fmt, ...)                        \
do { if (DEBUG) fprintf(fp, "answer[%d]:"fmt, __LINE__, ##__VA_ARGS__); } while (0)

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

enum command string_to_command(char *cmd) {
    if (0 == strcasecmp("east", cmd)) {
        return east;
    }
    if (0 == strcasecmp("west", cmd)) {
        return west;
    }
    if (0 == strcasecmp("north", cmd)) {
        return north;
    }
    if (0 == strcasecmp("south", cmd)) {
        return south;
    }
    if (0 == strcasecmp("gold", cmd)) {
        return gold;
    }
    return answer;
}


void output_room_html(char *url, const int gold) {
    //    printf("%s\n", url);
    char html[10000];
    printf("Content-Type: text/html\n\n");
    snprintf(html, 10000, "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\">\n"
             "<html> <head>\n"
             "<title>go to room</title>\n"
             "</head>\n"
             "<body><h1><center>%s</center></h1>\n"
             "</body> </html>\n", url);
    printf(html, gold);
    return;
}

void output_you_died(const int gold_amount) {
    debug_print("gold_amount:[%d]\n", gold_amount);
    printf("Content-Type: text/html\n\n");
    printf("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\">"
           "<html> <head><title>Nika's Dungeon</title>"
           "</head>"
           "<body>"
           "<h1></h1>"
           "<center><font size=\"14\">Your Quest in Comp206's dungeon has ended,"
           " you are dead with %d gold</font>"
           "<img src=\"https://www.cs.mcgill.ca/~nprair/Comp206/death.jpg\" alt=\"Picture of death\">"
           "</center>"
           "<hr>"
           "</body> </html>\n"
           ,gold_amount);

}

void output_you_won(const int gold_amount) {
    printf("Content-Type: text/html\n\n");
    printf("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\">\n"
           "<html> <head>\n"
           "<title>won</title>\n"
           "</head>\n"
           "<body><center><font size=\"14\">\n"
           "You have won with %d gold coins!\n"
           "</font><p>"
           "<img src=\"https://www.cs.mcgill.ca/~nprair/Comp206/exit.jpg\" alt=\"Exit of the dungeon shown\">"
           "</center>\n"
           "</body> </html>\n", gold_amount);
}

void output_for_gold(const int gold_amount) {
    debug_print("It's gold you want is it???\n");
    printf("Content-Type: text/html\n\n");
    printf("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\">\n"
           "<html> <head>\n"
           "<title>GOLD</title>\n"
           "</head>\n"
           "<body><center><font size=\"14\">\n"
           "You have %d gold coins!\n"
           "<a href=\"https://www.cs.mcgill.ca/~nprair/cgi-bin/addgold.cgi?gold=%d\">RETURN TO THE GAME</a>"
           "</font><p>"
           "<img src=\"https://www.cs.mcgill.ca/~nprair/Comp206/gold.jpg\" alt=\"gold shown\"></center>"
           "</body> </html>\n", gold_amount, gold_amount);
    return;
}

void output_answer(int *gold_amount, char *answer) {
    // if 0 gold_amount execute you die and then return
    char *response;
    
    if(0 == *gold_amount){
        output_you_died(*gold_amount);
        return;
    }
    // if answer is right, increase gold_amount by 10, set response to "You got it right"
    const char *correct_answer = "malloc";
    if(0 == strcmp(answer,correct_answer)){
        *gold_amount+=10;
        response = "You got it right!";
    }    // else gold_amount is reduced by 5 set response to "You got it wrong"
    else{
        *gold_amount-=5;
        response = "You got it wrong!";
    }
    
    // if gold_amount <=0 execute you die and then return
    if(*gold_amount <= 0){
        output_you_died(*gold_amount);
        return;
    }
    
    // if gold_amount >= 100 execute you win and then return
    if(*gold_amount >= 100){
        output_you_won(*gold_amount);
        return;
    }
    
    
    // output you got the answer right, what do you want to do?
    // by invoking addgold.cgi
    printf("Content-Type: text/html\n\n");
    printf("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\">"
           "<html> <head>"
           "<title>Nika's Dungeon</title>"
           "</head>"
           
           "<body>"
           "<h1></h1>"
           "<center><font size=\"14\">%s</font>"
           "<p>"
           "<font size=\"6\">Gold Remaining: %d</font>"
           "<p>"
           "<img src=\"https://www.cs.mcgill.ca/~nprair/Comp206/dungeon.jpg\" alt=\"Picture of Nika's room\">"
           "<p>"
           "<font size=\"6\">Which C library function receives 1 parameter and"
           " allocates memory from the heap?</font>"
           "</center>"
           "<center>"
           "<font size=\"5\">Please type NORTH, SOUTH, EAST, WEST, GOLD or answer the question</font>"
           "<p>"
           "</center>"
           "<center>"
           "<form action=\"https://www.cs.mcgill.ca/~nprair/cgi-bin/answer.cgi\" method=\"get\">"
           "<input type=\"hidden\" name=\"gold\" value=\"%d\">"
           "<input type=\"text\" name=\"inputstr\""
           " style=\"font-face: 'Comic Sans MS';"
           " font-size: larger; color: black; background-color: red;"
           " border: 3pt ridge lightgrey\" size=\"20\""
           " autofocus type=\"button \">"
           "<input type=\"submit\" style=\"font-face: 'Comic Sans MS';"
           " font-size: larger; color: black; "
           " border: 3pt ridge lightgrey\"  value=\"Submit\">"
           "</form>"
           "</center>"
           "</body> </html>",
           response, *gold_amount, *gold_amount);
    
    return;
}

void test() {
    char *search ="inputstr=somedata&var2=data2&var3=data3";
    int g = 10;
    debug_print("searching [%s]\n  for:[%s]\n  found:[%s]\n",
                search,
                "inputstr",
                get_var(search,"inputstr"));
    debug_print("searching [%s]\n  for:[%s]\n  found:[%s]\n",
                search,
                "var2",
                get_var(search,"var2"));
    debug_print("searching [%s]\n  for:[%s]\n  found:[%s]\n",
                search,
                "var3",
                get_var(search,"var3"));
    debug_print("east:[%d] west:[%d] north:[%d] south:[%d] gold:[%d] answer:[%d]\n",
                east,
                west,
                north,
                south,
                gold,
                answer);
    debug_print("string_to_command(\"east\"):[%d]\n",
                string_to_command("east"));
    debug_print("string_to_command(\"EAST\"):[%d]\n",
                string_to_command("EAST"));
    debug_print("string_to_command(\"west\"):[%d]\n",
                string_to_command("west"));
    debug_print("string_to_command(\"WEST\"):[%d]\n",
                string_to_command("WEST"));
    debug_print("string_to_command(\"north\"):[%d]\n",
                string_to_command("north"));
    debug_print("string_to_command(\"NORTH\"):[%d]\n",
                string_to_command("NORTH"));
    debug_print("string_to_command(\"south\"):[%d]\n",
                string_to_command("south"));
    debug_print("string_to_command(\"SOUTH\"):[%d]\n",
                string_to_command("SOUTH"));
    debug_print("string_to_command(\"gold\"):[%d]\n",
                string_to_command("gold"));
    debug_print("string_to_command(\"GOLD\"):[%d]\n",
                string_to_command("GOLD"));
    debug_print("string_to_command(\"some answer\"):[%d]\n",
                string_to_command("some answer"));
    debug_print("string_to_command(\"\"):[%d]\n",
                string_to_command(""));
    char *query_string = "inputstr=NORTH&gold=40";
    char *var_val = get_var(query_string,"inputstr");
    enum command cmd = string_to_command(var_val);
    switch(cmd) {
        case east:
        case west:
        case north:
        case south:
            output_room_html(urls[cmd], 10);
            break;
        case gold:
            output_for_gold(10);
            break;
        default:
            output_answer(&g, "I don't know");
    }
}

void process_input(int *gold_amount, char *cmd_str) {
    enum command cmd = string_to_command(cmd_str);
    debug_print("east:[%d] west:[%d] north:[%d] south:[%d] gold:[%d] answer:[%d]\n",
                east,
                west,
                north,
                south,
                gold,
                answer);
    debug_print("cmd:[%d]\n",cmd);
    switch(cmd) {
        case east:
        case west:
        case north:
        case south:
            output_room_html(urls[cmd], *gold_amount);
            break;
        case gold:
            output_for_gold(*gold_amount);
            break;
        default:
            debug_print("*gold_amount:[%d]", *gold_amount);
            output_answer(gold_amount, cmd_str);
    }
}

int main(int argc, const char * argv[]) {
    int gold_amount = 0;
    init_debug();
    if (0) {
        test();
    } else {
        //output_answer();
        
        char *query_string=getenv("QUERY_STRING");
        debug_print("QUERY_STRING=[%s]\n",
                    query_string);
        char *gold_str = get_var(query_string,
                                 "gold");
        // If there is no gold string
        // we assume they have 10 gold
        if (NULL != gold_str) {
            gold_amount = atoi(gold_str);
        } else {
            gold_amount = 10;
        }
        debug_print("gold_amount:[%d]\n",
                    gold_amount);
        char *cmd_str = get_var(query_string,
                                "inputstr");
        debug_print("cmd_str:[%s]\n", cmd_str);
        process_input(&gold_amount, cmd_str);
        
    }
    return 0;
}

