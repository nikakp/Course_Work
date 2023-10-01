// Nika Prairie
// ID: 260843183
package FinalProject_Template;

import java.util.ArrayList;

public class Twitter {

	//ADD YOUR CODE BELOW HERE


	//myHashTable for stopWords, values are 0 for all, stopWords are keys

	//myHashTable for authors, values are tweets, authros are keys

	//myHashTable for dates, Year-Month-Day, values are arraylist of tweets

	private MyHashTable<String,Integer> stopHash;
	private MyHashTable<String,Tweet> authorsHash;
	private MyHashTable<String, ArrayList<Tweet>> dateHash;
	private ArrayList<Tweet> savedTweets;

	//ADD CODE ABOVE HERE 

	// O(n+m) where n is the number of tweets, and m the number of stopWords
	public Twitter(ArrayList<Tweet> tweets, ArrayList<String> stopWords) {

		this.stopHash = new MyHashTable<String,Integer>((stopWords.size()+1)*4/3);
		for(String stopWord: stopWords) {

			this.stopHash.put(stopWord.toLowerCase(), new Integer(0));
		}
		this.savedTweets = new ArrayList<Tweet>(tweets.size());
		this.savedTweets.addAll(tweets);
		this.authorsHash = new MyHashTable<String,Tweet>((tweets.size()+1)*4/3);
		this.dateHash = new MyHashTable<String,ArrayList<Tweet>>((tweets.size()+1)*4/3);

		for(Tweet aTweet: tweets) {
			addTweet(aTweet);
		}
	}


	/**
	 * Add Tweet t to this Twitter
	 * O(1)
	 */
	public void addTweet(Tweet t) {

		if(t == null) {
			return;
		}


		//add Tweet to ArrayList of Tweets
		// we'll need this later for the trending work
		this.savedTweets.add(t);

		String authorName = t.getAuthor();
		Tweet latestAuthorTweet = this.authorsHash.get(authorName);

		if(latestAuthorTweet == null) {
			// If the author doesn't yet have tweets in the hash
			// add this tweet as the first element for the author
			this.authorsHash.put(authorName, t);
		} else if(latestAuthorTweet.compareTo(t)  < 0) {
			// The author's previous tweet is older than
			// this one, so do a put to replace the old tweet
			// with this one
			this.authorsHash.put(authorName, t);
		}
		// We only need the YYYY-MM-DD portion of the date string
		String dateOnly = t.getDateAndTime().substring(0, 10);

		// Are there already tweets on this date?
		ArrayList<Tweet> dateTweetList = this.dateHash.get(dateOnly);;
		if(dateTweetList == null) {
//			No tweets on this date, so add the first Array List
			dateTweetList = new ArrayList<Tweet>();
			dateTweetList.add(t);
			this.dateHash.put(dateOnly, dateTweetList);
		} else {
			// there were already tweets for this date
			// so add t to the list
			dateTweetList.add(t);
		}

	}


	/**
	 * Search this Twitter for the latest Tweet of a given author.
	 * If there are no tweets from the given author, then the 
	 * method returns null. 
	 * O(1)  
	 */
	public Tweet latestTweetByAuthor(String author) {
		return this.authorsHash.get(author);
	}


	/**
	 * Search this Twitter for Tweets by `date' and return an 
	 * ArrayList of all such Tweets. If there are no tweets on 
	 * the given date, then the method returns null.
	 * O(1)
	 */
	public ArrayList<Tweet> tweetsByDate(String date) {
		return this.dateHash.get(date);
	}

	/**
	 * Returns an ArrayList of words (that are not stop words!) that
	 * appear in the tweets. The words should be ordered from most 
	 * frequent to least frequent by counting in how many tweet messages
	 * the words appear. Note that if a word appears more than once
	 * in the same tweet, it should be counted only once. 
	 */
	public ArrayList<String> trendingTopics() {
		
		// Let's assume that, on average, there are 20 words per tweet
		MyHashTable<String, Integer> wordHash = new MyHashTable<String,Integer>(this.savedTweets.size()*20);
		
		for(Tweet aTweet: this.savedTweets) {
			// We need to ensure that all word comparison's are done using
			// lowercase so that mixed cases are handled correctly
			ArrayList<String> words = getWords(aTweet.getMessage().toLowerCase());
			
			for(String word: words) {
				// Is the word a stop word, if not increment the frequency for
				// the word
				if (null == this.stopHash.get(word)) {
					// The word is not in the stop list 
					Integer wordFrequency = wordHash.get(word);
					if (wordFrequency == null) {
						// We aren't yet tracking this word, add it
						// with a count of 0
						wordHash.put(word, new Integer(0));
					} else {
						// The word is already in
						// Get the existing number of times it's been seen
						// then increment it by 1 update the entry by doing a
						// put
						wordHash.put(word, new Integer(wordFrequency.intValue()+1));
					}
				}
			}
		}
		return MyHashTable.fastSort(wordHash);

		
		///grab a tweet, get message, get word, check if its a stop list word , add to hashtable
		//get value, 
		
	}
	/**
	 * An helper method you can use to obtain an ArrayList of words from a 
	 * String, separating them based on apostrophes and space characters. 
	 * All character that are not letters from the English alphabet are ignored. 
	 */
	private static ArrayList<String> getWords(String msg) {
		msg = msg.replace('\'', ' ');
		String[] words = msg.split(" ");
		ArrayList<String> wordsList = new ArrayList<String>(words.length);
		for (int i=0; i<words.length; i++) {
			String w = "";
			for (int j=0; j< words[i].length(); j++) {
				char c = words[i].charAt(j);
				if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
					w += c;

			}
			wordsList.add(w);
		}
		return wordsList;
	}



}
