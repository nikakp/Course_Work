//Nika Prairie
//Student Number: 260843183
// A program which prints a left triangle based on the integer argument
// passed on the command line

public class LeftTriangle {
  
  public static void main(String args[]) {
    
    if (args.length != 1) {
      System.out.println("Please provide an integer > 0 on the command line");
      return;
    }
    
    int rows = Integer.parseInt(args[0]);
    
    if( rows <= 0) {
      System.out.println("Error: input value must be > 0"); 
    }
    else {
      printLeftTriangle(rows);
    }
  }
  
  // printLeftTriangle
  //
  // This function method prints an increasing number
  // of stars based on the number of rows
  //
  // Input: rows, integer number of rows and maximum 
  // number of stars printed out
  
  
  public static void printLeftTriangle( int rows) {
    
    // counts number of lines 
    for(int lines = 0; lines < rows; lines++) {
      
      // iterates number of lines and prints that many stars
      for(int x = 0; x <= lines; x++) {
        
        System.out.print("*");
        
      }
      
      // changes to next line once spaces and stars are printed
      System.out.println("");
    }
  }
}
