//Nika Prairie
//Student Number: 260843183
// A program which prints a right triangle based on an integer
// argument passed on the command line

public class RightTriangle {
  
  public static void main(String args[]) {
    
    if (args.length != 1) {
      System.out.println("Please provide an integer > 0 on the command line");
      return;
    }
    
    int rows = Integer.parseInt(args[0]);
    
    if( rows <= 0) {
      System.out.println("Error: input value must be >= 0"); 
    }
    else{
      printRightTriangle(rows);
    }
  }
  
  
  // printRightTriangle
  //
  // This function method prints an increasing number
  // of stars based on the number of rows with
  // decreasing number of spaces before the star(s)
  //
  // Input: rows, integer number of rows and maximum 
  // number of stars printed out
  
  public static void printRightTriangle( int rows) {
    
    
    // counts number of lines 
    for(int line = 0; line < rows; line++) {
      
      //iterates number of rows minus lines and prints that many spaces
      for(int s = rows - line; s > 1; s = s - 1)   {
        
        System.out.print(" ");
      }
      
      // iterates number of lines and prints that many stars
      for(int x = 0; x <= line; x++) {
        
        System.out.print("*");
        
      }
      
      // changes to next line once spaces and stars are printed
      System.out.println("");
    }
    
  }
}
