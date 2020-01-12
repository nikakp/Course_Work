//Studnet Id 260843183
//Nika Prairie

import java.util.ArrayList;  
import java.util.Random; 
import java.util.Scanner; 


public class BattleGame{  
  
  public static void main(String[] args) {
    BattleGame.playGame("player.txt",  
                        "monster.txt","spells.txt"); 
  }
  
  private static Random randomAttribute = new Random(789);  
  
  public static void playGame(String playerFile, String monsterFile, String spellsFile) {  
    //initialize variable
    boolean useSpells = true; 
    //create player
    Character player = FileIO.readCharacter(playerFile); 
    //create monster
    Character monster = FileIO.readCharacter(monsterFile); 
    //make the list of spells
    ArrayList<Spell> listOfSpells = FileIO.readSpells(spellsFile); 
    
    
    //check that monster and player arent invalid
    if(checkIfCharactersValid(player, monster) == false){ 
      System.out.println("Unable to read player or monster, goodbye!"); 
      return; 
    } 
    if(listOfSpells == null){ 
      
      System.out.println("This game will be player without the use of spells");
      System.out.println();
      useSpells = false; 
    } 
    
    //display information of the two characters in the game and list of spells if
    //they are being used from the spells.txt file
    
    printInitialCharacterStats(player); 
    System.out.println(""); 
    printInitialCharacterStats(monster); 
    System.out.println(""); 
    if(useSpells) { 
      Character.setSpells(listOfSpells); 
      Character.displaySpells(); 
    } 
    System.out.println(""); 
    
    
    
    //create scanner to get info from user
    Scanner gameReader = new Scanner(System.in);  
    
    //for when the game is played with spells 
    while (player.getCurrHealth() >= 0 && monster.getCurrHealth() >= 0 && useSpells){ 
      
      //prompts user for an imput command
      System.out.println("Enter a command:"); 
      String command = gameReader.nextLine(); 
      //checks if the users input is valid
      if(isValidCommandWithSpells(command, listOfSpells, player)) { 
        //when the user enters attack
        if (command.equalsIgnoreCase("attack")){ 
          attackCommand(player, monster); 
          //if the user eneters quit
        } else if (command.equalsIgnoreCase("quit")){ 
          System.out.println("Thank you for playing, goodbye!"); 
          gameReader.close(); 
          return; 
        } else{ 
          //when the user enters a spell
          spellsCommand(player, monster, command, listOfSpells); 
          
        } //if the user eneters an unknown spell
      }else{
        
        onlyMonsterAttack(player, monster);
        
      }
    } 
    
    //for when the game is played without spells 
    while (player.getCurrHealth() >= 0 && monster.getCurrHealth() >= 0 ){ 
      //prompts user for an imput command
      System.out.println("Enter a command:"); 
      String command = gameReader.nextLine();
      //checks if the users input is valid
      if(isValidCommand(command)) { 
        //when the user attacks
        if (command.equalsIgnoreCase("attack")){ 
          attackCommand(player, monster); 
        } else if (command.equalsIgnoreCase("quit")){ 
          //when the user quits
          System.out.println("Thank you for playing, goodbye!"); 
          gameReader.close(); 
          return; 
        } 
      } 
    } 
    
    if (player.getCurrHealth() > 0) {
      System.out.println();
      System.out.println("Fantastic! You killed the monster!");
      player.increaseWins();
      FileIO.writeCharacter(player, playerFile);
      System.out.println("Successfully wrote to file: "+playerFile);
      System.out.println(player.getName() + " has won: " + player.getNumWins() + " times");
      System.out.println(""+ monster.getName()+" has been knocked out of the game"); 
    } else {
      System.out.println(player.getName()+" has been knocked out of the game"); 
      
      monster.increaseWins(); 
      System.out.println();
      System.out.println("Oh no! You lost!");
      FileIO.writeCharacter(monster, monsterFile);
      System.out.println("Successfully wrote to file: "+monsterFile);
      System.out.println(monster.getName() + " has won: " + monster.getNumWins() + " times");
      
    }
    
    gameReader.close(); 
    System.out.println("Game over, goodbye!"); 
    return; 
  } 
  //helper method to display player and monster initial stats
  private static void printInitialCharacterStats(Character c) { 
    
    System.out.println("Name:  " + c.getName()); 
    System.out.println("Health:  " + c.getMaxHealth()); 
    System.out.println("Attack:  " + c.getAttackValue()); 
    System.out.println("Number of Wins:  " + c.getNumWins()); 
  } 
  
  
  //helper method to see if the characters are valid
  private static boolean checkIfCharactersValid(Character player, Character monster){ 
    
    if ((player == null) || (monster == null)){ 
      return false; 
    } 
    return true; 
  } 
  
  //checks if the users input is a valid command
  private static boolean isValidCommand(String command){ 
    
    if((command.equalsIgnoreCase("attack")) || (command.equalsIgnoreCase("quit"))){ 
      return true; 
    } 
    System.out.println("Your input was not regongnized, we suggest attack or quit as possible commands"); 
    return false; 
  } 
  
  
  
  //checks if the users input is a valid command including the use of spells
  
  private static boolean isValidCommandWithSpells(String command, ArrayList<Spell> listOfSpells, Character player){ 
    
    if((command.equalsIgnoreCase("attack")) || (command.equalsIgnoreCase("quit"))){ 
      
      return true; 
      
    } 
    
    for(int i = 0; i < listOfSpells.size(); i++){ 
      if(command.equalsIgnoreCase(listOfSpells.get(i).getName())){ 
        return true; 
      } 
    } 
    System.out.println(player.getName() + " tried to cast " + command + ", but they don't know that spell."); 
    return false;  
  } 
  
  
  //helper method for when the player casts a spell on the monster
  private static boolean spellsCommand(Character player, Character monster, String command 
                                         , ArrayList<Spell> listOfSpells){ 
    
    String spellStr; 
    double damage; 
    
    // The player casts spell
    int randomNum = randomAttribute.nextInt(); 
    damage = Character.castSpell(command,randomNum); 
    
    if(damage > 0){
      spellStr = String.format("%1$.2f", damage); 
      System.out.println(player.getName() + " casts " + command + " dealing "  + spellStr + " damage!");  
    }else if (damage == 0){
      //when damage is 0 or bellow
      System.out.println(player.getName() + " tried to cast " + command + ", but they failed.");
      
    } else if (damage < 0) {
      return true;
    }
    
    monster.takeDamage(damage); 
    
    //for when the monsters new health is printed
    if (monster.getCurrHealth() > 0 && monster.getCurrHealth() != monster.getMaxHealth()) { 
      System.out.println(monster); 
      //for when the monsters health remains the same because the player did no damage
    } else if(monster.getCurrHealth() == monster.getMaxHealth()){
      
      System.out.print("");
      
    } else { //when the monster gets knocked out of the game
      
      return true; 
      
    } 
    System.out.println("here");
    System.out.println(); 
    
    // The monster attacks 
    randomNum = randomAttribute.nextInt(); 
    damage = monster.getAttackDamage(randomNum); 
    //damage = monster.getAttackDamage(123); 
    spellStr = String.format("%1$.2f", damage); 
    System.out.println(monster.getName() + " attacks for " + spellStr + " damage!");  
    
    player.takeDamage(damage); 
    
    if (player.getCurrHealth() > 0) { 
      System.out.println(player); 
      System.out.println(); 
    } else { //when the player gets knocked out of the game
      
      return true; 
      
    } 
    
    return false; 
  } 
  
  
  
  private static boolean attackCommand(Character player, Character monster){ 
    
    String attackStr; 
    double damage; 
    
    // The player attacks 
    int randomNum = randomAttribute.nextInt(); 
    damage = player.getAttackDamage(randomNum); 
    
    //display message for total damage inflicted
    attackStr = String.format("%1$.2f", damage); 
    System.out.println(player.getName() + " attacks for " + attackStr + " damage!");  
    
    
    monster.takeDamage(damage); 
    
    if (monster.getCurrHealth() > 0) { 
      System.out.println(monster); 
    } else { //when the moster gets knocked out of the game
      
      return true; 
      
    }
    
    System.out.println(); 
    
    // The monster attacks 
    randomNum = randomAttribute.nextInt(); 
    damage = monster.getAttackDamage(randomNum); 
    
    attackStr = String.format("%1$.2f", damage); 
    //display message for total damage inflicted
    System.out.println(monster.getName() + " attacks for " + attackStr + " damage!");  
    
    player.takeDamage(damage); 
    
    if (player.getCurrHealth() > 0) { 
      System.out.println(player); 
      System.out.println(); 
    } else { 
      
      return true; 
      
    } 
    
    return false; 
  }
  
  private static boolean onlyMonsterAttack(Character player, Character monster) {
    
    
    // The monster attacks 
    int randomNum = randomAttribute.nextInt(); 
    double damage = monster.getAttackDamage(randomNum); 
    
    String attackStr = String.format("%1$.2f", damage); 
    //display message for total damage inflicted
    System.out.println(monster.getName() + " attacks for " + attackStr + " damage!");  
    
    player.takeDamage(damage); 
    
    if (player.getCurrHealth() > 0) { 
      System.out.println(player); 
      System.out.println(); 
    } else { 
      //when the player gets knocked out of the game
      
      return true; 
      
    }
    
    return false;
    
  }
  
  
}    
