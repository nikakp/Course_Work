package comp303.assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();
	
	private String name;
	private Optional<String> email;
	
	private static Library INSTANCE = null;
	
	private Library(String pName) {
		this.name = pName;
		this.email = Optional.empty();
	}
	
	
	/**
	 * @param pName
	 * 			The name to be given to the library
	 * @return
	 * 			Returns the only Library object ever instantiated.
	 * 			If a library instance previous existed, it's name is changed
	 * 			It is never possible to have more than 1 unique Library object
	 */
	public static Library instance(String pName) {
		assert pName != null : "A name must be provided to instance!";
		if (null == INSTANCE) {
			INSTANCE = new Library(pName);
		}
		INSTANCE.name = pName;
		return INSTANCE;
	}
	
	/**
	 * @param pName
	 * 			The name to be given to the library
	 * @param pEmail
	 * 			The E-mail address to be used with the library
	 * @return
	 * 			Returns the only Library object ever instantiated
	 * 			It is never possible to have more than 1 unique Library object
	 */
	public static Library instance(String pName, String pEmail) {
		assert pName != null : "A name must be provided to instance!";
		assert pEmail != null : "A non null E-mail address should be used";
		instance(pName).changeEmail(pEmail);
		return INSTANCE;
	}
	
	/**
	 * @param pName
	 * 			The new name to be used for the Library
	 */
	public void changeName(String pName) {
		assert pName != null : "A name must be provided to changeName!";
		name = pName;
	}
	
	/**
	 * @param pEmail
	 * 			Set the E-mail address of the Library to pEmail
	 */
	public void changeEmail(String pEmail) {
		assert pEmail != null : "An E-mail address must be provided to changeEmail!";
		email = Optional.of(pEmail);
	}
	
	/**
	 * @return
	 * 		If an e-mail address has been set, it it returned. Otherwise
	 * 		an exception is raised.
	 */
	public String getEmail() {
		return email.get();
	}
	
	/**
	 * @return
	 * 		The name of the Library is returned
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 * @pre pMovie!=null
	 */
	public void addMovie(Movie pMovie) {
		assert pMovie != null;
		aMovies.add(pMovie);
	}
	
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 * @pre pList!=null;
	 */
	public void addWatchList(WatchList pList) {
		assert pList != null;
		aWatchLists.add(pList);
		for (Watchable movie : pList) {
			addMovie((Movie) movie);
		}
	}
	
	/**
	 * Adds a TVShow to the library. All Episodes from the list are also added as individual episodes to the library.
	 *
	 * @param pTVShow
	 *            the TVShow to add
	 * @pre pTVShow!=null;
	 */
	public void addTVShow(TVShow pTVShow) {
		assert pTVShow != null;
		aTVShows.add(pTVShow);
		for (Episode episode : pTVShow) {
			aEpisodes.add(episode);
		}
	}
	
	/**
	 * Method to generate a new watchlist based on some filtering mechanism
	 * 
	 * @param pName
	 *            the name of the watchlist to create
	 * @param pGenerationParameters
	 *            the generation parameters
	 * @pre pName!=null && pFilter!=null;
	 */
	public WatchList generateWatchList(String pName, WatchListGenerationInfo pGenerationParameters) {
		assert (pName != null) && (pGenerationParameters != null);
		List<Watchable> items = new ArrayList<>();
		for (TVShow show : aTVShows) {
			if (pGenerationParameters.filter(show)) {
				for (Episode episode : show) {
					if (pGenerationParameters.filter(episode)) {
						items.add(episode);
					}
				}
			}
		}
		for (Movie movie : aMovies) {
			if (pGenerationParameters.filter(movie)) {
				items.add(movie);
			}
		}
		Collections.sort(items, pGenerationParameters);
		WatchList watchlist = new WatchList(pName);
		for (Watchable item : items) {
			watchlist.addWatchable(item);
		}
		return watchlist;
	}
}
