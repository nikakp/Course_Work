// Nika Prairie
// ID: 260843183
package FinalProject_Template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
	// num of entries to the table
	private int numEntries;
	// num of buckets 
	private int numBuckets;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<HashPair<K,V>>> buckets; 

	// constructor
	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS

		// initialize number of entries to 0
		this.numEntries = 0;

		//initialize ArrayList with constructor input
		if (initialCapacity == 0 ) {
			this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(1);
			this.numBuckets = 1;
		} else {
			this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(initialCapacity);
			this.numBuckets = initialCapacity;
		}

		for(int i = 0; i < initialCapacity; i++) {
			buckets.add(i, new LinkedList<HashPair<K,V>>());
		}

		//ADD YOUR CODE ABOVE THIS
	}


	public int size() {
		return this.numEntries;
	}

	public boolean isEmpty() {
		return this.numEntries == 0;
	}

	public int numBuckets() {
		return this.numBuckets;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList<HashPair<K,V>>> getBuckets(){
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.numBuckets;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {

		// get hash value with key input 
		int hashValue = hashFunction(key);

		// bucket is empty where the hash should be going
		if(this.buckets.get(hashValue).size() == 0) {
			HashPair<K,V> pair = new HashPair<K,V>(key, value);
			this.buckets.get(hashValue).add(pair);
			this.numEntries++;
			//check if we need to rehash due to load factor
			if(((float)(this.numEntries))/((float)this.numBuckets) > MAX_LOAD_FACTOR) {
				//update buckets
				this.rehash();
			}
			return null;
		}

		//bucket has elements
		if(this.buckets.get(hashValue).size() > 0) {
			for(HashPair<K,V> pair: this.buckets.get(hashValue)) {
				if (pair.getKey().equals(key)) {
					V oldValue = pair.getValue();
					pair.setValue(value);
					// replace value with new one and return old value
					return oldValue;
				}
			}

			this.buckets.get(hashValue).add(new HashPair<K,V>(key,value));

			this.numEntries++;
			if(((float)(this.numEntries))/((float)this.numBuckets) > MAX_LOAD_FACTOR) {
				//update buckets
				this.rehash();
			}
			return null;

		}
		return null;
	}



	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {

		LinkedList<HashPair<K,V>> list = this.buckets.get(hashFunction(key));
		for (HashPair<K,V> existingPair : list) {
			if (existingPair.getKey().equals(key)){
				// There is already an entry with this key
				return existingPair.getValue();
			}
		}
		return null;

	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1) 
	 */
	public V remove(K key) {

		int hashValue = hashFunction(key);

		Iterator<HashPair<K,V>> iterator = this.buckets.get(hashValue).iterator();

		HashPair<K,V> existingPair;

		while(iterator.hasNext()) {
			existingPair = iterator.next();
			if(existingPair.getKey().equals(key)) {
				V value = existingPair.getValue();
				iterator.remove();
				this.numEntries--;
				return value;
			}
		}
		return null;
	}


	/** 
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		ArrayList<LinkedList<HashPair<K,V>>> oldBuckets = this.buckets;
		this.numBuckets = this.numBuckets * 2;
		this.buckets = new ArrayList<>(this.numBuckets);
		this.numEntries = 0;
		for(int i =0; i < this.numBuckets; i++) {
			this.buckets.add(new LinkedList<HashPair<K,V>>());
		}
		// Loop through all existing elements in the list and add them to the new 
		// buckets
		for(LinkedList<HashPair<K,V>> ll : oldBuckets) {
			// Go through each buck to see if there are 
			// any items to be added to the new hash
			if (!ll.isEmpty() ) {
				for (HashPair<K,V> existingPair : ll) {
					int hashValue = this.hashFunction(existingPair.getKey());
					this.buckets.get(hashValue).add(existingPair);
					this.numEntries++;
				}
			}
		}

	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> keys() {

		// create ArrayList that will contain the keys
		ArrayList<K> keyArray = new ArrayList<K>(); 
		for(HashPair<K,V> pair : this) {
			keyArray.add(pair.getKey());
		}
		return keyArray;


	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> values() {

		MyHashTable<V,V> uniqueValuesTable = new MyHashTable<V,V>(this.numEntries*4/3);

		for(HashPair<K,V> item: this) {
			uniqueValuesTable.put(item.getValue(), item.getValue());
		}

		return uniqueValuesTable.keys();

	}


	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2), where n is the number 
	 * of pairs in the map. 
	 */
	public static <K, V extends Comparable<V>> ArrayList<K> slowSort (MyHashTable<K, V> results) {
		ArrayList<K> sortedResults = new ArrayList<>();
		for (HashPair<K, V> entry : results) {
			V element = entry.getValue();
			K toAdd = entry.getKey();
			int i = sortedResults.size() - 1;
			V toCompare = null;
			while (i >= 0) {
				toCompare = results.get(sortedResults.get(i));
				if (element.compareTo(toCompare) <= 0 )
					break;
				i--;
			}
			sortedResults.add(i+1, toAdd);
		}
		return sortedResults;
	}
	
	public static <K, V extends Comparable<V>> ArrayList<K> fastSort(MyHashTable<K,V> results) {

		ArrayList<HashPair<K,V>> hashPairArrayList = results.hashPairArrayList();

		ArrayList<HashPair<K,V>> sorted = quickSort(hashPairArrayList);

		ArrayList<K> sortedKeys = new ArrayList<K>(); // ArrayList to return 

		for(HashPair<K,V> temp: sorted) {

			sortedKeys.add(temp.getKey());
		}

		return sortedKeys;

	}

	private static <K, V extends Comparable<V>>  ArrayList<HashPair<K,V>> quickSort(ArrayList<HashPair<K,V>> toSort){


		ArrayList<HashPair<K,V>> smaller = new ArrayList<HashPair<K,V>>();
		ArrayList<HashPair<K,V>> greater = new ArrayList<HashPair<K,V>>();

		if(toSort.isEmpty()) {

			return toSort;

		}

		HashPair<K,V> pivot = toSort.get(toSort.size()-1); //use last hashpair in hashtable for the pivot

		for(int i = 0; i <toSort.size()-1; i++) {
			HashPair<K,V> temp = toSort.get(i);
			if(temp.getValue().compareTo(pivot.getValue()) >= 0) {
				smaller.add(temp);
			} else {
				greater.add(temp);
			}


		}

		smaller = quickSort(smaller);
		greater = quickSort(greater);

		smaller.add(pivot);
		smaller.addAll(greater);

		return smaller;

	}

	

	private ArrayList<HashPair<K,V>> hashPairArrayList(){

		ArrayList<HashPair<K,V>> hashPairs = new ArrayList<HashPair<K,V>>(this.numEntries);

		for(LinkedList<HashPair<K,V>> linkedList: this.buckets) {

			for(HashPair<K,V> temp: linkedList) {

				hashPairs.add(temp);
			}
		}

		return hashPairs;


	}

	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}   

	private class MyHashIterator implements Iterator<HashPair<K,V>> {

		int bucket;
		int node;
		int elemNumber;


		/**
		 * Expected average runtime is O(m) where m is the number of buckets
		 */
		private MyHashIterator() {
			//initialize  variables to 0
			bucket = 0;
			node = 0;
			elemNumber = 0;
		}

		@Override
		/**
		 * Expected average runtime is O(1)
		 */
		public boolean hasNext() {
			return elemNumber < numEntries; 
		}

		@Override
		/**
		 * Expected average runtime is O(1)
		 */
		public HashPair<K,V> next() {
			// Have we already gone through all the elements in the bucket?
			if(node >= buckets.get(bucket).size()) {
				// Go to the next bucket
				bucket++;
				// Find a bucket that is not empty
				while(buckets.get(bucket).isEmpty()) {
					bucket++;
				}
				// Start looking at the first element of the linked list
				node = 0;
			}

			HashPair<K,V> pair = buckets.get(bucket).get(node++);

			elemNumber++;

			return pair;



		}

	}
}