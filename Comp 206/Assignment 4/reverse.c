//Nika Prairie
//260843183
//The program takes as input from the command line two words and checks to
//see if the second word is the reverse of the first word
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, const char* argv[]){

	if(argc != 3){

		printf("Wrong number of arguments. Please input: ./reverse WORD1 WORD2.\n");

		return 1;

		//if user inputs less than or more than two  words the display message above prints

	}
	const char *word1 = argv[1];
	const char *word2 = argv[2];

	if(strlen(word1) != strlen(word2)){

		printf("WORD1=%s WORD2=%s – NOT REVERSE\n",word1,word2);

		return 0;

		//if the two words given as arguments are not the same length, returns 1
	}

	int word_length = strlen(word1);

	for(int i = 0; i < word_length; i++){

		if(*(word1+i)!=*(word2+word_length-i-1)){

			printf("WORD1=%s WORD2=%s – NOT REVERSE\n",word1,word2); 
			return 0;

		}

	}

	printf("WORD1=%s WORD2=%s – REVERSE\n",word1, word2);
	return 0;
}
