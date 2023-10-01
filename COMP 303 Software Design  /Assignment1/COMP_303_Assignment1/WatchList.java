import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

//Nika Prairie
//Student ID: 260843183

public class WatchList<K,V> {

	private String name;
	// A LinkedList is used instead of ArrayList
	// so that the pop operation is O(1) instead of O(n)
	private LinkedList<Movie<K,V>> movieList;

	
	/*
	 * @pre name != null	
	 */
	public WatchList(String n) {
		assert null != n: "Watch list name passed in constructor is null";
		this.name = n;
		this.movieList = new LinkedList<>();

	}

	/*
	 * @pre movie!= null
	 * 
	 * Adds a movie to the end of the existing list of movies
	 * in the watch list.
	 * 
	 * @post confirm that the number of movies has increased by 1
	 */
	
	public void pushMovie(Movie<K,V> mv){
		assert null != mv: "null movie passed in!";
		int i = this.movieList.size();
		this.movieList.add(mv);
		assert (i+1) == this.movieList.size() : "Size of movie list did not increase!";
	}
	
	/*
	 * Note, if 
	 * 
	 * @pre check that movieList is not empty
	 * 
	 * @post The number of movies present decrease by one when movie is poped
	 * 
	 * 
	 */
	
	public void popMovie() {
		assert false == this.movieList.isEmpty() : "WatchList is empty, unable to play movie";
		int i = this.movieList.size();
		this.movieList.remove(0);
		assert (i-1) == this.movieList.size() : "Size of movie list did not decrease!";
	}
	
	/*
	 * @pre check that movieList isnt empty when a watchlist is played
	 * 
	 */

	public boolean playMovie() {
		assert false == this.movieList.isEmpty() : "WatchList is empty, unable to play movie";
		boolean result = this.movieList.get(0).play();
		this.popMovie();
		return result;
	}
	
	/*
	 * @pre name != null
	 * 
	 */

	public void updateName(String name) {
		assert null != name : "Null name passed in to updateName";
		this.name = name;

	}
	
	/*
	 * @post check that movieArray and lovieList have the same length
	 * 
	 */

	public ArrayList<Movie<K,V>> getMovieList(){
		
		ArrayList<Movie<K,V>> movieArray = new ArrayList<>();
		for(Movie<K,V> m : this.movieList) {
			movieArray.add(m);
		}
		assert movieArray.size() == this.movieList.size() : "Unable to generate ArrayList";
		return movieArray;

	}

	
	public int getNumOfValidMovies() {

		int counter = 0;

		for(Movie<K,V> mv: this.movieList) {
			if(mv.getStatus()) {
				counter++;
			}
		}
		return counter;
	}

	/*
	 * @pre newName != null
	 */
	
	public void rename(String newName) {
		assert null != newName : "Null name passed";
		this.name = newName;
	}

	public ArrayList<String> getPublishingStudios() {
		ArrayList<String> publishingStudios = new ArrayList<>();
		HashSet<String> uniquePublishingStudios = new HashSet<>();

		for(Movie<K,V> mv: this.movieList) {
			uniquePublishingStudios.add(mv.getPublishingStudio());
		}

		publishingStudios.addAll(uniquePublishingStudios);

		return publishingStudios;

	}

	public ArrayList<String> getLanguages() {
		ArrayList<String> languages = new ArrayList<>();
		HashSet<String> uniqueLanguages = new HashSet<>();


		for(Movie<K,V> mv: this.movieList) {
			uniqueLanguages.add(mv.getLanguage());
		}

		languages.addAll(uniqueLanguages);

		return languages;

	}


}

