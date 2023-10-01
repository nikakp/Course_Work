/*
 * Nika Prairie
 * Student number: 260843183
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException; 


public class CatTree implements Iterable<CatInfo>{
	public CatNode root;

	@Override
	public String toString() {
		return root.toString();
	}

	public CatTree(CatInfo c) {
		this.root = new CatNode(c);
	}

	private CatTree(CatNode c) {
		this.root = c;
	}

	public void printTree() {
		this.root.printTree();

	}

	public void addCat(CatInfo c)
	{
		this.root = root.addCat(new CatNode(c));
	}

	public void removeCat(CatInfo c)
	{
		this.root = root.removeCat(c);
	}

	public int mostSenior()
	{
		return root.mostSenior();
	}

	public int fluffiest() {
		return root.fluffiest();
	}

	public CatInfo fluffiestFromMonth(int month) {
		return root.fluffiestFromMonth(month);
	}

	public int hiredFromMonths(int monthMin, int monthMax) {
		return root.hiredFromMonths(monthMin, monthMax);
	}

	public int[] costPlanning(int nbMonths) {
		return root.costPlanning(nbMonths);
	}

	public Iterator<CatInfo> iterator()
	{

		return new CatTreeIterator();
	}


	class CatNode {

		CatInfo data;
		CatNode senior;
		CatNode same;
		CatNode junior;

		public CatNode(CatInfo data) {
			this.data = data;
			this.senior = null;
			this.same = null;
			this.junior = null;
		}

		public String toString() {
			String result = this.data.toString() + "\n";
			if (this.senior != null) {
				result += "more senior " + this.data.toString() + " :\n";
				result += this.senior.toString();
			}
			if (this.same != null) {
				result += "same seniority " + this.data.toString() + " :\n";
				result += this.same.toString();
			}
			if (this.junior != null) {
				result += "more junior " + this.data.toString() + " :\n";
				result += this.junior.toString();
			}
			return result;
		}

		/*
		 * Swap the data information between two nodes
		 */
		private void swapData(CatNode c) {
			CatInfo temp = this.data;
			this.data = c.data;
			c.data = temp;
		}

		/*
		 * I had a really hard time debugging the trees
		 * There is this nice 'language' called dot
		 * which allows you to draw diagrams
		 * the output isn't perfect, I haven't figured out
		 * how to output a single node which has same,
		 * senior and junior set to null
		 * While I was debugging I set this to public
		 */
		private void printTree() {
			// Output the tree in dot format that can displayed using
			// graphviz, see http://www.webgraphviz.com/
			System.out.println("digraph G {");
			this.printNodes();
			System.out.println("}");
		}

		/*
		 * Print out the connections between nodes
		 * To do my debugging I changed this to public
		 */
		private void printNodes() {

			if (null != this.senior) {
				System.out.println("\"" + this.data + "\" -> \"" + this.senior.data + "\"");
			} 

			if (null != this.same) {
				System.out.println("\"" + this.data + "\" -> \"" + this.same.data + "\"");
			}

			if (null != this.junior) {
				System.out.println("\"" + this.data + "\" -> \"" + this.junior.data + "\"");
			} 

			if (null != this.senior) {
				this.senior.printNodes();
			}
			if (null != this.same) {
				this.same.printNodes();
			}
			if (null != this.junior) {
				this.junior.printNodes();
			}
		}

		/*
		 * Add a node to another node.
		 * Note: this does not change the junior, same or senior
		 *       fields, which is REALLY handy for removeCat
		 *       because we can take a whole 'tree' and insert
		 *       it into another tree
		 */
		public CatNode addCat(CatNode c) {
			if (c.data.monthHired < this.data.monthHired) {
				// Add to the more senior list
				if (null == this.senior) {
					this.senior = c;
				} else {
					this.senior.addCat(c);
				}
			} else if (c.data.monthHired > this.data.monthHired) {
				if (null == this.junior) {
					this.junior = c;
				} else {
					this.junior.addCat(c);
				}
			} else { // they were hired in the same month
				if (c.data.furThickness > this.data.furThickness) {
					// c's thickness is larger than this node, so
					// let's swap the data, and set same of the root
					// to this
					this.swapData(c);
					c.same = this.same;
					this.same = c;
				} else {
					// We need to go through the same list and determine
					// where to place c
					CatNode prev = null;
					CatNode cur = this;
					while ( (cur != null) && (c.data.furThickness < cur.data.furThickness) ){
						prev = cur;
						cur = cur.same;
					}
					if (null == cur) {
						// we reached the end of the list without finding
						// a node with smaller thickness, add it to the end
						// of prev
						prev.same = c;
					} else {
						cur.swapData(c);
						c.same = cur.same;
						cur.same = c;
					}

				}
			}
			return this;
		}

		/*
		 * Remove the node which has the data associated with the 
		 * CatInfo passed in through c
		 */
		public CatNode removeCat(CatInfo c) {
			
			if (c.monthHired < this.data.monthHired) {
				// Go down the senior path of the tree
				// if not null, otherwise we are done.
				if (null != this.senior) {
					this.senior = this.senior.removeCat(c);
				} else {
					return this;
				}
			} else if (c.monthHired > this.data.monthHired) {
				// Go down the junior path of the tree
				// if not null, otherwise we are done.
				if (null != this.junior) {
					this.junior = this.junior.removeCat(c);
				} else {
					return this;
				}
			} else { // Month is equal
				// We first check if this is a match
				if (this.data.equals(c)) {
					// First check if there is an entry in same that can take
					// this's place
					if (null != this.same) {
						this.data = this.same.data;
						this.same = this.same.same;
					} else {
						// this is a match and there is nothing in same
						if (null == this.senior) {
							if (null == this.junior ) {
								return null;
							}
							this.data = this.junior.data;
							this.senior = this.junior.senior;
							this.same = this.junior.same;
							this.junior = this.junior.junior;
							return this;
						} else {
							// senior is not null
							if (null != this.junior) {
								// Rip out the junior node, and we'll move it back
								// after we have the root setup
								CatInfo juniorInfo = new CatInfo(this.junior.data.name,
										this.junior.data.monthHired,
										this.junior.data.furThickness,
										this.junior.data.nextGroomingAppointment,
										this.junior.data.expectedGroomingCost);
								CatNode juniorNode = new CatNode(juniorInfo);
								juniorNode.same = this.junior.same;
								juniorNode.senior = this.junior.senior;
								juniorNode.junior = this.junior.junior;
								this.data = this.senior.data;
								this.junior = this.senior.junior;
								this.same = this.senior.same;
								this.senior = this.senior.senior;
								this.addCat(juniorNode);
							} else {
								// There is no junior, so just place the senior
								// data in the root
								this.data = this.senior.data;
								this.junior = this.senior.junior;
								this.same = this.senior.same;
								this.senior = this.senior.senior;
							}
						}
					}
				} else { 
					// The month is a match but the other fields are not
					// check the nodes in same
					if (null != this.same ) {
						// is there a match within one of the same nodes for this?
						CatNode prev = this;
						CatNode cur = this.same;
						while ( (cur != null) && (!cur.data.equals(c)) ){
							prev = cur;
							cur = cur.same;
						}
						if (null == cur) {
							return this;
						}
						// cur is the node we need to remove
						//prev.data = cur.data;
						prev.same = cur.same;
						return this;
					}
				}
			}
			return this;
		}

		public int mostSenior() {
			if(this.senior != null) {
				return this.senior.mostSenior();
			}
			return this.data.monthHired;
		}

		public int fluffiest() {
			// initialize maxFluff with root values fluffiness 
			int maxFluff = this.data.furThickness;
			int tempFluff = maxFluff;
//			We don't need to check same because the top of the list
			// always has the fluffiest
			if (this.senior != null) {
				tempFluff = this.senior.fluffiest();
				if (tempFluff > maxFluff) {
					maxFluff = tempFluff;
				}
			}

			if(this.junior != null) {
				tempFluff = this.junior.fluffiest();
				if (tempFluff > maxFluff) {
					maxFluff = tempFluff;
				}
			}
			return maxFluff;
		}

		/*
		 * We need to determine how many people were hired in 
		 * a range.
		 */
		public int hiredFromMonths(int Min, int Max) {
			int count = 0;

			if ( (this.data.monthHired >= Min) && (this.data.monthHired <= Max)) {
				// this is within the period we are looking for, increment count
				count +=1;
				// Now loop through all the same nodes since they have the same hire month
				// and increment the count;
				CatNode cur = this.same;
				while (null != cur) {
					count++;
					cur = cur.same;
				}
				/*
				 * There may still be some in junior and senior
				 * so we need to go through them.
				 */
				if ( (this.data.monthHired > Min) && (null != this.senior)){
					count += this.senior.hiredFromMonths(Min, Max);
				}
				if ( (this.data.monthHired < Max) && (null != this.junior) ) {
					count += this.junior.hiredFromMonths(Min, Max);
				}

			} else if ( (this.data.monthHired > Max) && (null != this.senior) ) {
				/*
				 * If this node wasn't in the range, we can make an informed decision
				 * and only explore junior or senior
				 */
				count += this.senior.hiredFromMonths(Min, Max);
			} else if ( (this.data.monthHired < Min) && (null != this.junior) ) {
				count += this.junior.hiredFromMonths(Min, Max);
			}
			return count;
		}

		/*
		 * Return the fluffiest cat for a given month
		 */
		public CatInfo fluffiestFromMonth(int month) {
			/*
			 * We have a match, so return the data
			 * no need to explore same since they are all less
			 */
			if(this.data.monthHired == month) {
				return this.data;
			}
			/*
			 * Let's be smart, and only explore the relevant sub-tree
			 * skip junior or senior as makes sense
			 */
			if(this.data.monthHired < month && this.junior != null) {
				return this.junior.fluffiestFromMonth(month);
			}

			if(this.data.monthHired > month && this.senior != null) {
				return this.senior.fluffiestFromMonth(month);
			}
			return null; 
		}

		/*
		 * Return an array with the planning costs
		 * for grooming based on a number of months
		 * after 243
		 */
		public int[] costPlanning(int nbMonths) {
			int [] arrayOfCosts = new int[nbMonths];

			int index = 243;
			int maxValue = index + nbMonths;
			/*
			 * Init the array with zero, and then we can use it to
			 * recurse
			 */
			for(index = 243; index < maxValue; index++) {
				int arrayIndex = index - 243;
				arrayOfCosts[arrayIndex] = 0;
			}
			/*
			 * Start recursing and return the result
			 */
			this.getCostForMonth(arrayOfCosts, 243, 243+nbMonths-1);
			return arrayOfCosts;

		}

		/*
		 * Okay, we have an array to store the results in, along with 
		 * starting and ending months, inclusive
		 * What's really nice about the approach I used is that I check
		 * what the month is, and update the relevant array entry
		 * I don't search the tree over and over again for each month
		 * so the time complexity is O(n) instead of O(n*m) where n
		 * is the number of cats and m the number of months
		 */
		private int getCostForMonth(int [] arrayOfCosts, int start, int end) {
			int total = 0;

			if ( (this.data.nextGroomingAppointment >= start) &&
					(this.data.nextGroomingAppointment <= end) ) {
				arrayOfCosts[this.data.nextGroomingAppointment -243] += this.data.expectedGroomingCost;
			}
			if(this.same != null) {
				this.same.getCostForMonth(arrayOfCosts, start, end); 		
			}
			if(this.senior != null) {
				this.senior.getCostForMonth(arrayOfCosts, start, end); 		
			}
			if(this.junior != null) {
				this.junior.getCostForMonth(arrayOfCosts, start, end); 		
			}
			return total;
		}


		/*
		 * We receive an ArrayList and append to it the elements of the 
		 * tree in the requested order
		 */
		private void traverseTreeAddElement(ArrayList<CatNode> catList) {

			if (this.senior != null) {
				this.senior.traverseTreeAddElement(catList);
			}

			if (this.same != null) {
				this.same.traverseTreeAddElement(catList);
			}

			catList.add(this);

			if (this.junior != null) {
				this.junior.traverseTreeAddElement(catList);
			}
		}

		/*
		 * Traverse the tree and add the elements into an arrayList
		 */
		private ArrayList<CatNode> traverseTree(){
			ArrayList<CatNode> catList = new ArrayList<CatNode>();
			this.traverseTreeAddElement(catList);
			return catList;
		}

	}
	private class CatTreeIterator implements Iterator<CatInfo> {
		// HERE YOU CAN ADD THE FIELDS YOU NEED
		private ArrayList<CatNode> array;
		private int i;
		public CatTreeIterator() {
			/*
			 * Wow, it took me a LOT of work to figure this part out
			 * I didn't know I could get access to the tree
			 * from here, lots of research involved.
			 * Probably an area I learn more about in Java
			 */
			array = CatTree.this.root.traverseTree();
			i = 0;
		}

		public CatInfo next(){
			//YOUR CODE GOES HERE
			if (i<array.size()) {
				// Note: really important to increment after
				return array.get(i++).data;
			}
			throw new NoSuchElementException();

		}

		public boolean hasNext() {
			//YOUR CODE GOES HERE
			if (i < array.size()) {
				return true;
			}
			return false; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
	}

}

