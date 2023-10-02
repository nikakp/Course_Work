package comp303.assignment4;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class AndFiltersTest {

	@Test
	void test() {
		System.out.println("yes");
		Library myTestingLib = new Library();
		File myFile = new File("../Movies/mm1.mp4");

		Movie mv1  = new Movie(myFile,"Sound of Music", Language.ENGLISH, "Warner Brothers");
		myTestingLib.addMovie(mv1);
		Movie mv2  = new Movie(myFile,"Justice League", Language.ANCIENT_GREEK, "DC");
		myTestingLib.addMovie(mv2);
		Movie mv3  = new Movie(myFile,"Silver Speedster", Language.ANCIENT_GREEK, "Marvel");
		myTestingLib.addMovie(mv3);
		Movie mv4  = new Movie(myFile,"Zeus", Language.LATIN, "DC");
		myTestingLib.addMovie(mv4);

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

		//make a bunch of filter strategies
		StudioFilterStrategy dc = new StudioFilterStrategy("DC");

		LanguageFilterStrategy greek = new LanguageFilterStrategy(Language.ANCIENT_GREEK);


		andFilter1.add(greek);
		Watchable moviesExpected[] = { mv2, mv3};

		WatchList ancientGreekWL = myTestingLib.generateWatchList("All Greek To Me", 
				andFilter1);

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
		// Ensure we found all the entries
		assertEquals(moviesExpected[0], null);
		assertEquals(moviesExpected[1], null);
		/*
		 * Ensure that we found all the expected entries in the watchlist
		 * when adding another language
		 */

		andFilter1.add(dc);
		Watchable moviesExpected2[] = { mv2 };
		WatchList greekDCWL = myTestingLib.generateWatchList("Greek DC", 
				andFilter1);

		// Ensure that we only have 1 matching elements
		assertEquals(greekDCWL.getTotalCount(), 1);
		for( Watchable w : greekDCWL) {
			if (moviesExpected2[0] == w) {
				moviesExpected2[0] = null;
			}

		}
		// Ensure we found all the entries
		assertEquals(moviesExpected2[0], null);

		/*
		 * White box testing
		 */


		assertEquals(andFilter1.filter(mv1), false);
		assertEquals(andFilter1.filter(mv2), true);
		assertThrows(AssertionError.class, () -> {
			andFilter1.filter((Movie)null);
		});
		assertThrows(AssertionError.class, () -> {
			andFilter1.filter((Episode)null);
		});
		assertThrows(AssertionError.class, () -> {
			andFilter1.filter((TVShow)null);
		});
		//fail("Not yet implemented");
	}

}
