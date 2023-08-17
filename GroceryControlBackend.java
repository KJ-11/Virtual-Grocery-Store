
// --== CS400 Project Two File Header ==--
// Name: Michael Deng
// CSL Username: mdeng
// Email: madeng@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>
import java.util.ArrayList;

public class GroceryControlBackend implements IGroceryControlBackend {
	IRedBlackTree store;
	String category;
	double upperPrice;
	GroceryCart cart;

	/**
	 * Initializes GroceryControlBackend by creating a new grocery store
	 */
	public GroceryControlBackend(RedBlackTreeBD store, GroceryCart cart) {
		this.store = store;
		category = null;
		upperPrice = Integer.MAX_VALUE;
		this.cart = cart;
	}

	public GroceryControlBackend(RedBlackTree store, GroceryCart cart) {
		this.store = store;
		category = null;
		upperPrice = Integer.MAX_VALUE;
		this.cart = cart;
	}

	/**
	 * Searches for an item within a given price range based on upper price and type
	 * filter
	 * 
	 * @return returns an array list of items with prices within the given price
	 *         range.
	 */
	@Override
	public ArrayList<IGroceryItem> searchByFilters() {
		// Utilizes RBT.filter() to get a list of all items under a certain price
		ArrayList<IGroceryItem> sufficientlyCheap = store.filter(upperPrice);
		if (category == null) { // If no category filter is set
			return sufficientlyCheap;
		}

		// Otherwise, add items which satisfy the category filter into a new list, and
		// return it
		ArrayList<IGroceryItem> filteredByCategory = new ArrayList<IGroceryItem>();
		for (IGroceryItem item : sufficientlyCheap) {
			if (item.getCategory().equals(category)) {

				filteredByCategory.add(item);
			}
		}

		return filteredByCategory;
	}

	/**
	 * Searches for an item by a keyword and existing filters. (If the name of the
	 * actual item contains the keyword)
	 * 
	 * @return returns an array list of items whose name contains the keyword
	 */
	@Override
	public ArrayList<IGroceryItem> searchByKeyword(String keyword) {
		ArrayList<IGroceryItem> filteredItems = searchByFilters();

		// Add all items which satisfy filter requirements and contain keyword in their
		// name to a list to be returned
		ArrayList<IGroceryItem> containsName = new ArrayList<IGroceryItem>();

		for (IGroceryItem item : filteredItems) {
			if (item.getName().toUpperCase().contains(keyword.toUpperCase())) {
				containsName.add(item);
			}
		}

		return containsName;
	}

	/**
	 * Returns the number of items currently in the grocery store (does not include
	 * stock, so number of unique items)
	 * 
	 * @return the number of unique items in the grocery store
	 */
	@Override
	public int getNumberItems() {
		return store.size();
	}

	/**
	 * Returns the number of items in the cart
	 * 
	 * @return the number of items in the cart
	 */
	public int getCartSize() {
		return cart.returnSize();
	}

	/**
	 * Returns an array list of the cart of items
	 * 
	 * @return
	 */
	public ArrayList<IGroceryItem> returnItems() {
		return cart.returnItems();
	}

	/**
	 * Removes item from cart and puts it back into the store
	 * 
	 * @param item the item to be removed/put back
	 */
	public void returnItem(IGroceryItem item) {
		store.insert(item);
		cart.removeItem(item);
	}

	/**
	 * Adds item from cart and removes it from the store
	 * 
	 * @param item the item to be put into the cart/removed
	 * @throws Exception
	 */
	public void getItem(IGroceryItem item) throws Exception {
		if (store.remove(item)) {
			cart.addItem(new GroceryItem(item.getName(), item.getCategory(), item.getPrice(), 1));
		} else {
			throw new Exception("item not in store");
		}
	}

	/**
	 * Returns the price of the cart
	 * 
	 * @return the price of the cart
	 */
	public double returnPrice() {
		return cart.getCartPrice();
	}

	/**
	 * Sets the type filter
	 * 
	 * @param type the type to set
	 * @throws IllegalArgumentException if type is not one of the possible
	 *                                  categories
	 */
	@Override
	public void setCategoryFilter(String type) {
		this.category = type;
	}

	/**
	 * Sets the price filters
	 */
	@Override
	public void setPriceFilter(double upper) {
		upperPrice = upper;
	}

	/**
	 * Returns the type
	 * 
	 * @return the current type filter
	 */
	@Override
	public String getCategoryFilter() {
		return category;
	}

	/**
	 * Returns the current price filter
	 * 
	 * @return the upper price value
	 */
	@Override
	public Double getPriceFilter() {
		return upperPrice;
	}
}
