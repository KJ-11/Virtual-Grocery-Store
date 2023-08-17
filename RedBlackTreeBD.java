// --== CS400 Project Two File Header ==--
// Name: Michael Deng
// CSL Username: mdeng
// Email: madeng@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;

/**
 * RBTB
 * 
 * @author Michael Deng
 *
 * @param <T>
 */
public class RedBlackTreeBD implements IRedBlackTree<IGroceryItem> {
	ArrayList<IGroceryItem> list1, list2, list3;
	public RedBlackTreeBD(ArrayList<IGroceryItem> list1, ArrayList<IGroceryItem> list2, ArrayList<IGroceryItem> list3) {
		this.list1 = list1;
		this.list2 = list2;
		this.list3 = list3;
	}
	
	public RedBlackTreeBD() {
	}
	/**
	 * Inserts a node of type T into the RBT, balancing as necessary
	 * 
	 * @param data T node that is to be inserted into the RBT
	 * @return boolean if data was successfully inserted
	 * @throws NullPointerException     data passed into function cannot be null
	 * @throws IllegalArgumentException
	 */
	public boolean insert(IGroceryItem data) throws NullPointerException, IllegalArgumentException {
		return false;
	}

	/**
	 * Checks the RBT for a value
	 * 
	 * @param data T data to search for
	 * @return boolean if value exists in tree
	 */
	public boolean contains(IGroceryItem data) {
		return false;
	}

	/**
	 * Returns the amount of nodes in the RBT
	 * 
	 * @return int amount of nodes
	 */
	public int size() {
		return -1;
	}

	/**
	 * Returns if RBT is empty
	 * 
	 * @return boolean representing if RBT is empty
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Returns an arrayList of all values in the RBT <= the max
	 * 
	 * @param upperPrice int Maximum price of an object
	 * @return ArrayList of all items under a certain price
	 */
	public ArrayList<IGroceryItem> filter(double upperPrice) {
		if (upperPrice == Integer.MAX_VALUE) {
			return list1;
		} else if (upperPrice == 6) {
			return list2;
		} else if (upperPrice == 1) {
			return list3;
		}
		
		return null;
	}

	/**
	 * Removes the node toRemove from the tree
	 * 
	 * @param toRemove T node to be removed
	 * @return boolean if removal was successful
	 */
	public boolean remove(IGroceryItem toRemove) {
		return false;
	}
}
