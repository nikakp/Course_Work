

public class Farm {
  
  Sheep[] farmsSheep;
  Dog farmsDog;
  String farmsName;
  
  //contrsucts the Farm
  public Farm(String name, Dog dog, Sheep[] arrayOfSheep) {
    farmsDog = dog;
    farmsName = name;
    farmsSheep = new Sheep[arrayOfSheep.length];
    
    //makes a copy of the Sheep aray
    for(int i = 0; i < arrayOfSheep.length; i++) {
      
      farmsSheep[i] = arrayOfSheep[i];
      
    }
    
    //if there are more Sheep than the Dog can hear this exception is thrown
    if(arrayOfSheep.length>dog.herd()){
      
      throw new IllegalArgumentException ("Maximum number of sheep for this dog is " 
                                            + dog.herd() + ". Received " + arrayOfSheep.length + " sheep");
      
    }
    
  }
  
  //returns name of the farm
  public String getName() {
    return farmsName;
  }
  
  //returns number of sheep
  public int getNumSheep() {
    return farmsSheep.length;
  }
  
  //prints the Farm
  public void printFarm() {
    System.out.println("Farm: "+farmsName + " Dog: " + farmsDog.getName());
    
    //prints sheeps name and age
    for(int i = 0; i < farmsSheep.length; i++){
      
      System.out.println(farmsSheep[i].getName() + " " + farmsSheep[i].getAge());
      
    }
  }
  
  //gets wool from the sheep
  public double getWool(){
    
    double sum = 0;
    
    for(int i = 0; i<farmsSheep.length; i++){
      
      sum += farmsSheep[i].shear();
      
    }    
    return sum;
    
  }
}