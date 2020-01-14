/*
 * Card.java
 * the class definition of a card for the game of blackjack
 * Derya Baran
 * ICS3U
 * January 12, 2018
 */
package application;

import java.util.Objects;

public class Card {
	
	public final CardSuit suit;
	public final CardRank rank;
	public boolean hidden;
	
	/**
	 * Constructor for a card
	 * 
	 * Pre-Conditions:
	 * 
	 * None
	 * 
	 * Post-Conditions:
	 * 
	 *  A new card is created with the rank of Ace and
	 *  suite of heart. It's marked as hidden.
	 * 
	 * Inputs: None
	 * 
	 */	
	public Card() {
		suit = CardSuit.Heart;
		rank = CardRank.Ace;
		hidden = true;
	}
	
	/**
	 * Constructor for a card
	 * 
	 * Pre-Conditions:
	 * 
	 * None
	 * 
	 * Post-Conditions:
	 * 
	 *  A new card is created with the rank and suit passed in
	 *  it's marked as hidden
	 * 
	 * Inputs: 
	 * 
	 * startRank: The rank that the new card is to have
	 * startSuite: The suit that the new card is to have
	 */
	public Card(CardRank startRank, CardSuit startSuit) {
		suit = startSuit;
		rank = startRank;
		hidden = true;
	}
	/**
	 * This function returns a hash for the object
	 * 
	 * Pre-Conditions:
	 *  The rank and suit of the card is set
	 * 
	 * Post-Conditions:
	 *  No changes
	 *  
	 * Inputs: None
	 * 
	 * Return:
	 * 
	 *  A hash code based on the rank and suit of the card
	 *  is returned
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}
	/**
	 * 
	 * 
	 * Pre-Conditions:
	 * 
	 * None
	 * 
	 * Post-Conditions:
	 * 
	 * No changes
	 * 
	 * Inputs:
	 * 
	 *  obj: The object against which this should be compared to determine
	 *       if they are identical
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions:
	 * 
	 * The card is flagged as hidden
	 * 
	 * Inputs: None
	 * 
	 */
	public void hide() {
		hidden = true;
	}
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: The card is flagged are unhidden
	 * 
	 * 
	 * Inputs: None
	 * 
	 */
	public void unhide() {
		hidden = false;
	}
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: None
	 * 
	 * 
	 * Inputs: None
	 * 
	 * Returns: True if the card is hidden, false otherwise
	 *
	 */
	public boolean isHidden() {
		return this.hidden;
	}
	
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * Post-Conditions: None
	 * 
	 * 
	 * Inputs: None
	 * 
	 * Returns the card's rank 
	 * 
	 */
	public CardRank GetRank() {
		return rank;
	}
	
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * Post-Conditions: None
	 * 
	 * 
	 * Inputs: None
	 * 
	 * Returns the card's suit 
	 * 
	 */
	public CardSuit GetSuit() {
		return suit;
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * Post-Conditions: None
	 * 
	 * 
	 * Inputs: None
	 * 
	 * Returns the value of the card, for an Ace 1 is returned
	 * 
	 */
	public int getValue() {
		return rank.getValue();
	}
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * Post-Conditions: None
	 * 
	 * Inputs: None
	 * 
	 * Returns a string representing the card's
	 * suite, rank and whether it's hidden  
	 * 
	 */
	public String toString() {
		if (hidden) {
			return rank + " of " + suit + "s: hidden";
		} 
	  return rank + " of " + suit + "s: unhidden";
	}

}
