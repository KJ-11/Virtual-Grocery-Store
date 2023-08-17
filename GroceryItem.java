// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

/**
 * This class creates an object that represents a grocery item and implements the 
 * IGroceryItem interface
 * 
 * @author Sreyas Srivastava
 *
 */
public class GroceryItem implements IGroceryItem {
	
	private String name; // name of the grocery item
	private String category; // category to which the grocery item belongs
	private double price; // price of the grocery item
	private int amount; // amount/number of pieces available of the grocery item
	
	/**
	 * Constructor to create a new GroceryItem object
	 * 
	 * @param name name of the grocery item to create
	 * @param category category of the grocery item to create
	 * @param price price of the grocery item to create
	 * @param amount amount available of the grocery item to create
	 */
	public GroceryItem(String name, String category, double price, int amount) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.amount = amount;
	}
	
	/**
     * Returns the name of the grocery item
     * 
     * @return name of the grocery item
     */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
     * Returns the category to which the grocery item belongs
     * 
     * @return category to which the grocery item belongs
     */
	@Override
	public String getCategory() {
		return this.category;
	}
	
	/**
     * Returns the price of the grocery item
     * 
     * @return price of the grocery item
     */
	@Override
	public double getPrice() {
		return this.price;
	}
	
	/**
     * Returns the amount/number of pieces available of the grocery item
     * 
     * @return amount available of the grocery item
     */
    public int getAmount() {
    	return this.amount;
    }
    
    /**
     * Sets the amount available of the grocery item to the given amount
     * 
     * @param newAmount amount to be set to the grocery item
     */
    public void setAmount(int newAmount) {
    	this.amount = newAmount;
    }
	
	/**
	 * Compares the prices of two grocery items
	 * 
	 * @param otherItem other GroceryItem to compare
	 * @return 0 if the prices of the items are equal, -1 if the price is less and 1
	 *         if the price is more
	 */
	@Override
	public int compareTo(IGroceryItem otherItem) {
		if (this.getPrice() < otherItem.getPrice()) {
			return -1;
		} else if (this.getPrice() > otherItem.getPrice()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Returns a string representation of the grocery item object
	 * 
	 * @return String representation of a GroceryItem object
	 */
	@Override
	public String toString() {
		return ("(" + this.getName() + ", " + this.getCategory() + ", " 
				+ this.getPrice() + ", " + this.getAmount() + ")");
	}
	
}
