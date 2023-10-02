package comp303.assignment4;

import java.util.ArrayList;
import java.util.List;


public class OrFilters implements WatchListFilter {

	private List<WatchListFilter> aFilters = new ArrayList<>();
	
	
	//goes through the watchable type and sees if it meets the filter criteria 
	@Override
	public boolean filter(Movie pMovie) {
		for (WatchListFilter f : aFilters) {
			if (f.filter(pMovie)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean filter(TVShow pTVShow) {
		// TODO Auto-generated method stub
		for (WatchListFilter f : aFilters) {
			if (f.filter(pTVShow)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean filter(Episode pEpisode) {
		// TODO Auto-generated method stub
		for (WatchListFilter f : aFilters) {
			if (f.filter(pEpisode)) {
				return true;
			}
		}
		return false;
	}
	
	//add another filter type to the OR filter collection
	public void add(WatchListFilter filter) {
		assert filter != null;
		aFilters.add(filter);
	}


}
