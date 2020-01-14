/*
*DealerTest.java
*Testing the dealer class of the program
*Derya Baran
*ICS3U
*January 22, 2018
*/
package application;

public class DealerTest {
	static public void test() {
		/*
		 * Check that a dealer is correctly created with and without a name being
		 * provided
		 */
		Dealer dealer1 = new Dealer();
		assert(dealer1.getName().equals("Dealer"));
		Dealer dealer2 = new Dealer();
		assert(dealer2.getName().equals("Dealer"));
		assert(dealer1.getNumCards() == 0);
		assert(dealer2.getNumCards() == 0);
		dealer1 = new Dealer();
		dealer2 = new Dealer();
		assert(dealer1.getName().equals("Dealer"));
		assert(dealer2.getName().equals("Dealer"));
		assert(dealer1.getNumCards()== 0);
		assert(dealer2.getNumCards() == 0);
		/*
		 * Test adding cards to a dealer
		 */
		dealer1.addCard(new Card(CardRank.Eight, CardSuit.Heart));	
		assert(dealer1.blackJackScore() == 8);
		Card card2 = new Card(CardRank.Eight, CardSuit.Diamond);
		dealer1.addCard(card2);
		assert(dealer1.blackJackScore()==16);
		Card card3 = new Card(CardRank.Ace, CardSuit.Diamond);
		card3.unhide();
		dealer1.addCard(card3, false);
		assert(dealer1.blackJackScore()==17);
		Card card4 = new Card(CardRank.Ace, CardSuit.Heart);
		dealer1.addCard(card4);
		assert(dealer1.blackJackScore()==18);
		Card card5 = new Card(CardRank.Ace, CardSuit.Club);
		dealer1.addCard(card5);
		assert(dealer1.blackJackScore()==19);
		dealer1.addCard(CardRank.Ace, CardSuit.Spade);
		assert(dealer1.blackJackScore()==20);
		assert(dealer1.getNumCards()==6);
		dealer1.removeCard(CardRank.Ace, CardSuit.Spade);
		assert(dealer1.blackJackScore()==19);
		assert(dealer1.getNumCards()==5);		
		dealer1.removeCard(card5);
		assert(dealer1.blackJackScore()==18);
		assert(dealer1.getNumCards()==4);
		Card card6 = new Card(CardRank.Three, CardSuit.Club);
		dealer1.addCard(card6, false);
		System.out.println(dealer1.toStringUnhiddenOnly());
		System.out.println(dealer1);
		
	}

}
