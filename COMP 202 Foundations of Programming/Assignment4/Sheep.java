import java.util.Random;

public class Sheep {
  
  private String name;
  private int age;
  private boolean hasWool;
  private static Random numberGenerator = new Random(123);
  
  //constructs the sheep
  public Sheep(String nameParam, int ageParam) {
    name = nameParam;
    age = ageParam;
    hasWool = true;
  }
  
  //returns the name of a sheep
  public String getName() {
    return name;
  }
  
  //returns the age of a sheep
  public int getAge() {
    return age;
  }
  
  //returns how many pounds of wool each sheep has once its sheared
  public double shear() {
    if (hasWool) {
      hasWool = false;
      double randomValue = 6.0 + 4.0*numberGenerator.nextDouble();
      
      while (randomValue == 10) {
        randomValue =  6.0 + 4.0*numberGenerator.nextDouble();
      }
      return randomValue;
    } 
    else {
      return 0;
    }
  }
  
  
}