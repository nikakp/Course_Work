 
import java.util.ArrayList;  
import java.util.Random;   
 
 
public class Character{   
   
  //attributes 
  private String name;   
  private double attackValue;   
  private double maxHealth;   
  private double currHealth;   
  private int numWins;   
  private static ArrayList<Spell> spells;  
   
   
  static{ 
    spells = new ArrayList<Spell>(); 
  } 
   
   
  //a character constructer   
  public Character(String nameParam, double attackValParam, double maxHealthParam,int numWinsParam){   
     
    name = nameParam;   
    attackValue = attackValParam;   
    maxHealth = maxHealthParam;   
    currHealth = maxHealthParam;   
    numWins = numWinsParam;   
     
  }   
  //gets name 
  public String getName(){   
     
    return name;   
     
  }   
  //gets attack value 
  public double getAttackValue(){   
     
    return attackValue;   
     
  }   
   
  //gets max health 
  public double getMaxHealth(){   
     
    return maxHealth;   
     
  }   
  //gets current health 
  public double getCurrHealth(){   
     
    return currHealth;   
     
  }   
  //gets total number of wins 
  public int getNumWins(){     
     
    return numWins;   
     
  }   
  //prints out the current health and name of a character 
  public String toString(){     
     
    return (name + " current health is " + String.format("%1$.2f", currHealth) + ".");   
     
     
  }    
   
   
  /* public String fullString() {   
   return "Character [name=" + name + ", attackValue=" + attackValue + ", maxHealth=" + maxHealth + ", currHealth="   
   + currHealth + ", numWins=" + numWins + "]";   
   }*/   
   
  //gets attack damamge for a character with an integer input 
  public double getAttackDamage(int seed) {   
     
     
    Random numberGenerator = new Random(seed);   
    double v = numberGenerator.nextDouble();   
    double randomValue = .7 + 0.3*v;   
     
    return attackValue*randomValue;   
  }   
  //gives the resulting health after damage done  
  public double takeDamage(double damageDone){   
     
    currHealth -= damageDone;   
    return (currHealth );   
     
     
  }   
  //increases wins at the end of a game 
  public void increaseWins(){   
     
    numWins++;   
     
     
  }   
   
  //sets spells for a character 
  public static void setSpells(ArrayList<Spell> newSpells){  
     
    Spell newSpell;  
     
    for(int i = 0; i < newSpells.size(); i++){  
      newSpell = new Spell(newSpells.get(i));  
       
       
      spells.add(newSpell);  
    }  
     
     
  }  
  //displays the available spells 
  public static void displaySpells(){  
     
    for(int i = 0; i < spells.size(); i++){  
       
      System.out.println(spells.get(i));  
       
    }  
     
  }  
  //casts the spell on the monster 
  public static double castSpell(String spellName, int seed){  
     
     
    for(int i = 0; i < spells.size(); i++){  
      if(spellName.equalsIgnoreCase(spells.get(i).getName())){  
        return spells.get(i).getMagicDamage(seed);  
      }  
       
    }  
     
    return -1;  
     
  }  
   
  /*public static void characterTests() {   
   Character myChar = new Character("Supergirl", 22.2, 100,1000);   
   System.out.println("Supergirl: "+myChar.fullString());   
   System.out.println("tostring: "+myChar);   
   System.out.println("getName(): " + myChar.getName());   
   System.out.println("getAttackValue(): "+myChar.getAttackValue());   
   System.out.println("getMaxHealth(): "+myChar.getMaxHealth());   
   System.out.println("getCurrHealth(): "+myChar.getCurrHealth());   
   System.out.println("getNumWins(): "+myChar.getNumWins());   
   System.out.println("takeDamage(10.5): "+myChar.takeDamage(10.5));   
   System.out.println("Supergirl: "+myChar.fullString());   
   System.out.println("increaseWins()");   
   myChar.increaseWins();   
   System.out.println("Supergirl: "+myChar.fullString());   
   for(int i=0; i < 100;i++) {   
   Character wonderWoman = new Character("Wonder Woman", 100, 100, 100);   
   System.out.println("getAttackDamage: " + wonderWoman.getAttackDamage(i*i*i*7));   
   }   
   } */  
   
}