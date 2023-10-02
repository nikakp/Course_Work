import java.io.*;
import java.util.Hashtable;

//Nika Prairie
//Student ID: 260843183


public class Movie<K,V> {

	private final String title;
	private final Format format;
	private final String filePath;
	private final String language;
	private final String publishingStudio;
	private Hashtable<K,V> customInfo;


	/*
	 * @pre title != null, language != null, publishingStudio != null, filePath != null
	 * check that the filepath is long enough to even be a valid file
	 * check it has proper format
	 */
	public Movie(String title, String language, String publishingStudio, String filePath) {

		assert title != null : "Null title passed in";

		assert language != null : "Null language passed in";
		assert publishingStudio != null : "Null publishing Studio passed in";
		assert filePath != null : "Null filePath passed in";

		this.title = title;
		this.language = language;
		this.publishingStudio = publishingStudio;
		this.filePath = filePath;

		String[] formatStrings = {".MP4", ".MOV", ".WMV", ".AVI", ".FLV", ".MKV"};
		boolean formatFound = false;
		Format[] formatValues = new Format[]{Format.MP4, Format.MOV, 
				Format.WMV, Format.AVI, 
				Format.FLV, Format.MKV};
		assert filePath.length() < formatStrings[0].length() : "File name is too short to include format";
		// set the interim format to a default value, we won't
		// use it if a valid format is found
		Format interimFormat = Format.MP4;
		for( int i = 0; i <= formatStrings.length - 1; i++)	{
			if ( filePath.endsWith(formatStrings[i])) {
				formatFound = true;
				interimFormat = formatValues[i];
				break;
			}
		} 
		// The compiler complains about the format potentially not
		// being set if it's stored above, so we 'trick' it here
		// even though the assert will be hit in the event
		// that a format was not found
		this.format = interimFormat;
		assert formatFound : "Invalid format used for the movie";

		this.customInfo = new Hashtable<>(2);
	}

	/*
	 * @pre k != null, v != null	
	 * 
	 * Either adds a new key value pair, or updates an 
	 * existing entry with the same key to the new value
	 */

	public void updateCustomInfo(K k, V v) {
		assert k != null : "Need a non null k";
		assert v != null : "Need a non null v";
		this.customInfo.put(k, v);

	}

	/*
	 * Determines if the file with the movie content
	 * is actually on disk
	 */
	public boolean getStatus() {

		File tmp = new File(this.filePath);
		return tmp.exists();
	}

	public Format getFormat() {
		return this.format;
	}

	/*
	 * Plays a movie if it's active in the file system
	 * return true upon success, false otherwise
	 */
	public boolean play() {
		// Here we would actually use getStatus
		// to determine if the file is present on disk
		// and then invoke the media player.
		// if the file wasn't present on disk we would
		// return false
		// Otherwise we would return true or false depending
		// on the outcome of using the media player's 
		// function
		return getStatus();
	}

	public String getPublishingStudio() {

		return this.publishingStudio;

	}

	public String getLanguage() {

		return this.language;

	}

	public String getTitle() {

		return this.title;
	}


}
