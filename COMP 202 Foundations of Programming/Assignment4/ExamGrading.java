
import java.util.Arrays;

public class ExamGrading {
  
  public static void main(String[] args){
    
    char[][] responses = {{'C', 'A', 'B', 'B', 'C','A'},{'A', 'A', 'B', 'B', 'B', 'B'},{'C', 'B', 'A', 'B', 'C', 'A'},{'A', 'B', 'A', 'B', 'B', 'B'}};
    
    char[] solutions = {'C', 'A', 'B', 'B', 'C','C'};
    
 
    //runs test cose when test is initialized to true
    boolean test = false;
    if (test) {
      examGradingTests();
    } else {
      System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 1)));
    }
    
    
  }
  
  
  
  
  
  
  
  
  public static int[][] findSimilarAnswers(char[][] responses, char[] solutions, int minSusAnswers){
    
    
    // initialize multi 2D array to size of responses array so I can populate 
    //the subarray using the other methods
    int[][] similarAnswers = new int[responses.length][];
    
    
    
    for(int i = 0; i<similarAnswers.length; i++){
      
      int k = 0;
      
      //initialize the subarrays  similarAnswers using the numMatches() methods
      
      similarAnswers[i] = new int[numMatches(responses, solutions, i, minSusAnswers)];
      
      
      for(int j = 0; j<responses.length; j++){
        
        if(i != j && minSusAnswers <= numWrongSimilar(responses[i], responses[j], solutions)){
          
          similarAnswers[i][k] = j;
          k++;
          
        }
      }
      
    }
    
    return similarAnswers;
  }
  
  //gives the number of wrong answers that the student has
  
  public static int numWrongSimilar(char[] responseOne, char[] responseTwo, char[] solutions){
    
    //initializes the amount of wrong asnwers of the student to 0
    int wrongAnswers = 0;
    for(int i = 0; i<solutions.length; i++){
      
      //if the students have responses thta differ in length from one another or from the solutions
      if (responseOne.length != responseTwo.length ||
          responseOne.length != solutions.length || 
          responseTwo.length != solutions.length){
        
        throw new IllegalArgumentException ("Student responses differ in length from each-other, or the solutions");
        
      }
      
      //increases wrong answer by one if the student has a wrong answer
      
      if(responseOne[i] != solutions[i] && responseTwo[i] == responseOne[i]){
        
        wrongAnswers += 1;
      }
    }
    return wrongAnswers;
  }
  
  
  
  
  public static int numMatches(char[][] responses, char[] solutions, int index, int similar){
    
    //initialize counter to 0
    int counter = 0;
    
    for(int i = 0; i<responses.length; i++){
      
      //increases counter by one if it is greater or equal to the threshold
      if( similar <= numWrongSimilar(responses[index], responses[i], solutions) && index != i){
        
        counter += 1;
        
      }
    }
    
    return counter;  
  }
  
  //prints an array
  public static void printArray(double[] arr){
    System.out.print("[");
    for(int i = 0; i<arr.length-1; i++){
      
      System.out.print(arr[i] + ", ");
      
    }
    System.out.print(arr[arr.length-1]);
    System.out.println("]");
  }
  
  public static double[] gradeAllStudents(char[][] responses, char[] solutions) {
    
    //calculates the grade of each student
    
    //initialize each studetns points to 0
    double points = 0;
    
    //initialize the legnth of the array grades to the length of the reponses array
    double[] grades = new double[responses.length];
    
    for(int student = 0; student<responses.length; student++) {
      
      //make points back to 0 after each student
      points = 0;
      
      
      for(int j = 0; j<responses[student].length; j++){
        
        //makes sure the number of student responses for a student does not match the number 
        //of questions on the exam
        if(responses[student].length != solutions.length){
          
          throw new IllegalArgumentException ("Student " + student + " caused the error as the two lengths were conflicting. Solution length:" + 
                                              solutions.length + " and Student lenght:" + responses[student].length);
          
        }
        //if the students answer is right points in increased by 1
        if(responses[student][j] == solutions[j]){
          
          points += 1;
          
        }
        
      }
      
      grades[student] = points/responses[student].length*100;
    }
    
    //returns their grades are a pecentage
    return grades;
  }
  
  
  //test code for the program, this can be ignored
  private static void examGradingTests() {
    System.out.println("Starting grading tests");
    
    System.out.println("-------------------------");
    System.out.println("Testing numWrongSimilar");
    if (true ) {
      char[] responseOne={'A','B','C','D','C','A'};
      char[] responseTwo={'A','B','D','B','B','A'}; 
      char[] solutions={'C','B','C','D','A','A'};
      if (1 == numWrongSimilar(responseOne,responseTwo, solutions)) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      } 
    }
    if (true) {
      char[] responseOne={'D','D','D','D','D','D'};
      char[] responseTwo={'D','D','D','D','D','D'}; 
      char[] solutions={'A','B','C','A','A','A'};
      if (6 == numWrongSimilar(responseOne,responseTwo, solutions)) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    if (true) {
      char[] responseOne={'D','D','D','D','D'};
      char[] responseTwo={'D','D','D','D','D','D'}; 
      char[] solutions={'A','B','C','A','A','A'};
      boolean exceptionGenerated = false;
      try {
        numWrongSimilar(responseOne,responseTwo, solutions);
      }
      catch (IllegalArgumentException e )
      {
        exceptionGenerated = true;
      }
      if (exceptionGenerated) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    if (true) {
      char[] responseOne={'D','D','D','D','D','D'};
      char[] responseTwo={'D','D','D','D','D','D'}; 
      char[] solutions={'A','B','C','A','A'};
      boolean exceptionGenerated = false;
      try {
        numWrongSimilar(responseOne,responseTwo, solutions);
      }
      catch (IllegalArgumentException e )
      {
        exceptionGenerated = true;
      }
      if (exceptionGenerated) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    if (true) {
      char[] responseOne={'D','D','D','D','D','D'};
      char[] responseTwo={'D','D','D','D','D','D'}; 
      char[] solutions={'A','B','C','A','A','A'};
      boolean exceptionGenerated = false;
      try {
        numWrongSimilar(responseOne,responseTwo, solutions);
      }
      catch (IllegalArgumentException e )
      {
        exceptionGenerated = true;
      }
      if (!exceptionGenerated) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    System.out.println("-------------------------");
    System.out.println("Testing numMatches");
    if (true) {
      char[][] responses = {{'A', 'B', 'C', 'D', 'B','A'},{'C', 'B', 'D', 'D', 'B', 'B'},{'C', 'B', 'D', 'D', 'C', 'B'}};
      char[] solutions = {'C','B', 'C', 'D', 'A', 'A'};
      int index = 1;
      int similar = 2;
      if (1 == numMatches(responses, solutions, index, similar)) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    if (true) {
      char[][] responses = {{'A', 'B', 'C', 'D', 'B','A'},{'C', 'B', 'D', 'D', 'B', 'B'},{'C', 'B', 'D', 'D', 'C', 'B'}};
      char[] solutions = {'C','B', 'C', 'D', 'A', 'A'};
      int index = 2;
      int similar = 2;
      if (1 == numMatches(responses, solutions, index, similar)) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    if (true) {
      char[][] responses = {{'A', 'B', 'C', 'D', 'B','A'},{'C', 'B', 'D', 'D', 'B', 'B'},{'C', 'B', 'D', 'D', 'C', 'B'}};
      char[] solutions = {'C','B', 'C', 'D', 'A', 'A'};
      int index = 2;
      int similar = 7;
      if (0 == numMatches(responses, solutions, index, similar)) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    if (true) {
      char[][] responses = {{'A', 'B', 'C', 'D', 'B','A'},{'C', 'B', 'D', 'D', 'B', 'B'},{'C', 'B', 'D', 'D', 'C', 'B'}};
      char[] solutions = {'C','B', 'C', 'D', 'A', 'A'};
      int index = 2;
      int similar = 6;
      if (0 == numMatches(responses, solutions, index, similar)) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    if (true) {
      char[][] responses = {{'A','A', 'A', 'D', 'A', 'A'},{'A','A', 'A', 'D', 'A', 'A'},{'A','A', 'A', 'D', 'A', 'A'},{'C','B', 'C', 'D', 'A', 'A'}};
      char[] solutions = {'C','B', 'C', 'D', 'A', 'A'};
      int index = 2;
      int similar = 2;
      if (2 == numMatches(responses, solutions, index, similar)) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
    System.out.println("-------------------------");
    System.out.println("Testing findSimilarAnswers");
    if (true) {
      char[][] responses = {{'C', 'A', 'B', 'B', 'C','A'},{'A', 'A', 'B', 'B', 'B', 'B'},{'C', 'B', 'A', 'B', 'C', 'A'},{'A', 'B', 'A', 'B', 'B', 'B'}};
      
      char[] solutions = {'C', 'A', 'B', 'B', 'C','C'};
      System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 3)));
    }
    if (true) {
      char[][] responses = {{'A', 'A', 'B', 'B', 'B', 'B'},{'C', 'A', 'B', 'B', 'C','A'},{'A', 'A', 'B', 'B', 'B', 'B'},{'C', 'B', 'A', 'B', 'C', 'A'},{'A', 'B', 'A', 'B', 'B', 'B'}};
      
      char[] solutions = {'C', 'A', 'B', 'B', 'C','C'};
      System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 3)));
    }
    if (true) {
      char[][] responses = {{'A', 'A', 'B', 'B', 'B', 'B'},{'A', 'A', 'B', 'B', 'B', 'B'},{'C', 'A', 'B', 'B', 'C','A'},{'A', 'A', 'B', 'B', 'B', 'B'},{'C', 'B', 'A', 'B', 'C', 'A'},{'A', 'B', 'A', 'B', 'B', 'B'}};
      
      char[] solutions = {'C', 'A', 'B', 'B', 'C','C'};
      System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 3)));
    }
    if (true) {
      char[][] responses = {{'C', 'A', 'B', 'B', 'C','C'}, {'C', 'A', 'B', 'B', 'C','C'}, {'C', 'A', 'B', 'B', 'C','C'}};
      
      char[] solutions = {'C', 'A', 'B', 'B', 'C','C'};
      System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 3)));
    }
    if (true) {
      char[][] responses = {{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'}};
      
      char[] solutions = {'A', 'A', 'A', 'A', 'A','A'};
      System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 6)));
    }
    if (true) {
      char[][] responses = {{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'}};
      
      char[] solutions = {'A', 'A', 'A', 'A', 'A','A'};
      System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 7)));
    }
    if (true) {
      boolean exceptionGenerated = false;
      
      char[][] responses = {{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'},{'B', 'B', 'B', 'B', 'B','B'}};
      
      char[] solutions = {'A', 'A', 'A', 'A', 'A'};
      try {
        System.out.println(Arrays.deepToString(findSimilarAnswers(responses, solutions, 7)));
      }
      catch (IllegalArgumentException e )
      {
        exceptionGenerated = true;
      }
      if (exceptionGenerated) {
        System.out.println("Pass");
      } else {
        System.out.println("Failed");
      }
    }
  }
}