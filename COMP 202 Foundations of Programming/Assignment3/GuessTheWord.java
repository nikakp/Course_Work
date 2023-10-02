//This program plays a guessing word game
//Name: Nika Prairie
//Student Number: 260843183

// do NOT touch the following import statement
import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {
  
  private static final String[] words = {"perfect", "country", "pumpkin", "special", "freedom", "picture", "husband", 
    "monster", "seventy", "nothing", "sixteen", "morning", "journey", "history", "amazing", "dolphin", "teacher", 
    "forever", "kitchen", "holiday", "welcome", "diamond", "courage", "silence", "someone", "science", "revenge", 
    "harmony", "problem","awesome", "penguin", "youtube", "blanket", "musical", "thirteen", "princess", "assonant", 
    "thousand", "language", "chipotle", "business", "favorite", "elephant", "children", "birthday", "mountain", 
    "football", "kindness", "abdicate", "treasure", "strength", "together", "memories", "darkness", "sandwich", 
    "calendar", "marriage", "building", "function", "squirrel", "tomorrow", "champion", "sentence", "daughter", 
    "hospital", "identical", "chocolate", "beautiful", "happiness", "challenge", "celebrate", "adventure", 
    "important", "consonant", "dangerous", "irregular", "something", "knowledge", "pollution", "wrestling", 
    "pineapple", "adjective", "secretary", "ambulance", "alligator", "congruent", "community", "different", 
    "vegetable", "influence", "structure", "invisible", "wonderful", "nutrition", "crocodile", "education", 
    "beginning", "everything", "basketball", "weathering", "characters", "literature", "perfection", "volleyball", 
    "homecoming", "technology", "maleficent", "watermelon", "appreciate", "relaxation", "abominable", "government", 
    "strawberry", "retirement", "television", "silhouette", "friendship", "loneliness", "punishment", "university", 
    "confidence", "restaurant", "abstinence", "blackboard", "discipline", "helicopter", "generation", "skateboard", 
    "understand", "leadership", "revolution"};  
  
  // this method takes an integer as input and returns a radom String from the array above. 
  // you can use it, but do NOT modified neither the method NOR the above array. 
  public static String getRandomWord(int seed) {
    Random gen = new Random(seed);
    int randomIndex = gen.nextInt(words.length);
    return words[randomIndex];
  }
  //sees if the character inputed is a valid input
  public static boolean isValidGuess(char g) {
    
    return ((g >= 'a') && (g <= 'z'));
    
  }
  
  public static int[] generateArrayOfGuesses(String secretWord) {
    
    int[] guesses = new int[secretWord.length()];
    //makes an array of type int with as many zero's as there is letters in the word
    for(int i = 0; i < secretWord.length(); i++) {
      
      guesses[i]  = 0;
      
    }
    
    return guesses;
  }
  
  public static boolean checkAndUpdate(String secretWord, int [] guesses, char lastGuess) {
    
    boolean goodGuess = false;
    //sees if the user has guessed any of the letters right and replaces a 0 in the array witha 1
    for(int i = 0; i < secretWord.length(); i++) {
      
      if(secretWord.charAt(i) == lastGuess) {
        
        guesses[i] = 1;
        goodGuess = true;
      }
    }
    return goodGuess;
  }
  
  public static String getWordToDisplay(String secretWord, int[] guesses, char lastGuess) {
    
    String newStringGuess = "";
    
    for(int i = 0; i < secretWord.length(); i++) {
      
      if(guesses[i] == 0) {
        //prints - where the user hasnt guessed the right letter yet
        newStringGuess += "-";
        
      }
      else if(guesses[i] == 1) {
        
        String s = "" + secretWord.charAt(i);
        
        if(secretWord.charAt(i) == lastGuess){
          //makes the last guess capital
          newStringGuess += s.toUpperCase();
          
        } else { /// ELSE
          //makes the other guesses before the last one lower case
          newStringGuess += s.charAt(0);
        }
      }
    }
    return newStringGuess; 
  }
  
  
  //sees if the user has guessed the whole word
  public static boolean isWordGuessed(int[] guesses) {
    
    boolean isWordGuessed = true;
    
    for(int i = 0; i < guesses.length; i++) {
      //if any of the array values are 0 the boolean variable is changed to false
      if(guesses[i] == 0) {
        
        isWordGuessed = false;
        
      }
    }
    //will only return true if all the array's values are 1 meaning it has been guessed
    return isWordGuessed;
    
  }
  
  public static void play(int usersNumber) {
    
    //gets a secret word
    String secretWord = getRandomWord(usersNumber);
    
    // generates an array bassed on how many characters the secret word has
    int[] guesses = generateArrayOfGuesses(secretWord);
    
    //tells the user how many lives they have and how any characters long the word is that they are guessing
    System.out.println("Welcome to \"Guess the Word!\"");
    System.out.println("Your secret word has been generated. It has " + secretWord.length() + " characters. You have 10 lives. Good luck!");
    
    
    
    //create an in to track lives initialize it to 10
    int lives = 10;   
    
    
    //ask the user for their guess utill they have either guessed the 
    //word or they have used up all their lives
    //ask the user for their guess  
    while(lives != 0 && false == isWordGuessed(guesses)) {                   
      
      //if the player gives a string longer than 1 character report an error but no lives lost
      //if the player gives an invalid character report different error also no lives lost
      //inside above if                  
      String lastGuess;
      Scanner read = new Scanner(System.in);
      
      System.out.println();
      System.out.print("You have " + lives + " lives left. Please enter a character:");
      lastGuess = read.next();
      //lastGuess = read.nextLine();
      
      if(lastGuess.length() > 1) {
        
        //System.out.println();
        System.out.println("You can only enter one single character. Try again!");
        //System.out.println(getWordToDisplay(secretWord, guesses, lastGuess.charAt(0)));
        
      }
      else if(false == isValidGuess(lastGuess.charAt(0))) {
        
        //System.out.println();
        System.out.println("The character must be a lower case letter of the English alphabet. Try again!");
        //System.out.println(getWordToDisplay(secretWord, guesses, lastGuess.charAt(0)));
        
      }
      else { // NOT NEEDED follows from ABOVE!!! if(isValidGuess(lastGuess.charAt(0))) {
        
        if(checkAndUpdate(secretWord, guesses, lastGuess.charAt(0))) {
          //System.out.println();
          System.out.println("Good job! The secret word contains the character '" + lastGuess + "'");
          System.out.println(getWordToDisplay(secretWord, guesses, lastGuess.charAt(0)));
        } else {
          //System.out.println();
          System.out.println("There's no such character. Try again!");
          System.out.println(getWordToDisplay(secretWord, guesses, lastGuess.charAt(0)));
          lives--;
        }
      }
      
    }
    //if the user guesses the word right
    if(isWordGuessed(guesses)){
      
      System.out.println();
      System.out.println("Congratulations you guessed the secret word!");
      
    }
    //if the user doesnt guess the word in ten tries
    if(lives == 0) {
      
      System.out.println();
      System.out.println("You have no lives left, better luck next time! The secret word was: \"" + secretWord + "\"");
      
    }
    
  }
  
  public static void main(String[] args) {
    
    play(1234);
  }
  
}

