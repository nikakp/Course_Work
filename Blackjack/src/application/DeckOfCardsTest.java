/*
* DeckOfCards.java
* Test code for the deck of cards
* Derya Baran
* ICS3U
* January 22, 2018
*/
package application;

public class DeckOfCardsTest {

	
	static public void test() {
		DeckOfCards dc = new DeckOfCards(1);
		for (int i = 0; i<60; i++) {
			Card card = dc.getRandomCard();
			System.out.println(card);
		}
	}

}
