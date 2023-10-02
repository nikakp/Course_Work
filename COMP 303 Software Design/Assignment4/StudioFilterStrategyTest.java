package comp303.assignment4;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class StudioFilterStrategyTest {

	@Test
	void test() {
		System.out.println("yes");
		Library myTestingLib = new Library();
		File myFile = new File("../Movies/mm1.mp4");
		
		Movie mv1  = new Movie(myFile,"Sound of Music", Language.ENGLISH, "Warner Brothers");
		myTestingLib.addMovie(mv1);
		Movie mv2  = new Movie(myFile,"Justice League", Language.ANCIENT_GREEK, "DC");
		myTestingLib.addMovie(mv2);
		Movie mv3  = new Movie(myFile,"Wonder Woman", Language.ANCIENT_GREEK, "DC");
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
		
		/*
		AndFilters andFilter1 = new AndFilters();
		OrFilters orFilter1 = new OrFilters();
			
		//make a bunch of filter strategies
		TVEpisodeNumFilterStrategy tv1 = new TVEpisodeNumFilterStrategy(0);
		StudioFilterStrategy warner = new StudioFilterStrategy("Warner Brothers");
		StudioFilterStrategy netflix = new StudioFilterStrategy("Netflix");
		StudioFilterStrategy columbia = new StudioFilterStrategy("Columbia");
		LanguageFilterStrategy english = new LanguageFilterStrategy(Language.ENGLISH);
		LanguageFilterStrategy french = new LanguageFilterStrategy(Language.FRENCH);
		
		andFilter1.add(warner);
	
		orFilter1.add(english);
		//orFilter1.add(french);
		
		//andFilter1.add(orFilter1);
		
		WatchList andTestWatchList = myTestingLib.generateWatchList("AND Test WatchList, Warner Studio",andFilter1);
		assertEquals(andTestWatchList.getTotalCount(), 1);
        assertEquals(mv1, andTestWatchList.removeNext());
		WatchList orTestWatchList = myTestingLib.generateWatchList("OR Test WatchList, Warner Studio",orFilter1);
		assertEquals(orTestWatchList.getTotalCount(), 5);
		orFilter1.add(netflix);
		assertEquals(myTestingLib.generateWatchList("French", new LanguageFilterStrategy(Language.FRENCH)).getTotalCount(),2);
		assertEquals(myTestingLib.generateWatchList("Spanish", new LanguageFilterStrategy(Language.SPANISH)).getTotalCount(),0);
		assertEquals(myTestingLib.generateWatchList("Warner", new StudioFilterStrategy("Warner Brothers")).getTotalCount(),1);
		assertEquals(myTestingLib.generateWatchList("CBC", new StudioFilterStrategy("CBC")).getTotalCount(),0);
		*/
		StudioFilterStrategy imperial = new StudioFilterStrategy("Imperial");
		assertEquals(myTestingLib.generateWatchList("Imperial", imperial).getTotalCount(),0);
		/*
		 * Generate a watchlist which should have 2 entries in
		 * We verify that the 2 entries present are the correct ones
		 * by adding them to an expected list, and then removing them
		 * from the list as they are retreived
		 */
		Watchable moviesExpected[] = { mv2, mv3};
		
		WatchList DCWL = myTestingLib.generateWatchList("All Greek To Me", 
				new StudioFilterStrategy("DC"));

		// Ensure that we only have 2 matching elements
		assertEquals(DCWL.getTotalCount(), 2);
		for( Watchable w : DCWL) {
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
		
		/*
		 * White box testing
		 */
		StudioFilterStrategy warner = new StudioFilterStrategy("Warner Brothers");
		StudioFilterStrategy columbia = new StudioFilterStrategy("Columbia");
		
		assertEquals(columbia.filter(mv1), false);
		assertEquals(warner.filter(mv1), true);
		assertThrows(AssertionError.class, () -> {
			warner.filter((Movie)null);
		});
		assertThrows(AssertionError.class, () -> {
			warner.filter((Episode)null);
		});
		assertThrows(AssertionError.class, () -> {
			warner.filter((TVShow)null);
		});
		//fail("Not yet implemented");
	}

}
