import java.util.ArrayList;

public interface IGroceryCart {
    /**
    * Returns the number of items in the cart
    * @return the number  of items in the cart
    */
    public int returnSize();

    /**
     * Returns an arraylist of grocery items in the cart
     * @return An arraylist holding all the items in the cart
     */
    public ArrayList<IGroceryItem> returnItems();
    
    /**
     * Adds a grocery item to the grocery cart
     * @param item the item to be added
     */
    public void addItem(IGroceryItem item);

    /**
     * Removes a grocery item from the grocery cart
     * @param item the item to be removed
     */
    public void removeItem(IGroceryItem item);
    
    /**
     * Returns the total price of all items in the cart
     * @return the total price of the cart
     */
    public double getCartPrice();
}
