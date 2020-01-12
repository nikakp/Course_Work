//Name: Nika Prairie
//Student Number: 260843183
//A program that analyzes numbers inputed by the user. Useful information like whether the number is odd 
//or a negative number or if the numbers are entered in decreasing order is displayed.

public class InputAnalyzer {
  public static void main(String args[]) {
    if(args.length < 3) {
      System.out.println("You need to enter three arguments to this program. Try typing 'run InputAnalyzer 2.5 8 9' in Dr. Java, or 'java InputAnalyzer 2.5 8 9' on the command line.");
      return;
    } 
    // get the three numbers from the user
    double a = getInputDouble(args[0]);
    double b = getInputDouble(args[1]);
    double c = getInputDouble(args[2]);
    
    
    
    // set the variable nonNegativeNumbers to true if all the numbers are non-negaive
    boolean nonNegativeNumbers = ((a>=0) && (b>=0)) && (c>=0); 
    
    // set the variable oddNumber to true if one of the three numbers is an odd number
    boolean oddNumber = (a%2 == 1) || (b%2 == 1) || (c%2 == 1);
    
    // set the variable decreaseOrder to true if the numebers are entered in decreasing order
    boolean decreaseOrder = a>b && b>c;
    
    // set the variable nonNegOrDecrease to true if the numbers are either all negative or are in decreasing order
    boolean nonNegOrDecrease = nonNegativeNumbers || decreaseOrder; 
    
    // set the variable nonOddAndNeg to true if all the numbers are non negative and none of them are odd
    boolean nonOddAndNeg = !oddNumber && nonNegativeNumbers;        
    
    
    
    // displays the numbers and associated properties that we claculated above
    System.out.println("The numbers " + a + ", " + b + ", and " + c + " are all non-negative: " 
                         + nonNegativeNumbers);
    
    System.out.println("At least one between " + a + ", " + b + ", and " + c + " is odd: " + oddNumber);
    
    System.out.println("The numbers " + a + ", " + b + ", and " + c + " are in strictly decreasing order: "
                         + decreaseOrder);
    
    System.out.println("The numbers " + a + ", " + b + ", and " + c +
                       " are either all non-negative or in a strictly decreasing order: " + nonNegOrDecrease);
    
    
    System.out.println("The numbers " + a + ", " + b + ", and " + c + 
                       " are all non-negative numbers and none of them is odd: " + nonOddAndNeg);
  }
  
  public static double getInputDouble(String arg)
  {
    try
    {
      return Double.parseDouble(arg);
    } catch(NumberFormatException e) {
      System.out.println("ERROR: " + e.getMessage() + " This argument must be a number!");
    }
    
    //error, return 0
    return 0;
  }
}