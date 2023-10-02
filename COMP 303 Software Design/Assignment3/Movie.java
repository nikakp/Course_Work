package comp303.assignment3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single movie, with at least a title, language, and publishing studio. Each movie is identified by its
 * path on the file system.
 */

public class Movie implements Sequenceable<Movie> {
	// Store all instances of the Movie type based on their
	// title. Only a single instance for a given title is possible
	private static Map<String, Movie> allMovies = new HashMap<>();
	private final File aPath;
	
	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	
	private Movie prequel;
	private Movie sequel;
	
	private Map<String, String> aTags = new HashMap<>();
	
	/**
	 * Creates a movie from the file path. Callers must also provide required metadata about the movie.
	 *
	 * @param pPath
	 *            location of the movie on the file system.
	 * @param pTitle
	 *            official title of the movie in its original language
	 * @param pLanguage
	 *            language of the movie
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pPath!=null && pTitle!=null && pLanguage!=null && pStudio!=null
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	private Movie(File pPath, String pTitle, Language pLanguage, String pStudio) {
		assert pPath != null && pTitle != null && pLanguage != null && pStudio != null;
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath; // ok because File is immutable.
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
	}
	
	
	/**
	 * @param pPath
	 * 			Path of the movie file. If a movie with pTitle has already been added
	 *          calling get will not change the file path
	 * @param pTitle
	 * 			The title of the movie. There can only be 1 movie object with a given
	 *   		title. i.e. you cannot have 2 distinct movie objects with the same
	 *   		title 
	 * @param pLanguage
	 * 			The language of the movie. If there is already a movie object with
	 * 			pTitle, its language is not changed
	 * @param pStudio
	 * 			The Studio of the movie. If there is already a movie object with
	 * 			pStudio, its studio is not changed
	 * 			
	 * @return
	 * 			The object with pTitle. If no movie already exists with pTitle,
	 * 			one is created. Otherwise the existing object is returned
	 */
	public static Movie get(File pPath, String pTitle, Language pLanguage, String pStudio) {
		assert pPath != null && pTitle != null && pLanguage != null && pStudio != null;
		Movie theMovie = Movie.allMovies.get(pTitle);
		if (null == theMovie) {
			theMovie = new Movie(pPath, pTitle, pLanguage, pStudio);
			Movie.allMovies.put(pTitle, theMovie);
		}
		return theMovie;
	}
	
	@Override
	public void watch() {
		// Just a stub.
		// We don't expect you to implement a full media player!
		System.out.println("Now playing " + aTitle);
	}
	
	@Override
	public boolean isValid() {
		return aPath.isFile() && aPath.canRead();
	}
	
	//@Override
	public String getTitle() {
		return aTitle;
	}
	
	//@Override
	public Language getLanguage() {
		return aLanguage;
	}
	
	//@Override
	public String getStudio() {
		return aStudio;
	}
	
	//@Override
	public String setInfo(String pKey, String pValue) {
		assert pKey != null && !pKey.isBlank();
		if (pValue == null) {
			return aTags.remove(pKey);
		}
		else {
			return aTags.put(pKey, pValue);
		}
	}
	
	//@Override
	public boolean hasInfo(String pKey) {
		assert pKey != null && !pKey.isBlank();
		return aTags.containsKey(pKey);
	}
	
	//@Override
	public String getInfo(String pKey) {
		assert hasInfo(pKey);
		return aTags.get(pKey);
	}
	
	@Override
	public boolean hasPrevious() {
		return prequel != null;
	}
	
	@Override
	public boolean hasNext() {
		return sequel != null;
	}
	
	@Override
	public Movie getPrevious() {
		return prequel;
	}
	
	@Override
	public Movie getNext() {
		return sequel;
	}
	
	/**
	 * Sets the previous Movie in the series, and updates the prequel and sequel information of all related movies
	 * involved.
	 *
	 * @param pMovie
	 *            the Movie object to set as previous
	 * @pre pMovie != null
	 */
	public void setPrevious(Movie pMovie) {
		assert pMovie != null;
		if (prequel != null) {
			prequel.sequel = null;
		}
		if (pMovie.sequel != null) {
			pMovie.sequel.prequel = null;
		}
		prequel = pMovie;
		pMovie.sequel = this;
	}
}
