#include <stdio.h>
#include <stdlib.h>

/*
 Nika Prairie
 ID: 260834183
 Comp 206 Assignment 3
 This program prints a right sided triangle with
 a spefified size taken from the user
*/

int main(int argc, char *argv[])
{
   int size = 0;

   // Did the user enter command line parameters?
   if (argc < 2) { 
      //prints a prompt telling the user to enter a valid value and takes the new value
      puts("Please provide an integer > 0");
      scanf("%d", &size);
   } else {
    // We don't really care if the user entered too many arguments
    // it's only important the first one provided on the command
    // line is an integer 
     //size of the triangle is assigned to the first input of the user 
     size = atoi(argv[1]);
   }
   // ensure that the triangle to be printed out is at least one
   // in size
   if (size < 1) {
      puts("An incorrect triangle size was inputted. The size must be "
           "greater than 0 and an integer number. Syntax: ./triangles SIZE");
   return 1;
   } 

//counts number of lines
   for(int line = 0; line < size; line++){

      //iterates number of rows minus lines and prints that may spaces
      for(int s = size - line; s > 1; s--){
         printf(" ");
      }

      //iterates number of lines and prints that many stars
      for(int x = 0; x <= line; x++){
         printf("*");
      }
      //changes to next line once spaces and stars are printed
      puts("");
   }

   return 0;
}
