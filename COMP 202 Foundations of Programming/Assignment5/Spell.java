import java.util.Random;  

public class Spell{  
  
  //spell attributes 
  private String name;  
  private double minDamage;  
  private double maxDamage;  
  private double chanceSuccess;  
  
  
  //constructor for the spell 
  public Spell(String nameParam, double minDamageParam, double maxDamageParam, double chanceSuccessParam){  
    
    name = nameParam;   
    minDamage = minDamageParam;   
    maxDamage = maxDamageParam;   
    chanceSuccess = chanceSuccessParam;   
    
    if(minDamage < 0 || minDamage > maxDamage || chanceSuccess < 0 || chanceSuccess > 1){  
      
      throw new IllegalArgumentException("One of the param values for spell is not valid");   
    }  
    
  }  
  //another constructor for the spell 
  public Spell(Spell spellParam) {  
    
    name = spellParam.name;   
    minDamage = spellParam.minDamage;  
    maxDamage = spellParam.maxDamage;  
    chanceSuccess = spellParam.chanceSuccess;  
  }  
  //gets name of the spell 
  public String getName(){   
    
    return name;   
    
  }   
  //gets the amount of damage done by the spell 
  public double getMagicDamage(int seed){  
    
    double magicDamage;  
    
    Random numberGenerator = new Random(seed);   
    double randomValue = numberGenerator.nextDouble();   
    
    if(randomValue > chanceSuccess){  
      
      magicDamage = 0;  
      return magicDamage;  
      
    }  
    else{  
      //returns the random damage between the min damage and max damage of each spell  
      return magicDamage = minDamage + (maxDamage - minDamage) * numberGenerator.nextDouble(); 
    }  
    
  }  
  //prints the spell info for each one 
  public String toString(){  
    
    return ("Name: " + name + " Damage: " + minDamage + "-" + maxDamage   
              + " Chance: " + 100.0*chanceSuccess + "%");  
    
  }  
  
  
}