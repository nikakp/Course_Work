/*
 * PlayerTest.java
 * Testing the player class of the program
 * Marissa Boch
 * ICS3U
 * January 19, 2018
 */
package application;

public class PlayerTest {
	
	static public void test() {
		/*
		 * Check that a player is correctly created with and without a name being
		 * provided
		 */
		Player player1 = new Player();
		assert(player1.getName().equals("Unknown0"));
		Player player2 = new Player();
		assert(player2.getName().equals("Unknown1"));
		assert(player1.getNumCards() ==  0);
		assert(player2.getNumCards() == 0);
		player1 = new Player("Bob",1);
		player2 = new Player("Nika",1);
		assert(player1.getName().equals("Bob"));
		assert(player2.getName().equals("Nika"));
		assert(player1.getNumCards()== 0);
		assert(player2.getNumCards()== 0);
		/*
		 * Test adding cards to a player
		 */
		player1.addCard(new Card(CardRank.Eight, CardSuit.Heart));	
		assert(player1.blackJackScore() == 8);
		Card card2 = new Card(CardRank.Eight, CardSuit.Diamond);
		player1.addCard(card2,true);
		assert(player1.blackJackScore()==16);
		Card card3 = new Card(CardRank.Ace, CardSuit.Diamond);
		player1.addCard(card3, false);
		assert(player1.blackJackScore()==17);
		Card card4 = new Card(CardRank.Ace, CardSuit.Heart);
		player1.addCard(card4);
		assert(player1.blackJackScore()==18);
		Card card5 = new Card(CardRank.Ace, CardSuit.Club);
		player1.addCard(card5);
		assert(player1.blackJackScore()==19);
		player1.addCard(CardRank.Ace, CardSuit.Spade);
		assert(player1.blackJackScore()==20);
		assert(player1.getNumCards()==6);
		player1.removeCard(CardRank.Ace, CardSuit.Spade);
		assert(player1.blackJackScore()==19);
		assert(player1.getNumCards()==5);		
		player1.removeCard(card5);
		assert(player1.blackJackScore()==18);
		assert(player1.getNumCards()==4);
		System.out.println(player1);
		player1.placeBet(100);
		assert(player1.getBet()==100);
		player1.increaseBet(100);
		assert(player1.getBet()==200);
		player1.placeBet(-100);
		System.out.println(player1.getBet());
		assert(player1.getBet()==200);
		player1.increaseBet(-100);
		assert(player1.getBet()==200);
		assert(false==player1.isStanding());
		player1.setStanding();
		assert(player1.isStanding());
	}

}
