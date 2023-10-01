package comp303.assignment3;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable> {
	
	private final List<Watchable> aList = new LinkedList<>();
	private String aName;
	private int aNext;
	

	

	/**
	 * Generate a hashcode for a watchlist 
	 * based on the contents of it's list of watchable content
	 * stored in aList
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result;
		result = prime  + 
				((aList == null) ? 0 : aList.hashCode());
		return result;
	}

	
	/**
	 * Implements the equals function method for a watch list
	 * Equality is determined by first ensuring the objects
	 * this and obj are of the same class type. If both
	 * are not null and of type WatchList, then equality
	 * is determined by their aList have the same elements in
	 * the same order
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WatchList other = (WatchList) obj;
		if (aList == null) {
			if (other.aList != null) {
				return false;
			}
		} else if (!aList.equals(other.aList)) {
			return false;
		}
		return true;
	}

	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName
	 *            the name of the list
	 * @pre pName!=null;
	 */
	public WatchList(String pName) {
		assert pName != null;
		aName = pName;
		aNext = 0;
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName
	 *            the new name
	 * @pre pName!=null;
	 */
	public void setName(String pName) {
		assert pName != null;
		aName = pName;
	}
	
	/**
	 * Adds a watchable at the end of this watchlist.
	 * 
	 * @param pWatchable
	 *            the watchable to add
	 * @pre pWatchable!=null;
	 */
	public void addWatchable(Watchable pWatchable) {
		assert pWatchable != null;
		aList.add(pWatchable);
	}
	
	/**
	 * Retrieves and removes the next watchable to watch from this watchlist. Watchables are retrieved in FIFO order.
	 */
	public Watchable removeNext() {
		return aList.remove(0);
	}
	
	/**
	 * @return the total number of valid watchable elements
	 */
	public int getValidCount() {
		int count = 0;
		for (Watchable item : aList) {
			if (item.isValid()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int getTotalCount() {
		return aList.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aList.size() - aNext;
	}
	
	@Override
	public Watchable next() {
		assert getRemainingCount() > 0;
		Watchable next = aList.get(aNext);
		aNext++;
		if (aNext >= aList.size()) {
			aNext = 0;
		}
		return next;
	}
	
	@Override
	public void reset() {
		aNext = 0;
	}
	
	@Override
	public Iterator<Watchable> iterator() {
		return Collections.unmodifiableList(aList).iterator();
	}
}
