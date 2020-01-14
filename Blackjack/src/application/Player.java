/*
 * Player.java
 * the player part of the game of blackjack
 * Marissa Boch
 * ICS3U
 * January 18, 2018
 */
package application;

public class Player {
	private String Name; //This is an instance variable* Initializes the name.
	public void setName(String name) { //This method makes the variable Name accessible to other classes
		Name = name;
	}

	private ListOfCards CardList; //Initializes the CardList, which is the deck
	int bet; // Initializes the bet
	boolean stand; //Initializes stand
	int funds;  //Initializes funds
	
	public int getFunds() { //returns the funds
		return funds;
	}

	public void setFunds(int funds) { //Lets you get the funds
		this.funds = funds;
	}

	public void increaseFunds(int funds) { //Lets you increase the funds
		this.funds += funds;
	}
	
	public void decreaseFunds(int funds) {  //Lets you decrease the funds
		this.funds -= funds;
		if (this.funds < 0) {
			this.funds = 0;
		}
	}
	
	private static int UnknownCount;  //Instance variable called UnknownCount it is static so it changes everywhere

	static {
		UnknownCount = 0; //it starts off as zero
	}
	
    Player(String name, int funds) {  //This is the constructor for the Player class
    	   Name = name;
    	   this.CardList = new ListOfCards(); //this creates a new object named CardList type ListOfCards
    	   this.bet = 0;
    	   this.stand = false;
    	   this.funds = funds;
    }
	
    Player() { //This constructor is used when there is no name or funds given
       	Name = "Unknown"+UnknownCount;
    	    UnknownCount++;
    	    this.CardList = new ListOfCards();
    	    this.bet = 0;
    	    this.stand = false;
    }
    
    public boolean isStanding() { //Is a getter, which returns the value of stand
    	   return this.stand;
    }
    
    public void setStanding() { //Is a setter, which lets you change the value of stand
    	   this.stand = true;    	
    }
    
	public String getName() { //Is a getter, which gets the name and returns what is stored in the variable string
		return Name;
	}
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: No changes
	 *
	 * Inputs: None
	 *
	 * Returns true if all the player's cards are of the same
	 * suit. false is returned otherwise
	 * 
	 */
	public boolean areAllSameSuit() {
		return this.CardList.areAllSameSuit();
	}
	public String toString() { //Is a toString method, which Java recognizes and tells the program to print whatever is in the toString method
		return Name+": Bet:" + bet +" Score: " + CardList.blackJackScore() + " Standing: "+stand + " Cards: " + CardList.toString() ;
	}
	public void placeBet(int betplaced) { //This method places the bet
		if (betplaced < 0) {
			return;
		}
		this.bet = betplaced;
	}
	
	public void increaseBet(int betincrease) { //Increases the bet
		if (betincrease < 0) {
			return;
		}
		this.bet += betincrease;
	}
	
	public int getBet() { //This is a getter for the variable bet
		return this.bet;
	}
	
	public void addCard(Card card) { //ass a card to the Card List
		CardList.addCard(card);
	}
	
	public void addCard(CardRank rank, CardSuit suit) { //Adds card to the CardList, then gives value to the rank and suit
		CardList.addCard(rank, suit);
	}
	
	public void addCard(Card card, boolean hidden) { //Sets the card to hidden or not hidden and then adds it to the CardList
		if (hidden) {
			card.hide();
		} else {
			card.unhide();
		}
		CardList.addCard(card);
	}
	
	public int getNumCards() { //It is a getter that returns the number of Cards in the List
		return CardList.getNumCards();
	}
	
	public void removeAllCards() { //deletes the deck
		CardList.removeAllCards();
	}
	
	public void removeCard(Card card) { //removes a card from the deck defined by the variable card
		CardList.removeCard(card);
	}
	
	public void removeCard(CardRank rank, CardSuit suit) { //removes a certain card from the deck based on rank and suit
		CardList.removeCard(rank, suit);
	}
	
	public int blackJackScore() { //returns the score of the player
		return CardList.blackJackScore();
	}

}
