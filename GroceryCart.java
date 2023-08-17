// --== CS400 Project Two File Header ==--
// Name: Michael Deng
// CSL Username: mdeng
// Email: madeng@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;

public class GroceryCart implements IGroceryCart {
	ArrayList<IGroceryItem> cart;
	
	public GroceryCart() {
		cart = new ArrayList<IGroceryItem>();
	}
	
	/**
	 * Returns the size of the grocery cart 
	 * @return the number of items in the grocery cart
	 */
	@Override
	public int returnSize() {
		//return cart.getSize() would return # unique items
		int count = 0;
		for(IGroceryItem item: cart) {
			count += item.getAmount();
		}
		
		return count;
	}

	 /**
     * Returns an arraylist of grocery items in the cart
     * @return An arraylist holding all the items in the cart
     */
	@Override
	public ArrayList<IGroceryItem> returnItems() {
		return cart;
	}

	/**
     * Adds a grocery item to the grocery cart
     * @param item the item to be added
     */
	@Override
	public void addItem(IGroceryItem item) {
		cart.add(item);
	}

	/**
     * Removes a grocery item from the grocery cart
     * @param item the item to be removed
     */
	@Override
	public void removeItem(IGroceryItem item) {
		for (IGroceryItem itemInCart: cart) {
			if (itemInCart.compareTo(item) == 0) {
				cart.remove(itemInCart);
				break;
			}
		}
	}

	/**
     * Returns the total price of all items in the cart
     * @return the total price of the cart
     */
	@Override
	public double getCartPrice() {
		double totalPrice = 0;
		for (IGroceryItem item: cart) {
			totalPrice += item.getPrice() * item.getAmount();
		}
		
		return totalPrice;
	}

}
