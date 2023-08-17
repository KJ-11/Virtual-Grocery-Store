// --== CS400 Project Two File Header ==--
// Name: Michael Deng
// CSL Username: mdeng
// Email: madeng@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;

public interface IGroceryControlBackend {
    /**
     * Sets the type filter
     * @param type the type to set
     */
    public void setCategoryFilter(String type);

    /**
     * Searches for an item within a given price range
     * @return returns an array list of items with prices within the given price range.
     */
    public ArrayList<IGroceryItem> searchByKeyword(String keyword);
    public ArrayList<IGroceryItem> searchByFilters();

    /**
     * Sets the price filters
     */
    public void setPriceFilter(double upper);

    /**
     * Returns the number of items currently in the grocery store (doesn't includes stock, so number of unique items)
     * @return the number of unique items in the grocery store
     */
    public int getNumberItems();

    /**
     * Returns the type
     * @return the current type filter
     */
    public String getCategoryFilter();

/**
 * Returns the current price filter
 * @return the price range as a string separated by commas.
 */
public Double getPriceFilter();

    /**
     * Removes item from cart and puts it back into the store
     * @param item the item to be removed/put back
     */
    public void returnItem(IGroceryItem item);

    /**
     * Adds item from cart and removes it from the store
     * @param item the item to be put into the cart/removed
     * @throws Exception 
     */
    public void getItem(IGroceryItem item) throws Exception;

    /**
     * Returns the price of the cart
     * @return the price of the cart
     */
    public double returnPrice();

    /**
     * Returns the number of items in the cart
     * @return the number of items in the cart
     */
    public int getCartSize();

    /**
     * Returns an array list of the cart of items
     * @return
     */
    public ArrayList<IGroceryItem> returnItems();
}

