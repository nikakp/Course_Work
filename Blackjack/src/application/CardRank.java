/*
*CardRank.java
*The card rank for cards of the game blackjack
*Derya Baran
*ICS3U
*January 12, 2018
*/
package application;

public enum CardRank {
      Two(2),
      Three(3),
      Four(4),
      Five(5),
      Six(6),
      Seven(7),
      Eight(8),
      Nine(9),
      Ten(10),
      King(10),
      Queen(10),
      Jack(10),
      /*
       * An ace's value can be either 1 or 11 in blackjack. 
       * Top level code will need to deal with this
       */
      Ace(1);
	
	private int value;
	
	/**
	 * 
	 * 
	 * Pre-Conditions:
	 * 
	 * None
	 * 
	 * Post-Conditions:
	 * 
	 * The card's rank is set to value 
	 *
	 * Inputs:
	 *
	 * value: Integer value associated with the rank
	 *        
	 * 
	 */
	private CardRank(int value) {
		this.value = value;
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: No changes
	 * 
	 * 
	 * Inputs: None
	 * 
	 * Returns the value of the rank
	 */
	public int getValue() {
		return value;
	}

	

      
}