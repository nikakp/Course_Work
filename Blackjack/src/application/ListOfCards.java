/*
 * ListOfCards.java
 * Class implementing a list of cards and that calculates
 * the blackjack score for a list of cards
 * Nika Prairie
 * ICS3U
 * January 15, 2018
 */
package application;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import application.CardRank;


public class ListOfCards implements Iterable<Card> {
	private List<Card> CardList;
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions:
	 *  List of cards created
	 * 
	 * Inputs: None
	 * 
	 */
	public ListOfCards() {
		this.CardList = new ArrayList<>();
	}
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: A new iterator is created
	 * 
	 * 
	 * Inputs: None
	 *
	 * Return:
	 * 
	 *  An iterator for the card list is returned
	 * 
	 */
	@Override
	public Iterator<Card> iterator() {
		return Collections.unmodifiableList(CardList).iterator();
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
	 * Returns the number of cards in the list
	 * 
	 */
	public int getNumCards() {
		return CardList.size();
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
	 * Returns true if the first card is an ace, 
	 *         false otherwise
	 */
	public boolean isFirstAce() {
		if (this.CardList.size() == 0) {
			return false;
		}
		return (this.CardList.get(0).GetRank() == CardRank.Ace);
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions:
	 *  The card passed in is added to the list of cards
	 * 
	 * Inputs:
	 *  card: A Card is passed in and it's added to the list
	 */
	public void addCard(Card card) {
		CardList.add(card);
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions:
	 *  A card with the rank and suit passed in is added
	 *  to the list of cards
	 * 
	 * Inputs:
	 *  rank: The rank of the card to add to the list
	 *  suit: The suit of the card to add to the list
	 *
	 */
	public void addCard(CardRank rank, CardSuit suit) {
		Card card = new Card(rank, suit);
		CardList.add(card);
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: 
	 *
	 * A card with the matching rank and suit is removed from the list.
	 * Only 1 card is removed from the list
	 * If there is no card with the matching rank and suit in the list
	 * there are no changes to the list.
	 *
	 * Inputs:
	 *
	 * rank: The rank of the card to be removed from the list
	 * suit: The suit of the card to be removed from the list
	 * 
	 */
	public void removeCard(CardRank rank, CardSuit suit) {

		int i = 0;
		Card card = new Card(rank, suit);
		// Let's go through the list of cards to see if we can find it
		while ((i < CardList.size()) && (CardList.get(i).equals(card)==false)) {
			i++;
		}
		// if i is less than the number of cards in the list
		// it must mean we have found it
		if (i < CardList.size()) {
			assert(CardList.get(i).equals(card));
			CardList.remove(i);
		}
		return;
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
	 * Returns true if all the cards in the list are of the same
	 * suit. false is returned otherwise
	 * 
	 */
	public boolean areAllSameSuit() {

		CardSuit suit;
		if (CardList.isEmpty()) {
			return false;
		}
		suit = CardList.get(0).GetSuit();
		// Let's go through the list of cards
		// if any card doesn't have the rank
		// of the first card, then return false
		for (Card elem : this.CardList) {
			if (elem.GetSuit() != suit) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: 
	 * If there is a card with index num it's removed from the list
	 * 
	 * 
	 * Inputs:
	 * num: Integer index to be removed from the list.
	 * 
	 */
	public void removeCard(int num) {
		CardList.remove(num);
		return;
	}	

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions:
	 * the card at index num is removed from the list
	 * If there are fewer than num cards, an exception is
	 * generated
	 * 
	 * Inputs:
	 *  num: The index of the card to be removed from the list
	 */
	public Card takeCard(int num) {
		Card taken_card = CardList.remove(num);
		return taken_card; 
	}	

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: No changes occur
	 * 
	 * 
	 * Inputs:
	 * num: Integer index of the card to be returned
	 *
	 * Returns:
	 *  A card which is at the position num. If num
	 *  is larger than the number of elements in the list
	 *  an exception is returned
	 * 
	 */
	public Card getCard(int num) {
		return this.CardList.get(num);
	}
	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: All cards are removed from the list of cards
	 * 
	 * 
	 * Inputs: None
	 * 
	 */
	public void removeAllCards() {
		this.CardList.clear();
	}

	/**
	 * 
	 * 
	 * Pre-Conditions: None
	 * 
	 * 
	 * Post-Conditions: 
	 *
	 * A card matching the parameter card is removed from the list.
	 * Only 1 card is removed from the list
	 * If there is no card matching the passed card in the list
	 * there are no changes to the list.
	 *
	 * Inputs:
	 *
	 * card: The card passed in is used to match against the list
	 *       to determine which card should be removed from the list
	 * 
	 */
	public void removeCard(Card card) {

		int i = 0;
		// Let's go through the list of cards to see if we can find it
		while ((i < CardList.size()) && (CardList.get(i).equals(card)==false)) {
			i++;
		}
		// if i is less than the number of cards in the list
		// it must mean we have found it
		if (i < CardList.size()) {
			assert(CardList.get(i).equals(card));
			CardList.remove(i);
		}
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
	 * Returns a string representation of the list of cards
	 * 
	 */
	public String toString() {
		return CardList.toString();

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
	 * Returns an integer which is the blackjack score for the list 
	 *         of cards
	 *  
	 */
	public int blackJackScore() {
		int score = 0;
		int num_aces = 0;
		for (Card card : CardList) {
			/* Let's calculate the score without the aces 
			 * to start with since they can either be a 1
			 * or 11
			 */
			if (card.GetRank() != CardRank.Ace) {
				score += card.getValue();
			} else {
				num_aces++;
			}

		}
		/*
		 * Let's add the aces to the score
		 */
		if (num_aces > 0) {
			/*
			 * Assume that at first the aces are worth 1
			 * Then we see if one of the aces could be worth 11
			 * without going over 21. We know that 2 or more aces
			 * can't be worth 11 because it would add up to 22 or more
			 * so we count all the other aces, but one of them, as 1
			 */
			int possible_score = score + num_aces;
			if ((possible_score + 10) <= 21) {
				score = possible_score + 10;
			} else {
				score = possible_score;
			}

		}
		return score;
	}


}

