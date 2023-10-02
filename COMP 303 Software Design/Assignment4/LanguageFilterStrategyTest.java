package comp303.assignment4;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import java.io.File;

//import org.graalvm.compiler.debug.Assertions;
import org.junit.jupiter.api.Test;

class LanguageFilterStrategyTest {

	@Test
	void test() {
		System.out.println("yes");
		Library myTestingLib = new Library();
		File myFile = new File("../Movies/mm1.mp4");
		
		Movie mv1  = new Movie(myFile,"Sound of Music", Language.ENGLISH, "Warner Brothers");
		myTestingLib.addMovie(mv1);
		Movie mv2  = new Movie(myFile,"Justice League", Language.ANCIENT_GREEK, "DC");
		myTestingLib.addMovie(mv2);
		Movie mv3  = new Movie(myFile,"End Game", Language.ANCIENT_GREEK, "Marvel");
		myTestingLib.addMovie(mv3);
		
		TVShow grey = new TVShow("Grey's anatomy", Language.ENGLISH, "ABC");
		myFile = new File("../TVShow/mm1.mp4");

		grey.createAndAddEpisode(myFile, "number 1");
		grey.createAndAddEpisode(myFile, "number 2");
		myTestingLib.addTVShow(grey);
		
		TVShow ruPaul = new TVShow("Rupauls Drag Race", Language.FRENCH, "ABC");
		myFile = new File("../TVShow/mm1.mp4");
		ruPaul.createAndAddEpisode(myFile, "number 1");
		ruPaul.createAndAddEpisode(myFile, "number 2");
		myTestingLib.addTVShow(ruPaul);
		
		TVShow ginnyAndGeorgia = new TVShow("Ginny & Georgia", Language.ENGLISH, "Netflix");
		myFile = new File("../TVShow/mm1.mp4");
		ginnyAndGeorgia.createAndAddEpisode(myFile, "numero 1");
		ginnyAndGeorgia.createAndAddEpisode(myFile, "numero 2");
		myTestingLib.addTVShow(ginnyAndGeorgia);
		
		TVShow starTrek = new TVShow("Star Trek", Language.KLINGON, "Columbia");
		myFile = new File("../TVShow/mm1.mp4");
		starTrek.createAndAddEpisode(myFile, "mI' 1");
		starTrek.createAndAddEpisode(myFile, "mI' 2");
		myTestingLib.addTVShow(starTrek);
		
		
		AndFilters andFilter1 = new AndFilters();
		OrFilters orFilter1 = new OrFilters();
			
		//make a bunch of filter strategies
		StudioFilterStrategy warner = new StudioFilterStrategy("Warner Brothers");
		LanguageFilterStrategy english = new LanguageFilterStrategy(Language.ENGLISH);
		LanguageFilterStrategy french = new LanguageFilterStrategy(Language.FRENCH);
		
		andFilter1.add(warner);
	
		orFilter1.add(english);
		//orFilter1.add(french);
		
		//andFilter1.add(orFilter1);
		
		/*
		 * Generate a watchlist which should have 2 entries in
		 * We verify that the 2 entries present are the correct ones
		 * by adding them to an expected list, and then removing them
		 * from the list as they are retreived
		 */
		Watchable moviesExpected[] = { mv2, mv3};
		
		WatchList ancientGreekWL = myTestingLib.generateWatchList("All Greek To Me", 
				new LanguageFilterStrategy(Language.ANCIENT_GREEK));

		// Ensure that we only have 2 matching elements
		assertEquals(ancientGreekWL.getTotalCount(), 2);
		for( Watchable w : ancientGreekWL) {
			if (moviesExpected[0] == w) {
				moviesExpected[0] = null;
			}
			if (moviesExpected[1] == w) {
				moviesExpected[1] = null;
			}
		}
		/*
		 * Ensure that we found all the expected entries in the watchlist
		 */
		assertEquals(moviesExpected[0], null);
		assertEquals(moviesExpected[1], null);
        // Ensure that we correctly retrieve 0 Spanish movies		
		assertEquals(myTestingLib.generateWatchList("Spanish", new LanguageFilterStrategy(Language.SPANISH)).getTotalCount(),0);
/*
 * White box testing, focus is on ensuring that assertions are correctly
 * generated
 */

		assertEquals(french.filter(mv1), false);
		assertEquals(english.filter(mv1), true);
		assertThrows(AssertionError.class, () -> {
			french.filter((Movie)null);
		});
		assertThrows(AssertionError.class, () -> {
			french.filter((Episode)null);
		});
		assertThrows(AssertionError.class, () -> {
			french.filter((TVShow)null);
		});

		//fail("Not yet implemented");
	}
	


}
