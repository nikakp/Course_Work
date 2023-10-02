import java.util.TreeMap;

//Nika Prairie
//Student ID: 260843183

public class Library<K,V> {

	private TreeMap<String,Movie<K,V>> movies;
	private TreeMap<String,WatchList<K,V>> watchlists;


	public Library() {
		this.movies = new TreeMap<>();
		this.watchlists = new TreeMap<>();

	}

	/*
	 * @pre title != null, language != null, publishingStudio != null, filePath != null
	 *      check that the movie being added does'nt already exist
	 *
	 * @post ensure that the number of movie entries increased by 1
	 */
	public void addMovie(String title, String language, 
			             String publishingStudio, String filePath) { 
		assert null != title: "title is null";
		assert null != language: "language is null";
		assert null != publishingStudio: "publishingStudio is null";
		assert null != filePath: "filePath is null";
		

		Movie<K,V> alreadyPresent = this.movies.get(title);
		assert null != alreadyPresent : "The movie titled "+title+" is already present in the library";
		Movie<K,V> mv = new Movie<>(title, language, publishingStudio, filePath);
		int i = this.movies.size();
		this.movies.put(title, mv);
		assert (i+1) == this.movies.size() : "Size of movie library did not increase!";
	}

	/*
	 * @pre watchListName != null
	 *      watchList with the same name doesn't exist
	 * 			
	 * @post check that size of watchList has increased by 1
	 */
	public void createWatchList(String watchListName) {

		assert null != watchListName: "null watch list name";
		
		WatchList<K,V> alreadyPresent = watchlists.get(watchListName);
		assert null == alreadyPresent : "A watch list name "+watchListName+" already exists";

		WatchList<K,V> wl = new WatchList<>(watchListName);
		int len = this.watchlists.size();
		this.watchlists.put(watchListName, wl);
		assert (len+1) == this.watchlists.size(): "Watch list did not increase in size!";

	}

	
	/*
	 * @pre watchListName != null, title != null, movie != null, wl != null
	 *      watch list with the name provided exists
	 *      movie with the name provided exists
	 */
	public void addMovieToWatchList(String watchListName, String title) {
		
		assert null != watchListName: "null watch list name";
		assert null != title : " null movie title";
		
		// Check that the watchList provided exists
		WatchList<K,V> wl = watchlists.get(watchListName);
		assert wl != null: "No watch list named "+watchListName;

		Movie<K,V> movie = movies.get(title);
		assert movie != null : "No movie named "+title+" in watch list "+watchListName;
		// Find the watchlist, find the movie, and then add the movie to it
		wl.pushMovie(movie);
	}

	/*
	 * @pre watchListName != null
	 *      a watchList with the provided name exists
	 */

	public void playWatchList(String watchListName) {
		assert null != watchListName: "null watch list name";
		
		WatchList<K,V> wl = this.watchlists.get(watchListName);
		assert null != wl: "Watchlist named "+watchListName+" doesn't exist";
		
		wl.playMovie();

	}
	
	/*
	 * @pre oldName != null, newName != null, wl != null
	 *      the watchlist we want to rename is present in the watchlist
	 *      
	 */
	
	public void renameWatchList(String oldName, String newName) {
		assert null != oldName: "null oldName";
		assert null != newName: "null newName";
		
		WatchList<K,V> wl = this.watchlists.get(oldName);
		assert null != wl: "Watchlist named "+oldName+" doesn't exist";
		
		wl.rename(newName);
		this.watchlists.remove(oldName);
		this.watchlists.put(newName, wl);
	}

}



