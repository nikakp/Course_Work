import java.util.Scanner;
import java.util.Random;
public class WoolFactory{
  //The provided code generates random ages for sheep, and picks random names from the below array
  //You can modify this list of names as you wish (add/remove/replace elements).
  private static String[] namesForSheep = {"Cerdic","Cynric","Ceawlin","Ceol","Ceolwulf","Cynegils",
    "Cenwalh","Seaxburh","Aescwine","Centwine","Ceadwalla","Ine","Aethelheard","Cuthred","Cynewulf",
    "Berhtric","Egbert","Aethelwulf","Aethelbald","Aethelberht","Aethelred","Hengest","Aesc","Octa",
    "Eormenric","Aethelbert I","Eadbald","Earconbert","Egbert I","Hlothere","Oswine","Wihtred",
    "Aethelbert II","Sigered","Egbert II","Eadberht II","Cuthred","Baldred","Aethelfrith","Edwin","St. Oswald",
    "Oswiu","Ecgfrith","Aldfrith","Osred I","Cenred","Osric","Ceolwulf","Eadberht",
    "Aethelwald","Alhred","Aethelred I","Aelfwald I","Eardwulf","Eanred","George V","Edward VIII",
    "George VI","Elizabeth II"};
  private static Random r = new Random(123);
  
  //returns a random String from the above array. 
  private static String getRandomName(){
    int index = r.nextInt(namesForSheep.length);
    return namesForSheep[index];
  }
  //returns a random age for a sheep from 1 to 10
  private static int getRandomAge(){
    return r.nextInt(10)+1;
  }
  //End of provided name/age generation code. 
 
  
  public static int getInputInteger(String arg)
  {
    try
    {
      return Integer.parseInt(arg);
    } catch(NumberFormatException e) {
      System.out.println("ERROR: " + e.getMessage() + " This argument must be an integer!");
    }
    
    
    return -1;
  }
  
  public static void main(String[] args){
    //Student Name: Nika Prairie
    //Student Number: 260843183
    
    String farmName;
    Scanner read = new Scanner(System.in);
    
    //asks user for name of the farm
    System.out.print("What is the name of your farm? ");
    farmName = read.nextLine();
    
    System.out.println();
    
    String dogName;
    
    //asks user for name of the dog
    System.out.print("What is the name of your dog? ");
    dogName = read.nextLine();
    
    System.out.println();
    
    String dogBreed;
    
    //asks user for the type of breed of the dog
    System.out.print("What kind of dog is " + dogName + "?");
    dogBreed = read.nextLine();
    
    System.out.println();
   
    int numberOfSheep = -1;
    
  
    
    while(numberOfSheep<0){
    
      
      
    System.out.print("And how many sheep do you have?");
    numberOfSheep = getInputInteger(read.nextLine());
    //if the user enters a value less than 0 it asks for another value
    if(numberOfSheep < 0){
      
      System.out.print("Plaease enter an integer bigger or equal to 0!");
      
    }
    
     System.out.println();
    
    }
    
   
    //generates the Sheep array
    Sheep[] arrayOfSheep = new Sheep[numberOfSheep];
    
    for(int i = 0; i < numberOfSheep; i++){
      
      arrayOfSheep[i] = new Sheep(getRandomName(), getRandomAge());
      
    }
    
    //makes myFarm by passing in everything we did above
   Farm myFarm = new Farm(farmName, new Dog(dogName, dogBreed), arrayOfSheep);
  
   //prints the Farm
     myFarm.printFarm();
   //calculates how many pounds of wool
     double woolInPounds = myFarm.getWool();
     //calculates the money made from all that wool
     double valueForWool = woolInPounds* 1.45;
     
     //prints amount of wool and the money made from it
    System.out.println("We just sheared " + woolInPounds + "lbs of wool for a value of $" + valueForWool); 
    
    
    
    
  }
}
