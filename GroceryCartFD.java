// --== CS400 Project Two File Header ==--
// Name: Henry Burke
// CSL Username: hburke
// Email: hpburke@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: hopefully this cart doesn't have squeaky wheels

import java.util.ArrayList;

public class GroceryCartFD implements IGroceryCart {
    private ArrayList<IGroceryItem> cart = new ArrayList<>();

    @Override
    public int returnSize() {
        return cart.size();
    }

    @Override
    public ArrayList<IGroceryItem> returnItems() {
        return cart;
    }

    @Override
    public void addItem(IGroceryItem item) {
        cart.add(item);
    }

    @Override
    public void removeItem(IGroceryItem item) {
        cart.remove(item);
    }

    @Override
    public double getCartPrice() {
        double totalPrice = 0;
        for (IGroceryItem item : cart) {
            totalPrice = totalPrice + (item.getPrice() * item.getAmount());
        }
        return totalPrice;
    }
}
