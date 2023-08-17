// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

/**
 * This interface defines getter methods for each grocery item's data fields
 * and is implemented by a class that represents a grocery item and its associated
 * data.
 */

public interface IGroceryItem extends Comparable<IGroceryItem> {

    /**
     * Returns the name of the grocery item
     * 
     * @return name of the grocery item
     */
    public String getName();

    /**
     * Returns the category to which the grocery item belongs
     * 
     * @return category to which the grocery item belongs
     */
    public String getCategory();

    /**
     * Returns the price of the grocery item
     * 
     * @return price of the grocery item
     */
    public double getPrice();
    
    /**
     * Returns the amount/number of pieces available of the grocery item
     * 
     * @return amount available of the grocery item
     */
    public int getAmount();

    public void setAmount(int amount);
}
