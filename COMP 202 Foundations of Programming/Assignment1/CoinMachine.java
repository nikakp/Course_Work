//Name: Nika Prairie
//Student Number: 260843183
//A program which determines the amount of change to be retunred by the coin machine
//based on the amount entered into the machine and the price of the item

  
  public class CoinMachine {
  
  public static void main(String args[]) {
    if(args.length < 2) {
      System.out.println("You need to enter two arguments to this program. Try typing 'run CoinMachine 400 215' in Dr. Java, or 'java VendingMachine 400 215' on the command line.");
      return;
    } 
    int cash = getInputInteger(args[0]);
    int price = getInputInteger(args[1]);
    
    
    //alocates a variable for each coin type 
    int twoonies;
    int loonies;
    int quarters;
    int dimes;
    int nickels;
    int change;
    
    change = cash - price; //calculate the change required to return to the customer
    
    
    //calculate how many of each coin will be returned 
    twoonies = change/200;
    
    loonies = (change - twoonies*200)/100;
    
    quarters = (change - (loonies*100 + twoonies*200))/25;
    
    dimes = (change - (loonies*100 + twoonies*200 + quarters*25))/10;
    
    nickels = (change - (loonies*100 + twoonies*200 + quarters*25 + dimes*10))/5;
    
    //prints coins returned by the machine as well as cash inputed, cost of item and change in coins
    System.out.println("Amount received:   " + cash);
    System.out.println("Cost of the item:  " + price);
    System.out.println("Required change:   " + change);
    System.out.println(" ");
    System.out.println("Change:");
    System.out.println("    twoonies x " + twoonies);
    System.out.println("    loonie x " + loonies);
    System.out.println("    quarter x " + quarters);
    System.out.println("    dime x " + dimes);
    System.out.println("    nickel x " + nickels);
    
  }
  
  public static int getInputInteger(String arg) {
    try
    {
      return Integer.parseInt(arg);
    } catch(NumberFormatException e) {
      System.out.println("ERROR: " + e.getMessage() + " This argument must be an integer!");
    }
    
    //error, return 0
    return 0;
  }
}