// --== CS400 Project Two File Header ==--
// Name: Henry Burke
// CSL Username: hburke
// Email: hpburke@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: go drink water!

/**
 * Grocery control frontend.
 *
 * @author Henry Burke
 */
public interface IGroceryControlFrontend {
    // GroceryControlFrontend(Scanner userInputScanner, IGroceryControlBackend backend)

    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
     */
    public void runCommandLoop();

    /**
     * Prints out main menu.
     */
    public void displayMainMenu();

    /**
     * Searches the store for a specific item by name.
     */
    public void searchByItemName();

    /**
     * Searches the store for items under a certain price.
     */
    public void setPriceFilter();

    /**
     * Sets the filter to see specific items in the store.
     */
    public void setCategoryFilter();

    /**
     * Searches based on picked filters.
     */
    public void filterSearch();

    /**
     * Adds an item to the cart.
     */
    public void addCartItem();

    /**
     * Removes an item from the cart.
     */
    public void removeCartItem();

    /**
     * Displays the cart's items to the user.
     */
    public void printCart();
}