package comp303.assignment4;

import java.io.*;

import org.junit.jupiter.api.Test;


class Driver {
	private static final Language English = null;

	public static void main(String[] args) {
	
		Library myLib = new Library();
		File myFile = new File("../Movies/mm1.mp4");
		
		Movie mv1  = new Movie(myFile,"Sound of Music", Language.ENGLISH, "Studio1");
		myLib.addMovie(mv1);
		
		TVShow grey = new TVShow("Grey's anatomy", Language.ENGLISH, "Studio1");
		myFile = new File("../Movies/mm1.mp4");

		grey.createAndAddEpisode(myFile, "number 1");
		grey.createAndAddEpisode(myFile, "number 2");
		myLib.addTVShow(grey);
		
		TVShow warnerEnglish = new TVShow("Some Show", Language.ENGLISH, "Warner Brothers");
		myFile = new File("../Movies/mm1.mp4");
		warnerEnglish.createAndAddEpisode(myFile, "number 1");
		warnerEnglish.createAndAddEpisode(myFile, "number 2");
		myLib.addTVShow(warnerEnglish);
		
		TVShow warnerFrench = new TVShow("Arsene Lupin", Language.FRENCH, "Warner Brothers");
		myFile = new File("../Movies/mm1.mp4");
		warnerFrench.createAndAddEpisode(myFile, "numero 1");
		warnerFrench.createAndAddEpisode(myFile, "numero 2");
		myLib.addTVShow(warnerFrench);
		
		TVShow warnerKlingon = new TVShow("Star Conquest", Language.KLINGON, "Warner Brothers");
		myFile = new File("../Movies/mm1.mp4");
		warnerKlingon.createAndAddEpisode(myFile, "mI' 1");
		warnerKlingon.createAndAddEpisode(myFile, "mI' 2");
		myLib.addTVShow(warnerKlingon);
		
		
		//Show the Q1 use of filters and specifications
		
		
		AndFilters topLevelFilter = new AndFilters();
			
		TVEpisodeNumFilterStrategy tv1 = new TVEpisodeNumFilterStrategy(1);
		topLevelFilter.add(tv1);
		
		StudioFilterStrategy warner = new StudioFilterStrategy("Warner Brothers");
		topLevelFilter.add(warner);
		
		OrFilters orLanguages = new OrFilters();
		LanguageFilterStrategy english = new LanguageFilterStrategy(Language.ENGLISH);
		LanguageFilterStrategy french = new LanguageFilterStrategy(Language.FRENCH);
		orLanguages.add(english);
		orLanguages.add(french);
		
		topLevelFilter.add(orLanguages);
		
		WatchList ass4Q1 = myLib.generateWatchList("first episodes of all TV shows from the WarnerBrothers"
				+" studio that are in either English or French",
				topLevelFilter);
		System.out.println("Yep, we are done with Q1");
		System.out.println(ass4Q1);
		System.out.println();
		

	}
	
	
		
	
}
