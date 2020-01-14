 /*
* DeckOfCards.java
* Class implementing several deck of Cards
* Derya Baran
* ICS3U
* January 20, 2018
*/
package application;

public class DeckOfCards {
	private ListOfCards CardList;

	DeckOfCards(int numCards) {
		this.CardList = new ListOfCards();
		for(int i = 0; i < numCards; i++) {
			for (CardSuit suit: CardSuit.values()) {
				for (CardRank rank : CardRank.values()) {
					this.CardList.addCard(rank, suit);
				}				
			}
		}
	}
	public Card getRandomCard() {
		int x;
		Card card = null;
		if (CardList.getNumCards() <= 0) {
			return card;
		}
		x = (int) (Math.random() * this.CardList.getNumCards());
		//System.out.println("# cards: " + this.CardList.getNumCards() + " Random card to be removed: " + x);
		card = this.CardList.takeCard(x);
		return card;
	}
	
	public String toString() {
		return CardList.toString();
	}
	
	public int getNumCards() {
		return this.CardList.getNumCards();
	}
}
