import java.io.FileWriter; 
import java.io.BufferedWriter; 
import java.util.ArrayList;  
import java.io.BufferedReader;   
import java.io.FileReader;   
import java.io.FileNotFoundException;   
import java.io.IOException;   



public class FileIO{   
  //creates the characters from txt files, monster and player 
  public static Character readCharacter(String fileName){   
    
    String line = "";   
    String characterName = "";   
    double attackValue = 0;;   
    double maxHealth = 0;   
    int numWins = 0;   
    BufferedReader in = null;   
    try {   
      FileReader file = new FileReader(fileName);   
      
      in = new BufferedReader(file);   
      
      //reads the file and assigns things from the file to the characters  
      //as each one is called from the BattleGame class 
      try {   
        if ( (line = in.readLine()) != null){   
          characterName = line;   
        } else {   
          in.close();   
          return null;   
        }   
        
        if ( (line = in.readLine()) != null){   
          attackValue = Double.parseDouble(line);   
        }else {   
          in.close();   
          return null;   
        }   
        
        if ( (line = in.readLine()) != null){   
          maxHealth = Double.parseDouble(line);   
        }else {   
          in.close();   
          return null;   
        }   
        
        if ( (line = in.readLine()) != null){   
          numWins = Integer.parseInt(line);   
        }else {   
          in.close();   
          return null;   
        }   
        in.close();   
      }   //catches IOExeption and FileNotFoundException 
      catch (IOException | NumberFormatException e) {   
        System.out.println("IOException | NumberFormatException: " + e.getMessage());   
        return null;   
      }    
    }   
    catch(FileNotFoundException e) {   
      System.out.println("File not found exception: " + e.getMessage());   
      return null;   
    }    
    //returns the character made with the info from the file 
    Character aCharacter = new Character(characterName, attackValue, maxHealth, numWins);   
    return aCharacter;   
  }   
  //creates the spells and stores them in a ArrayList 
  public static ArrayList<Spell> readSpells(String fileName){  
    
    String line = "";   
    
    BufferedReader in = null;   
    ArrayList<Spell> listOfSpells = new ArrayList<Spell>();  
    
    try {   
      FileReader file = new FileReader(fileName);   
      
      in = new BufferedReader(file);   
      
      try {   
        //reads each line and splits up the 4 peices of info from one line and creates a  
        //spell which is stores in the ArrayList untill there is no more info to build spells 
        while((line = in.readLine()) != null){  
          String[] spellParts = line.split("\t");  
          if (spellParts.length == 4) {  
            Spell newSpell = new Spell(spellParts[0],   
                                       Double.parseDouble(spellParts[1]),  
                                       Double.parseDouble(spellParts[2]),   
                                       Double.parseDouble(spellParts[3]));  
            
            listOfSpells.add(newSpell);  
            
          }  
        }  
        in.close();   
      }   //catches the excpetions associated with these objects 
      catch (IOException | NumberFormatException e) {   
        System.out.println("IOException | NumberFormatException: " + e.getMessage());   
      }    
    }   
    catch(FileNotFoundException e) {   
      System.out.println("File not found exception: " + e.getMessage());   
    }  
    
    if(listOfSpells.size() > 0){ 
      
      return listOfSpells;  
      
    }else { 
      
      return null; 
      
    } 
  }  
  
  public static void FileIOTests() {   
    
    /*Character myChar = readCharacter("character.txtt");   
     if (myChar == null) {   
     System.out.println("Error reported for invalid file");   
     }   
     myChar = readCharacter("/Users/nikakp/Documents/Assignment 5/character.txt");   
     if (myChar == null) {   
     System.out.println("Problem with file, unable to read data");   
     } else {   
     System.out.println("readCharacter(\"character.txt\"): " + myChar.fullString());   
     }   
     */  
    
    ArrayList<Spell> mySpells = readSpells("spells.txtt");   
    if (mySpells == null ) {  
      System.out.println("Error reported for invalid file");   
    }   
    mySpells = readSpells("/Users/nikakp/Documents/Assignment 5/spells.txt");   
    if (mySpells == null) {   
      System.out.println("Problem with file, unable to read data");   
    } else {   
      System.out.println("readSpells(\"spells.txt\"): ");  
      for(int i = 0; i<mySpells.size(); i++) {  
        System.out.println(mySpells.get(i).toString());   
      }  
      
    } 
    
  }  
  //this method updates the winners stats in the txt file whether it be monster or player 
  //it increases there number of wins by 1 in the txt file 
  public static void writeCharacter(Character c, String fileName){ 
    
    
    
    try{ 
      
      //creates the filewriter and bufferedwriter 
      FileWriter fw = new FileWriter(fileName); 
      BufferedWriter bw = new BufferedWriter(fw); 
      
      bw.write(c.getName()+"\n"); 
      bw.write(c.getAttackValue()+"\n"); 
      bw.write(c.getMaxHealth()+"\n"); 
      bw.write(c.getNumWins()+"\n"); 
      bw.close(); 
      fw.close(); 
      
    }catch(IOException e){ 
      
      System.out.println("File not found exception: " + e.getMessage()); 
      
    } 
    
    
    
    
  } 
  
}