//Nika Prairie
//Student Number: 260843183
// A program which classifies birds based on beak size, claw size and colour, 
//if it does not match type A, B or C, the program displays that this bird
//is not included in the study

public class Classifier {
  
  
  
  public static void main (String args[]) {
    
    
    if(args.length < 3) {
      System.out.println("You need to enter three arguments to this program. Try typing 'run Classifier 1 0 Grey' in Dr. Java, or 'java Classifier 1 0 Grey' on the command line.");
      return;
    } 
    
    // gets input from the command line for beak, claw and colour values
    double beak = Double.parseDouble(args[0]);
    double claw = Double.parseDouble(args[1]);
    String colour = (args[2]);
    
    // Create boolean variables which store wether the combination
    // is Type A, B or C. Initialize each to false.
    boolean isA = false;
    boolean isB = false;
    boolean isC = false;
    
    
    // Check which types are true for the combination
    // of beak, claw and colour entered on the command line
    isA = isTypeA(beak, claw, colour);
    isB = isTypeB(beak, claw, colour);
    isC = isTypeC(beak, claw, colour);
    
    
    
    // If the combination passed is not a known type, tell the user
    if( (isA == false) && (isB == false) && (isC == false)) {
      
      System.out.println("This bird is not part of the study.");
      
    }
    else {
      
      System.out.print("The type of bird is");
      // Print out if the type is A
      if(isA) {
        
        System.out.print(" A");
      }
      // Print out if the type is B
      if(isB) {
        
        System.out.print(" B");
      }
      // Print out if the type is C
      if(isC) {
        
        System.out.print(" C");
      }
      
      System.out.println(".");
      
    }
  }
  
  //method which checks if the bird is of type A
  // based on the followig table:
  // Beak Claw Colour
  // 1      0   Grey
  // 2      1   Grey
  // 3      2   Grey
  // 4      3   Grey
  // 4,5    4   Grey
  
  public static boolean isTypeA( double beak, double claw, String colour) {
    
    boolean isA = false;
    
    if(beak == 1 && claw == 0 && colour.equalsIgnoreCase("grey")) {
      
      isA = true;
      
    }
    else  if(beak == 2 && claw == 1 && colour.equalsIgnoreCase("grey")) {
      
      isA = true;
      
    }
    
    else  if(beak == 3 && claw == 2 && colour.equalsIgnoreCase("grey")) {
      
      isA = true;
      
    }
    
    else  if(beak == 4 && claw == 3 && colour.equalsIgnoreCase("grey")) {
      
      isA = true;
      
    }
    
    else  if(beak == 4 && claw == 4 && colour.equalsIgnoreCase("grey")) {
      
      isA = true;
      
    }
    
    else  if(beak == 5 && claw == 4 && colour.equalsIgnoreCase("grey")) {
      
      isA = true;
      
    }
    
    
    return isA;
  }
  
  
  //method which checks if the bird is of type B
  // based on the followig table:
  // Beak  Claw   Colour
  // 4      4,5   Grey
  // 5      6,7   Grey, Blue
  // 6      8,9   Blue
  // 7      10    Blue
  // 8      11    Blue
  public static boolean isTypeB( double beak, double claw, String colour) {
    
    
    boolean isB = false;
    
    if(beak == 4 && claw == 4 && colour.equalsIgnoreCase("grey")) {
      
      isB = true;
      
    }
    
    else  if(beak == 4 && claw == 5 && colour.equalsIgnoreCase("grey")) {
      
      isB = true;
      
    }
    else  if(beak == 5 && claw == 6 && colour.equalsIgnoreCase("grey")) {
      
      isB = true;
      
    }
    
    
    else  if(beak == 5 && claw == 7 && colour.equalsIgnoreCase("grey")) {
      
      isB = true;
      
    }
    
    else  if(beak == 5 && claw == 6 && colour.equalsIgnoreCase("blue")) {
      
      isB = true;
      
    }
    
    else  if(beak == 5 && claw == 7 && colour.equalsIgnoreCase("blue")) {
      
      isB = true;
      
    }
    
    else  if(beak == 6 && claw == 8 && colour.equalsIgnoreCase("blue")) {
      
      isB = true;
      
    }
    
    else  if(beak == 6 && claw == 9 && colour.equalsIgnoreCase("blue")) {
      
      isB = true;
      
    }
    else  if(beak == 7 && claw == 10 && colour.equalsIgnoreCase("blue")) {
      
      isB = true;
      
    }
    
    else  if(beak == 8 && claw == 11 && colour.equalsIgnoreCase("blue")) {
      
      isB = true;
      
    }
    
    
    return isB;
  }
  
  //method which checks if the bird is of type C
  // based on the followig table:
  // Beak  Claw   Colour
  // 5      6,7   Blue, Green
  // 6      8,9   Green
  // 7      10    Green
  // 8      11    Green
  // 9      12    Green
  public static boolean isTypeC( double beak, double claw, String colour) {
    
    
    boolean isC = false;
    
    if(beak == 5 && claw == 6 && colour.equalsIgnoreCase("blue")) {
      
      isC = true;
      
    }
    
    else if(beak == 5 && claw == 7 && colour.equalsIgnoreCase("blue")) {
      
      isC = true;
      
    }
    else  if(beak == 5 && claw == 6 && colour.equalsIgnoreCase("green")) {
      
      isC = true;
      
    }
    
    else  if(beak == 5 && claw == 7 && colour.equalsIgnoreCase("green")) {
      
      isC = true;
      
    }
    
    else  if(beak == 7 && claw == 10 && colour.equalsIgnoreCase("green")) {
      
      isC = true;
      
    }
    
    else  if(beak == 8 && claw == 11 && colour.equalsIgnoreCase("green")) {
      
      isC = true;
      
    }
    
    else  if(beak == 9 && claw == 12 && colour.equalsIgnoreCase("green")) {
      
      isC = true;
      
    }
    
    else  if(beak == 6 && claw == 8 && colour.equalsIgnoreCase("green")) {
      
      isC = true;
      
    }
    
    else  if(beak == 6 && claw == 9 && colour.equalsIgnoreCase("green")) {
      
      isC = true;
      
    }
    return isC;
  }
}