package comp303.assignment4;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class AndOrCombinedTest {

	@Test
	void test() {
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
		Episode ep1Grey = grey.getEpisode(1);
		
		TVShow ruPaul = new TVShow("Rupauls Drag Race", Language.FRENCH, "ABC");
		myFile = new File("../TVShow/mm1.mp4");
		ruPaul.createAndAddEpisode(myFile, "number 1");
		ruPaul.createAndAddEpisode(myFile, "number 2");
		myTestingLib.addTVShow(ruPaul);
		Episode ep1Paul = ruPaul.getEpisode(1);
		
		TVShow ginnyAndGeorgia = new TVShow("Ginny & Georgia", Language.ENGLISH, "Netflix");
		myFile = new File("../TVShow/mm1.mp4");
		ginnyAndGeorgia.createAndAddEpisode(myFile, "numero 1");
		ginnyAndGeorgia.createAndAddEpisode(myFile, "numero 2");
		myTestingLib.addTVShow(ginnyAndGeorgia);
		Episode ep1Ginny = ginnyAndGeorgia.getEpisode(1);
		
		TVShow starTrek = new TVShow("Star Trek", Language.KLINGON, "Columbia");
		myFile = new File("../TVShow/mm1.mp4");
		starTrek.createAndAddEpisode(myFile, "mI' 1");
		starTrek.createAndAddEpisode(myFile, "mI' 2");
		myTestingLib.addTVShow(starTrek);
		Episode ep1Kapla = starTrek.getEpisode(1);		
		
		
		
		AndFilters topLevelFilter = new AndFilters();
			
		TVEpisodeNumFilterStrategy tv1 = new TVEpisodeNumFilterStrategy(1);
		topLevelFilter.add(tv1);
		
		StudioFilterStrategy abc = new StudioFilterStrategy("ABC");
		topLevelFilter.add(abc);
		
		OrFilters orLanguages = new OrFilters();
		LanguageFilterStrategy english = new LanguageFilterStrategy(Language.ENGLISH);
		LanguageFilterStrategy french = new LanguageFilterStrategy(Language.FRENCH);
		orLanguages.add(english);
		orLanguages.add(french);
		
		topLevelFilter.add(orLanguages);
		
		Watchable allFirstEpisodes[] = {ep1Grey, ep1Paul};
		
		//make a bunch of filter strategies
		WatchList tV1FrenchEnglish = myTestingLib.generateWatchList("1st French and English", topLevelFilter);


		// Ensure that we only have 4 matching elements
		assertEquals(tV1FrenchEnglish.getTotalCount(), 2);
		for( Watchable w : tV1FrenchEnglish) {
			for(int i = 0; i < allFirstEpisodes.length; i++) {
				if (allFirstEpisodes[i] == w) {
					allFirstEpisodes[i] = null;
				}
			}
		}
		/*
		 * Ensure that we found all the expected entries in the watchlist
		 */
		for(int i = 0; i < allFirstEpisodes.length; i++) {
			assertEquals(allFirstEpisodes[i], null);
		}
		
	}

}
