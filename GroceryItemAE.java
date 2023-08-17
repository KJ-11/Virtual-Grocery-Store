// --== CS400 Project Two File Header ==--
// Name: Kshitij Jhunjhunwala
// CSL Username: kshitij
// Email: kjhunjhunwa2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>


/**
 * Placeholdder class for GroceryItem object
 * 
 * @author kshitij
 *
 */
public class GroceryItemAE implements IGroceryItem {

  private String name;
  private String category;
  private double price;
  private int amount;
  
  /**
   * Placeholder constructor
   * @param name placeholder name of item
   * @param category placeholder category of item
   * @param price placeholder price of item
   * @param amount placeholder amount available of item
   */
  public GroceryItemAE(String name, String category, double price, int amount) {
    this.name = name;
    this.category = category;
    this.price = price;
    this.amount = amount;
  }
  /**
   * Placeholder method to get the name of the item
   * @return String name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Placeholder method to get the category of the item
   * @return String category
   */
  @Override
  public String getCategory() {
    return this.category;
  }

  /**
   * Placeholder method to get the price of the item
   * @return Double price
   */
  @Override
  public double getPrice() {
    return this.price;
  }

  /**
   * Placeholder method to get the amount available of the item
   * @return Integer amount
   */
  @Override
  public int getAmount() {
    return this.amount;
  }
  
  /**
   * Placeholder method to set the amount available of the item
   */
  @Override
  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  /**
   * Placeholder method for comparing two GroceryItems
   */
  @Override
  public int compareTo(IGroceryItem otherItem) {
    if (this.price < otherItem.getPrice()) {
      return -1;
    } else if(this.price > otherItem.getPrice()) {
      return 1;
    } else {
      return 0;
    }
  }
}
