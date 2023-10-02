package comp303.assignment4;

import java.util.ArrayList;
import java.util.List;


public class AndFilters implements WatchListFilter {

	private List<WatchListFilter> aFilters = new ArrayList<>(); //keeps a list of what filters are present for the AND type
	
	
	//goes through the watchable type and sees if it meets the filter criteria 
	@Override
	public boolean filter(Movie pMovie) {
		for (WatchListFilter f : aFilters) {
			if (!f.filter(pMovie)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean filter(TVShow pTVShow) {
		
		for (WatchListFilter f : aFilters) {
			if (!f.filter(pTVShow)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean filter(Episode pEpisode) {
		
		for (WatchListFilter f : aFilters) {
			if (!f.filter(pEpisode)) {
				return false;
			}
		}
		return true;
	}
	
	//add another filter type to the AND filter collection
	public void add(WatchListFilter filter) {
		assert filter != null;
		aFilters.add(filter);
	}
	

}
