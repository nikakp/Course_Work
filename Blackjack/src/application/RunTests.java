/*
* RunTests.java
* Class that runs all the tests done with function methods
* Nika Prairie
* ICS3U
* January 20, 2018
*/
package application;

public class RunTests {
	public static void main(String[] args) {
		System.out.println("Testing Cards=============================");
		CardTest.test();
		System.out.println("Testing ListOfCards=======================");
		ListOfCardsTest.test();
		System.out.println("Testing Dealer============================");
		DealerTest.test();
		System.out.println("Testing DeckOfCards=======================");
		DeckOfCardsTest.test();
		System.out.println("Testing Player============================");
		PlayerTest.test();
		
	}
}
