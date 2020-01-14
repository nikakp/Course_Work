/*
*Dealer.java
*The dealer part of the game blackjack
*Marissa Boch
*ICS3U
*January 22, 2018
*/

package application;

public class Dealer {
	private ListOfCards CardList; //This is an instance variable for the object Dealer. Initialize a list of cards that will be used as the deck
	
	
    Dealer() { //this is the constructor
    	    this.CardList = new ListOfCards(); // Creates a new listOfCards and assigns it to CardList
    }
    
	public String getName() { //This is a getter for name, it will return the name of the object
		return "Dealer";
	}
	
	public String toString() {  //tells java what to print when asked to print an object, Java will print what is told in the toString method instead of the address of the variable in the hard drive
		return "Dealer: " + CardList.toString(); 
	}
	
	public String toStringUnhiddenOnly() { //Finds all cards that are not hidden in the CardList and prints them out
		String str = "Dealer: [";
		for (Card card : CardList) { //the for statement goes through the listOfCards one by one and adds them to a list if they are not hidden
			if (card.isHidden() == false) {
				str = str.concat(card.toString());
			}
		}
		str = str.concat("]");
		return str;
	}

	public boolean areAllSameSuit() { // Returns true if all the cards are the same suit
		return this.CardList.areAllSameSuit();
	}
	
	public void addCard(Card card) { //Takes a new card and adds it to the CardList
		CardList.addCard(card);
	}
	
	public void addCard(Card card, boolean hidden) { //Sets the card to hidden or not hidden and then adds it to the CardList
		if (hidden) {
			card.hide();
		} else {
			card.unhide();
		}
		CardList.addCard(card);
	}
	
	public void addCard(CardRank rank, CardSuit suit) { //Adds card to the CardList, then gives value to the rank and suit
		CardList.addCard(rank, suit);
	}
	
	
	public int getNumCards() { //It returns the number of cards in the list
		return CardList.getNumCards();
	}
	
	public boolean isInsuranceInEffect() {  //Adds insurance into the game
		return this.CardList.isFirstAce();
	}
	
	public void removeCard(Card card) { //This would remove a card from the list of cards
		CardList.removeCard(card);
	}
	
	public void removeCard(CardRank rank, CardSuit suit) { //This would remove a specific card with certain rank and suit
		CardList.removeCard(rank, suit);
	}
	
	public int blackJackScore() { //This method would return the score
		return CardList.blackJackScore();
	}
	
	public Card getCard(int i) { //This method would get the card stored at position i 
		return this.CardList.getCard(i);
	}
	
	public void removeAllCards() { //This method deletes the deck
		CardList.removeAllCards();
	}


}
