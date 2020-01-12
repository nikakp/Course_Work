//Nika Prairie
//260843183
//Program that implements multiple matrix related functions

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <unistd.h>



#define ROWS 5
#define COLS 5

//print matrix funciton: prints a matrix
void printMatrix(int matrix[ROWS][COLS]){


	for(int i=0; i<ROWS; i++){ //loops through each row
		for(int j=0; j<COLS; j++){ //loops through each column

			printf("%d   ", matrix[i][j]);//prints each element of the matrix

		}

		printf("\n");//new line after looping through each column in each row 

	}

}


//fills a matrix with random integers between 1-100
void fillMatrix(int matrix[ROWS][COLS]){

	srand(getpid());


	for(int i=0; i<ROWS; i++){ //loops through each row

		for(int j=0; j<COLS; j++){ // loops through each column

			matrix[i][j] = rand() % 100 + 1;//generates a random number between 1-100 and adds it as an element at [i][j]

		}

	}

}

//transposes the matrix, ie. switches [i][j] values to [j][i]
void transposeMatrix(int matrix[ROWS][COLS]){

	for(int i=0; i<ROWS; i++){ //loops through each row
		for(int j=i; j<COLS; j++){ //loops through each column

			int temp; //value with temporaily stores [i][j] value while [j][i] is inputed into that position

			temp = *(*(matrix + i)+j);//pointer notation to assign the element at [i][j] to temp

			*(*(matrix + i)+j) = *(*(matrix + j)+i); // pointer notation to assign the element at [i][j] to [j][i]

			*(*(matrix + j)+i) = temp; // pointer notation to assign the element at [j][i] to previous value that was in [i][j], which is now in temp

		}

	}

}


//multiplies a 2x5 matrix by a 5x5 matrix
void multiplyMatrix(int m1[2][COLS], int m2[ROWS][COLS], int resultMatrix[ROWS][COLS]){

	int sum = 0; //initlaize sum to 0

	for(int i=0; i<ROWS; i++){ //loops through each row
		for(int j=0; j<COLS; j++){ //loops through each column

			resultMatrix[i][j]=0; // initialize the resultMatrix to a 5x5 zero matrix

		}
	}

	for(int i=0; i<2; i++){ // loops through all the rows of the first matrix, whihc is part of the multiplication
		for(int j=0; j<COLS; j++){ // lopps through each columns of the matrices
			for(int k=0; k<ROWS; k++){ // loops through each rows of the 2nd matrix in the multiplication

				sum = sum + m1[i][k]*m2[k][j]; // add to the sum to be stored in [i][j] of the result





			}

			resultMatrix[i][j] = sum;// store the accumalated sum in the result matric at [i][j]
			sum = 0;

		}

	}

}



int main(int argc, const char* argv[]){

	int matrix[ROWS][COLS];
	fillMatrix(matrix);
	printMatrix(matrix);

	printf("\n");

	transposeMatrix(matrix);
	printMatrix(matrix);

	printf("\n");

	int matrix2[2][COLS]={0,1,2,3,4,5,6,7,8,9};
	int matrixResult[ROWS][COLS];
	multiplyMatrix(matrix2, matrix, matrixResult);
	printMatrix(matrixResult);
	return 0;

}











