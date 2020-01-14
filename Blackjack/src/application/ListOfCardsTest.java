/*
* ListOfCardsTest.java
* Class testing for the ListOfCards class
* Nika Prairie
* ICS3U
* January 15, 2018
*/
package application;


public class ListOfCardsTest extends ListOfCards {

	static public void test() {
		ListOfCards listofcards = new ListOfCards();

		listofcards.addCard(new Card(CardRank.Eight, CardSuit.Heart));	
		//assertEquals(listofcards.blackJackScore(), 8);
		assert(listofcards.blackJackScore() == 8);

		Card card2 = new Card(CardRank.Eight, CardSuit.Diamond);
		listofcards.addCard(card2);
		assert(listofcards.blackJackScore() == 16);
		Card card3 = new Card(CardRank.Ace, CardSuit.Diamond);
		listofcards.addCard(card3);
		assert(listofcards.blackJackScore() == 17);
		Card card4 = new Card(CardRank.Ace, CardSuit.Heart);
		listofcards.addCard(card4);
		assert(listofcards.blackJackScore() == 18);
		Card card5 = new Card(CardRank.Ace, CardSuit.Club);
		listofcards.addCard(card5);
		assert(listofcards.blackJackScore() == 19);
		listofcards.addCard(CardRank.Ace, CardSuit.Spade);
		listofcards.addCard(CardRank.Ace, CardSuit.Spade);
		assert(listofcards.blackJackScore() == 21);
		assert(listofcards.getNumCards()==7);
		listofcards.removeCard(CardRank.Ace, CardSuit.Spade);
		assert(listofcards.blackJackScore() == 20);
		assert(listofcards.getNumCards()==6);		
		listofcards.removeCard(card5);
		assert(listofcards.blackJackScore() == 19);
		assert(listofcards.getNumCards()==5);
		int numcards = 0;

		for (Card elem : listofcards) {
			System.out.println(elem);
			numcards++;
		}

		assert(numcards == 5);
		listofcards.removeCard(CardRank.Ace, CardSuit.Spade);
		Card taken_card = listofcards.takeCard(1);
		System.out.println("Card taken: " + taken_card);
		assert(listofcards.getNumCards()==3);

		listofcards.removeCard(1);
		assert(listofcards.getNumCards() == 2);
		listofcards.removeCard(1);
		assert(listofcards.getNumCards() == 1);
		listofcards.removeCard(0);
		assert(listofcards.getNumCards() == 0);

		listofcards = new ListOfCards();
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Spade));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Club));
		assert(listofcards.blackJackScore() == 13);
		assert(listofcards.getNumCards()==3);

		listofcards = new ListOfCards();
		listofcards.addCard(CardRank.Ten, CardSuit.Heart);
		listofcards.addCard(CardRank.Ace, CardSuit.Spade);
		assert(listofcards.blackJackScore() == 21);
		assert(listofcards.getNumCards()==2);

		listofcards = new ListOfCards();
		listofcards.addCard(new Card(CardRank.Ten, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ten, CardSuit.Spade));
		assert(listofcards.blackJackScore() == 20);
		assert(listofcards.getNumCards()==2);

		listofcards = new ListOfCards();
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Spade));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Club));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Diamond));
		assert(listofcards.blackJackScore() == 14);
		assert(listofcards.getNumCards()==4);

		listofcards = new ListOfCards();
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Spade));
		listofcards.addCard(new Card(CardRank.Ten, CardSuit.Club));
		listofcards.addCard(new Card(CardRank.Nine, CardSuit.Diamond));
		assert(listofcards.blackJackScore() == 21);
		assert(listofcards.getNumCards()==4);

		listofcards = new ListOfCards();
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Spade));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Club));
		listofcards.addCard(new Card(CardRank.Nine, CardSuit.Club));
		listofcards.addCard(new Card(CardRank.Nine, CardSuit.Diamond));
		assert(listofcards.blackJackScore() == 21);		
		assert(listofcards.getNumCards()==5);

		listofcards = new ListOfCards();
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Spade));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Club));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Diamond));
		listofcards.addCard(new Card(CardRank.Seven, CardSuit.Diamond));
		assert(listofcards.blackJackScore() == 21);		
		assert(listofcards.getNumCards()==5);
		assert(listofcards.areAllSameSuit()==false);
		
		listofcards = new ListOfCards();
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Ace, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Two, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Two, CardSuit.Heart));
		listofcards.addCard(new Card(CardRank.Two, CardSuit.Heart));
		assert(listofcards.blackJackScore() == 20);	
		assert(listofcards.getNumCards()==7);
		assert(listofcards.areAllSameSuit()==true);

		System.out.println("List of cards: " + listofcards);

	}

}
