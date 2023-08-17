// --== CS400 Fall 2022 File Header Information ==--
// Name: <Kshitij Jhunjhunwala>
// Email: <kjhunjhunwa2@wisc.edu>
// Team: <AT>
// TA: <Minghao>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Red-Black Tree implementation with a Node inner class for representing the
 * nodes of the tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements IRedBlackTree<T> {

	/**
	 * This class represents a node holding a single value within a binary tree the
	 * parent, left, and right child references are always maintained.
	 */
	protected static class Node<T> {
		public T data;
		public Node<T> parent; // null for root node
		public Node<T> leftChild;
		public Node<T> rightChild;
		public int blackHeight;

		public Node(T data) {
			this.data = data;
			this.blackHeight = 0;
		}

		/**
		 * @return true when this node has a parent and is the left child of that
		 *         parent, otherwise return false
		 */
		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

	}

	protected Node<T> root; // reference to root node of tree, null when empty
	protected int size = 0; // the number of values in the tree

	/**
	 * Inserts a groceryItem into the Red Black Tree.
	 * 
	 * @param groceryItem to be added into the tree
	 * @return true if value was inserted, false otherwise
	 * @throws NullPointerException     when the provided argument is null
	 * @throws IllegalArgumentException when argument provided is a duplicate of an
	 *                                  item in the tree
	 */
	@Override
	public boolean insert(T groceryItem) throws NullPointerException, IllegalArgumentException {
		// null references cannot be stored within this tree
		if (groceryItem == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		Node<T> newNode = new Node<>(groceryItem);
		if (root == null) {
			root = newNode;
			size++;
			this.root.blackHeight = 1;
			return true;
		} // add first node to an empty tree
		else {
			boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
			if (returnValue)
				size++;
			else
				throw new IllegalArgumentException("This RedBlackTree already contains that value.");
			this.root.blackHeight = 1;
			return returnValue;
		}
	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newNode should be inserted, and then extend this tree by
	 * the newNode in that position.
	 * 
	 * @param newNode is the new node that is being added to this tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descenedent beneath
	 * @return true is the value was inserted in subtree, false if not
	 */
	private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
		int compare = newNode.data.compareTo(subtree.data);
		// do not allow duplicate values to be stored within this tree
		if (compare == 0) {
			((IGroceryItem) subtree.data).setAmount(((IGroceryItem) subtree.data).getAmount() + 1);
			size--; // decrements size as size will be incremented again when called in the insert
					// method
					// (incorrect functionality, size should remain the same; this is to ensure that
					// happens)
			return true;
		}
		// store newNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.leftChild);
		}

		// store newNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.rightChild);
		}
	}

	/**
	 * Helper method to resolve any red-black tree property violations when
	 * inserting a new node into the tree
	 * 
	 * @param newNode the node that was just added
	 */
	protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {

		Node<T> parent = newNode.parent;
		Node<T> grandparent = null;

		if (parent.parent != null) {
			grandparent = parent.parent; // finds grandparent if it exists
		}

		Node<T> uncle = null;

		// finds uncle if it exists, else uncle stays as null
		if (grandparent != null) {
			if (parent == grandparent.rightChild) {
				uncle = grandparent.leftChild;
			} else {
				uncle = grandparent.rightChild;
			}
		}

		if (parent.blackHeight == 1) {
			// no property violated when inserted
		} else if (parent.blackHeight == 0) {
			if (uncle == null) {
				if ((uncle == grandparent.leftChild && newNode == parent.leftChild)
						|| uncle == grandparent.rightChild && newNode == parent.rightChild) {
					// uncle and child inserted in same direction
					rotate(newNode, parent);
					rotate(newNode, grandparent);
					// changes colors as needed after the rotate
					newNode.blackHeight = 1;
					parent.blackHeight = 0;
					grandparent.blackHeight = 0;
				} else {
					// uncle and child inserted in opposite directions
					rotate(parent, grandparent);
					grandparent.blackHeight = 0;
					parent.blackHeight = 1;
				}
			} else if (uncle.blackHeight == 1) {
				if ((uncle == grandparent.leftChild && newNode == parent.leftChild)
						|| uncle == grandparent.rightChild && newNode == parent.rightChild) {
					// uncle and child inserted in same direction
					rotate(newNode, parent);
					rotate(newNode, grandparent);
					// changes colors as needed after the rotate
					newNode.blackHeight = 1;
					parent.blackHeight = 0;
					grandparent.blackHeight = 0;
				} else {
					// uncle and child inserted in opposite directions
					rotate(parent, grandparent);
					grandparent.blackHeight = 0;
					parent.blackHeight = 1;
				}
			} else {
				// uncle is red, recoloring as needed
				parent.blackHeight = 1;
				uncle.blackHeight = 1;
				if (grandparent != this.root) {
					grandparent.blackHeight = 0;
					enforceRBTreePropertiesAfterInsert(grandparent); // recursively call to fix problems up
																		// the tree
				}
			}
		}
	}

	/**
	 * Performs the rotation operation on the provided nodes within this tree. When
	 * the provided child is a leftChild of the provided parent, this method will
	 * perform a right rotation. When the provided child is a rightChild of the
	 * provided parent, this method will perform a left rotation. When the provided
	 * nodes are not related in one of these ways, this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param child  is the node being rotated from child to parent position
	 *               (between these two node arguments)
	 * @param parent is the node being rotated from parent to child position
	 *               (between these two node arguments)
	 * @throws IllegalArgumentException when the provided child and parent node
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {

		if (child == parent.leftChild) {
			// perform right rotate
			parent.leftChild = child.rightChild;
			if (child.rightChild != null) {
				child.rightChild.parent = parent;
			}
			child.parent = parent.parent;
			if (child.parent == null) {
				root = child;
			} else {
				if (parent == parent.parent.leftChild) {
					parent.parent.leftChild = child;
				} else {
					parent.parent.rightChild = child;
				}
			}

			parent.parent = child;
			child.rightChild = parent;

		} else if (child == parent.rightChild) {
			// perform left rotate
			parent.rightChild = child.leftChild;
			if (child.leftChild != null) {
				child.leftChild.parent = parent;
			}
			child.parent = parent.parent;
			if (child.parent == null) {
				root = child;
			} else {
				if (parent == parent.parent.rightChild) {
					parent.parent.rightChild = child;
				} else {
					parent.parent.leftChild = child;
				}
			}
			parent.parent = child;
			child.leftChild = parent;

		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Checks whether the tree contains the value *data*.
	 * 
	 * @param groceryItem the groceryItem to test for
	 * @return true if *groceryItem* is in the tree, false if it is not in the tree
	 */
	@Override
	public boolean contains(T groceryItem) {
		// null references will not be stored within this tree
		if (groceryItem == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");
		return this.containsHelper(groceryItem, root);
	}

	/**
	 * Recursive helper method that recurses through the tree and looks for the
	 * value *data*.
	 * 
	 * @param groceryItem the groceryItem value to look for
	 * @param subtree     the subtree to search through
	 * @return true of the value is in the subtree, false if not
	 */
	private boolean containsHelper(T data, Node<T> subtree) {
		if (subtree == null) {
			// we are at a null child, value is not in tree
			return false;
		} else {
			int compare = data.compareTo(subtree.data);
			if (compare < 0) {
				// go left in the tree
				return containsHelper(data, subtree.leftChild);
			} else if (compare > 0) {
				// go right in the tree
				return containsHelper(data, subtree.rightChild);
			} else {
				// we found it :)
				return true;
			}
		}
	}

	/**
	 * Get the size of the tree (its number of nodes).
	 * 
	 * @return the number of nodes in the tree
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Method to check if the tree is empty (does not contain any node).
	 * 
	 * @return true of this.size() return 0, false if this.size() > 0
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Returns an arrayList of all values in the RBT <= the max
	 * 
	 * @param max double Maximum price of an object
	 * @return ArrayList of all items under a certain price
	 */
	@Override
	public ArrayList<T> filter(double max) {
		return filterHelper(this.root, max);

	}

	/**
	 * Recursive helper method that recurses through the tree and adds groceryItems
	 * whose prices are under the max price
	 * 
	 * @param subTree current subtree that is being checked
	 * @param max     double Maximum price of an object
	 * @return
	 */
	public ArrayList<T> filterHelper(Node<T> subTree, double max) {

		IGroceryItem maxItem = new GroceryItemAE("maxName", "maxCategory", max, 1); // creates a
																					// groceryitem with
																					// the max price to
																					// be searched for
																					// implementation
																					// purposes
		ArrayList<T> toReturn = new ArrayList<T>();
		if (subTree == null) {
			return toReturn;
		} else {
			if (((IGroceryItem) (subTree.data)).compareTo(maxItem) <= 0) {
				toReturn.add(subTree.data);
			}
			if (subTree.leftChild != null) {
				toReturn.addAll(filterHelper(subTree.leftChild, max));
			}
			if (subTree.rightChild != null) {
				toReturn.addAll(filterHelper(subTree.rightChild, max));
			}
		}
		return toReturn;
	}

	/**
	 * Method to remove an item's amount in the node to simulate removing an item
	 * from the grocery store and 'adding it to the grocery cart'
	 * 
	 * @param toRemove the data in the node whose amount available is to be modified
	 * @return True if successful, false otherwise
	 */
	@Override
	public boolean remove(T toRemove) {
		if (((IGroceryItem) toRemove).getAmount() > 0) {
			((IGroceryItem) toRemove).setAmount(((IGroceryItem) toRemove).getAmount() - 1);
			return true;
		}
		return false;

	}

	/**
	 * This method performs an inorder traversal of the tree. The string
	 * representations of each data value within this tree are assembled into a
	 * comma separated string within brackets (similar to many implementations of
	 * java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note that
	 * this RedBlackTree class implementation of toString generates an inorder
	 * traversal. The toString of the Node class class above produces a level order
	 * traversal of the nodes / values of the tree.
	 * 
	 * @return string containing the ordered values of this tree (in-order
	 *         traversal)
	 */
	public String toInOrderString() {
		// generate a string of all values of the tree in (ordered) in-order
		// traversal sequence
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		sb.append(toInOrderStringHelper("", this.root));
		if (this.root != null) {
			sb.setLength(sb.length() - 2);
		}
		sb.append(" ]");
		return sb.toString();
	}

	private String toInOrderStringHelper(String str, Node<T> node) {
		if (node == null) {
			return str;
		}
		str = toInOrderStringHelper(str, node.leftChild);
		str += (node.data.toString() + ", ");
		str = toInOrderStringHelper(str, node.rightChild);
		return str;
	}

	/**
	 * This method performs a level order traversal of the tree rooted at the
	 * current node. The string representations of each data value within this tree
	 * are assembled into a comma separated string within brackets (similar to many
	 * implementations of java.util.Collection). Note that the Node's implementation
	 * of toString generates a level order traversal. The toString of the
	 * RedBlackTree class below produces an inorder traversal of the nodes / values
	 * of the tree. This method will be helpful as a helper for the debugging and
	 * testing of your rotation implementation.
	 * 
	 * @return string containing the values of this tree in level order
	 */
	public String toLevelOrderString() {
		String output = "[ ";
		if (this.root != null) {
			LinkedList<Node<T>> q = new LinkedList<>();
			q.add(this.root);
			while (!q.isEmpty()) {
				Node<T> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
		}
		return output + " ]";
	}

	public String toString() {
		return "level order: " + this.toLevelOrderString() + "\nin order: " + this.toInOrderString();
	}

}
