package comp303.assignment4;

import java.io.File;
import java.util.*;

/**
 * Represents a single TV show, with at least a title, language, and publishing studio. Each TVShow aggregates episodes.
 */
public class TVShow implements Watchable, Bingeable<Episode> {
	
	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aInfo;
	
	private List<Episode> aEpisodes = new ArrayList<>();
	private int aNextToWatch;
	
	private Episode aPrototypeEpisode = null;

	
	/**
	 * Creates a TVShow with required metadata about the show.
	 *
	 * @param pTitle
	 *            official title of the TVShow
	 * @param pLanguage
	 *            language of the movie, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pTitle!=null && pLanguage!=null && pStudio!=null
	 */
	public TVShow(String pTitle, Language pLanguage, String pStudio) {
		assert pTitle != null && pLanguage != null && pStudio != null;
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
		aNextToWatch = 0;
		aInfo = new HashMap<>();
		
	}
	
	/**
	 * Sets the EpisodePrototype based on a TVShow provided to the method
	 * The aPrototypeEpisode field is updated here
	 * * @param pTVProto
	 *            TVShow which the episode prototype will be based off of
	 */
	
	public void setEpisodePrototype(TVShow pTVProto) {
		Episode ep = pTVProto.getEpisode(1);
		
		aPrototypeEpisode = (Episode) ep.clone();
	}
	
	/**
	 * Creates a new Episode for this TV show using the first episode associated
	 * with the TVShow passed to the call setEpisodePrototype. All fields of
	 * the new episode are based on the prototype with the exception of 
	 * the episode number which is based on the number of episodes already in the 
	 * list
	 * The episode is added to the end of the list
	 * 
     * @pre prototype episode has been set
	 */
	public void createAndAddEpisode() {
		//Episode ep = aPrototypeEpisode.clone();
		int nb = aEpisodes.size();
		Episode episode = new Episode(aPrototypeEpisode.getaPath(), 
				this, aPrototypeEpisode.getTitle(), nb);
		aEpisodes.add(episode);
	}
	
	/**
	 * Creates a new Episode for this TV show, and adds it the end of the episode list.
	 * 
	 * The prototype episode set via use setEpisodePrototype is used to set all
	 * fields except the file path, and episode number. Episode number is set
	 * based on the number of episodes present in the list
	 * 
	 * @param pPath
	 *            the path of the file that contains the video of the episode
	 *            
	 * @pre pPath != null && prototype episode has been set;
	 */
	public void createAndAddEpisode(File pPath) {
		assert pPath != null;
		int nb = aEpisodes.size();
		Episode episode = new Episode(pPath, this, 
				aPrototypeEpisode.getTitle(), nb);
		aEpisodes.add(episode);
	}
	
	@Override
	public void watch() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				episode.watch();
			}
		}
	}
	
	public String getTitle() {
		return aTitle;
	}
	
	public Language getLanguage() {
		return aLanguage;
	}
	
	public String getStudio() {
		return aStudio;
	}
	
	public String getInfo(String pKey) {
		return aInfo.get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		return aInfo.containsKey(pKey);
	}
	
	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aInfo.remove(pKey);
		}
		else {
			return aInfo.put(pKey, pValue);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return true if the TV show contains at least one valid episode
	 */
	@Override
	public boolean isValid() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates a new Episode for this TV show, and adds it the end of the episode list.
	 * 
	 * @param pPath
	 *            the path of the file that contains the video of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @pre pPath != null && pTitle != null;
	 */
	public void createAndAddEpisode(File pPath, String pTitle) {
		assert pPath != null && pTitle != null;
		int nb = aEpisodes.size();
		Episode episode = new Episode(pPath, this, pTitle, nb);
		aEpisodes.add(episode);
	}
		
	
	/**
	 * Returns the Episode of a given number. Episode numbers are 1-based: the first episode is 1, not 0.
	 *
	 * @param pNumber
	 *            the number whose Episode is to be returned
	 * @return the Episode of the given number
	 * @pre there is an episode for the given number
	 */
	public Episode getEpisode(int pNumber) {
		assert aEpisodes.size() >= pNumber;
		return aEpisodes.get(pNumber - 1);
	}
	
	@Override
	public int getTotalCount() {
		return aEpisodes.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aEpisodes.size() - aNextToWatch;
	}
	
	@Override
	public Episode next() {
		assert getRemainingCount() > 0;
		Episode nextEpisode = aEpisodes.get(aNextToWatch);
		aNextToWatch++;
		if (aNextToWatch >= aEpisodes.size()) {
			aNextToWatch = 0;
		}
		return nextEpisode;
	}
	
	@Override
	public void reset() {
		aNextToWatch = 0;
	}
	
	@Override
	public Iterator<Episode> iterator() {
		return aEpisodes.iterator();
	}
}