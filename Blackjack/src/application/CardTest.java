/*
 * CardTest.java
 * Testing code for the Card class
 * Derya Baran
 * ICS3U
 * January 22, 2018
 */
package application;

//import static org.junit.Assert.*;

//import org.junit.Test;


public class CardTest extends Card {


	//@Test
	static public void test() {
		int NumCardsCreated = 0;
		/* Generate all the possible sequences of cards
		 * for black jack and ensure that the correct rank
		 * and suit is stored.
		 */
		for (CardSuit suit: CardSuit.values()) {
		   for (CardRank rank : CardRank.values()) {
			   NumCardsCreated++;
			   Card card = new Card(rank, suit);
			   assert(card.GetRank() == rank);
			   assert(card.GetSuit() == suit);
			   System.out.println(card.toString());
			}
        }
		/* Ensure that we were able to create the correct
		 * number of cards
		 */
		assert(NumCardsCreated == 52);
		Card card = new Card(CardRank.Ten, CardSuit.Heart);
		System.out.println(card);
		assert(card.toString().equals("Ten of Hearts: hidden"));
		Card card2 = new Card(CardRank.King, CardSuit.Heart);
		assert(card2.toString().equals("King of Hearts: hidden"));
		assert(NumCardsCreated == 52);
	
		assert(card2.equals(card2));
		Card card3 = new Card(CardRank.King, CardSuit.Heart);
		assert(card3.equals(card2));
		assert(false == card3.equals(null));
		int j = 1;
		assert(false == card3.equals(j));
		assert(false == card3.equals(card));
		Card card4 = new Card(CardRank.King, CardSuit.Diamond);
		assert(false == card3.equals(card4));
		assert(card3.getValue()==10);
		j = card3.hashCode();
		System.out.println(card3 + " hash code: " + j);
		Card card5 = new Card(null, CardSuit.Diamond);
		j = card5.hashCode();
		System.out.println(card5 + " hash code: " + j);
		card5 = new Card(CardRank.Four, null);
		j = card5.hashCode();
		System.out.println(card5 + " hash code: " + j);
		card5 = new Card(CardRank.Four, CardSuit.Diamond);
		card5.hide();
		assert(card5.toString().equals("Four of Diamonds: hidden"));
		assert(card.isHidden()== true);
		card5.unhide();
		assert(card5.toString().equals("Four of Diamonds: unhidden"));
		assert(card5.isHidden() == false);
	}



}
