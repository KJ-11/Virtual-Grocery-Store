// --== CS400 Project Two File Header ==--
// Name: Henry Burke
// CSL Username: hburke
// Email: hpburke@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: hopefully this cart doesn't have squeaky wheels

public class GroceryItemFD implements IGroceryItem{
    private String name;
    private String category;
    private double price;
    private int amtAvail;

    GroceryItemFD(String name, String category, double price, int amtAvail) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.amtAvail = amtAvail;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getAmount() {
        return amtAvail;
    }

    @Override
    public void setAmount(int amount) {
}

    @Override
    public int compareTo(IGroceryItem item) {
        if(item.getName().equals(name)) {
            if(item.getCategory().equals(category)) {
                if(item.getPrice() == price) {
                    if(item.getAmount() == amtAvail) {
                        return 0;
                    }
                }
            }
        }
        return -1;
    }
}
