// --== CS400 Project Two File Header ==--
// Name: Kshitij Jhunjhunwala
// CSL Username: kshitij
// Email: kjhunjhunwa2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;

/**
 * Interface for the Red Black Tree that is to be implemented
 * 
 * @author Kshitij Jhunjhunwala
 *
 * @param <T>

 */
public interface IRedBlackTree<T> {

  /**
   * Inserts a node of type T into the RBT, balancing as necessary 
   * 
   * @param data T node that is to be inserted into the RBT
   * @return boolean if data was successfully inserted
   * @throws NullPointerException     data passed into function cannot be null
   * @throws IllegalArgumentException
   */
  public boolean insert(T groceryItem) throws NullPointerException, IllegalArgumentException;

  /**
   * Checks the RBT for a value
   * 
   * @param data T data to search for
   * @return boolean if value exists in tree
   */
  public boolean contains(T groceryItem);

  /**
   * Returns the amount of nodes in the RBT
   * 
   * @return int amount of nodes
   */
  public int size();

  /**
   * Returns if RBT is empty
   * 
   * @return boolean representing if RBT is empty
   */
  public boolean isEmpty();

  /**
   * Returns an arrayList of all values in the RBT <= the max
   * 
   * @param max double Maximum price of an object
   * @return ArrayList of all items under a certain price
   */
  public ArrayList<T> filter(double max);

  /**
   * Method to remove an item's amount in the node to simulate removing an item from the grocery
   * store and 'adding it to the grocery cart'
   * 
   * @param toRemove the node whose amount available is to be modified
   * @return True if successful, false otherwise
   */
  public boolean remove(T toRemove);
}
