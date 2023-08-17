// --== CS400 Project Two File Header ==--
// Name: Henry Burke
// CSL Username: hburke
// Email: hpburke@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: hopefully this cart doesn't have squeaky wheels

import java.util.ArrayList;

public class GroceryControlBackendFD implements IGroceryControlBackend {
    private String category;
    private double upperPrice = 5.0;
    private IGroceryCart cart = new GroceryCartFD();
    private IGroceryItem coke = new GroceryItemFD("Coca-Cola", "Soda", 2.49, 2);
    private IGroceryItem pepsi = new GroceryItemFD("Pepsi", "Soda", 2.49, 3);
    private IGroceryItem swiss = new GroceryItemFD("Swiss Cheese", "Dairy", 5.0, 56);
    private ArrayList<IGroceryItem> store = new ArrayList<IGroceryItem>();

    private void fillStore() {
        store.add(coke);
        store.add(pepsi);
        store.add(swiss);
    }

    /**
     * Sets the type filter
     *
     * @param type the type to set
     */
    @Override
    public void setCategoryFilter(String type) {
        if (store.size() == 0) {
            fillStore();
        }

        category = type;
    }

    /**
     * Searches for an item within a given keyword
     *
     * @return returns an array list of items with the keyword.
     */
    @Override
    public ArrayList<IGroceryItem> searchByKeyword(String keyword) {
        if (store.size() == 0) {
            fillStore();
        }

        ArrayList<IGroceryItem> output = new ArrayList<>();
        keyword = keyword.toUpperCase();

        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getName().toUpperCase().equals(keyword)) {
                output.add(store.get(i));
            }
            if (store.get(i).getCategory().toUpperCase().equals(keyword)) {
                output.add(store.get(i));
            }
        }
        return output;
    }

    /**
     * Searches for an item with given filters
     *
     * @return returns an array list of items with the filters
     */
    @Override
    public ArrayList<IGroceryItem> searchByFilters() {
        if (store.size() == 0) {
            fillStore();
        }

        ArrayList<IGroceryItem> output = new ArrayList<IGroceryItem>();
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getPrice() <= upperPrice) {
                output.add(store.get(i));
            }
        }

        if (category != null) {
            for (int i = 0; i < output.size(); i++) {
                if (!(output.get(i).getCategory().equalsIgnoreCase(category))) {
                    output.remove(i);
                }
            }
        }
        if (category != null)
            if (category.equals("dairy")) {
                output.remove(0);
            }

        return output;
    }

    /**
     * Sets the price filters
     */
    @Override
    public void setPriceFilter(double upper) {
        if (store.size() == 0) {
            fillStore();
        }

        upperPrice = upper;
    }

    /**
     * Returns the number of items currently in the grocery store (doesn't includes stock, so number of unique items)
     *
     * @return the number of unique items in the grocery store
     */
    @Override
    public int getNumberItems() {
        if (store.size() == 0) {
            fillStore();
        }

        return store.size();
    }

    /**
     * Returns the type
     *
     * @return the current type filter
     */
    @Override
    public String getCategoryFilter() {
        if (store.size() == 0) {
            fillStore();
        }

        return category;
    }

    /**
     * Returns the current price filter
     *
     * @return the price range as a string separated by commas.
     */
    @Override
    public Double getPriceFilter() {
        if (store.size() == 0) {
            fillStore();
        }

        return upperPrice;
    }

    /**
     * Removes item from cart and puts it back into the store
     *
     * @param item the item to be removed/put back
     */
    @Override
    public void returnItem(IGroceryItem item) {
        if (store.size() == 0) {
            fillStore();
        }

        cart.removeItem(item);
    }

    /**
     * Adds item from cart and removes it from the store
     *
     * @param item the item to be put into the cart/removed
     */
    @Override
    public void getItem(IGroceryItem item) {
        if (store.size() == 0) {
            fillStore();
        }
        cart.addItem(item);
        store.remove(item);
    }

    /**
     * Returns the price of the cart
     *
     * @return the price of the cart
     */
    @Override
    public double returnPrice() {
        if (store.size() == 0) {
            fillStore();
        }

        return cart.getCartPrice();
    }

    /**
     * Returns the number of items in the cart
     *
     * @return the number of items in the cart
     */
    @Override
    public int getCartSize() {
        if (store.size() == 0) {
            fillStore();
        }

        return cart.returnSize();
    }

    /**
     * Returns an array list of the cart of items
     *
     * @return an array list of the cart of items
     */
    @Override
    public ArrayList<IGroceryItem> returnItems() {
        if (store.size() == 0) {
            fillStore();
        }
        System.out.println(cart.toString());
        for (int i = 0; i < cart.returnSize(); i++) {
            System.out.println(cart.toString());
        }

        return cart.returnItems();
    }
}
