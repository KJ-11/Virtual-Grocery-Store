// --== CS400 Project Two File Header ==--
// Name: Henry Burke
// CSL Username: hburke
// Email: hpburke@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: hopefully this cart doesn't have squeaky wheels

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

/**
 * Tests frontend developer code.
 */
public class FrontendDeveloperTest {
    protected GroceryCartFD cart = null; // initializes grocery cart

    /**
     * Initializes grocery cart before every test.
     */
    @BeforeEach
    public void createCart() {
        cart = new GroceryCartFD();
    }

    /**
     * Tests the runCommandLoop() display with only exit input.
     */
    @Test
    public void test1() {
        String expected = "Welcome to the grocery store! This is your cart.Take a look around, see what looks good.\n" +
                "---/x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x/---\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "Thank you for shopping with us!\n"; 
        TextUITester tester = new TextUITester("8\n");
        GroceryControlFrontend fd = new GroceryControlFrontend();
        fd.runCommandLoop();
        String actual = tester.checkOutput();
        assertTrue(actual.contains(expected));
    }

    /**
     * Tests the search by keyword functionality.
     */
    @Test
    public void test2() {
        String expected = "The store has pepsi in stock.\n"; 
        TextUITester tester = new TextUITester("1\npepsi\n8\n");
        GroceryControlFrontend fd = new GroceryControlFrontend();
        fd.runCommandLoop();
	String actual = tester.checkOutput();
        assertTrue(actual.contains(expected));
    }

    /**
     * Tests the price filter functionality.
     */
    @Test
    public void test3() {
        String expected = "Welcome to the grocery store! This is your cart.Take a look around, see what looks good.\n" +
                "---/x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x/---\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "What is the maximum price you would like to pay?\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "These items fit your filters: \n" +
                "Coca-Cola\n" +
                "Pepsi\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "Thank you for shopping with us!\n";

        TextUITester tester = new TextUITester("2\n4\n4\n8\n");
        GroceryControlFrontend fd = new GroceryControlFrontend();
        fd.runCommandLoop();
        String actual = tester.checkOutput();
        assertTrue(actual.contains(expected));
    }

    /**
     * Tests the set category filter functionality.
     */
    @Test
    public void test4() {
        String expected = "Welcome to the grocery store! This is your cart.Take a look around, see what looks good.\n" +
                "---/x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x/---\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "What category of items would you like to see?\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "These items fit your filters: \n" +
                "Coca-Cola\n" +
                "Pepsi\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "Thank you for shopping with us!\n";
        TextUITester tester = new TextUITester("3\nsoda\n4\n8\n");
        GroceryControlFrontend fd = new GroceryControlFrontend();
        fd.runCommandLoop();
        String actual = tester.checkOutput();
        assertTrue(actual.contains(expected));
    }

    /**
     * Tests grocery cart functionality.
     */
    @Test
    public void test5() {
        String expected = "What item would you like to remove to your cart?\n";
        TextUITester tester = new TextUITester("5\npepsi\n6\npepsi\n8\n");
        GroceryControlFrontend fd = new GroceryControlFrontend();
        fd.runCommandLoop();
        String actual = tester.checkOutput();
        assertTrue(actual.contains(expected));
    }

   /**
     * Tests the frontend integration with all 3 roles. Should give frontend data given from AE RBT
     * which was filled with DW data. Searches for gatorade and adds it to cart. Exits application
     * and should print cart correctly.
     */
    @Test
    public void integrationTest1() {
        String expected = "Welcome to the grocery store! This is your cart.Take a look around, see what looks good.\n" +
                "---/x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x/---\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "What item would you like to search for?\n" +
                "The store has gatorade in stock.\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "What item would you like to add to your cart?\n" +
                "Item added to cart.\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "Thank you for shopping with us!\n" +
                "You bought: [(Gatorade, Drinks, 1.75, 1)]\n";
        TextUITester tester = new TextUITester("1\ngAtOrAdE\n5\ngatorade\n8\n");

	RedBlackTree<IGroceryItem> rbt = new RedBlackTree();
        GroceryItemLoader itemLoader = new GroceryItemLoader();
        ArrayList<IGroceryItem> items = null;
        try {
            items = itemLoader.loadGroceryItems("groceryItems.xml");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        for (IGroceryItem item: items) {
            rbt.insert(item);
        }

        GroceryControlBackend backend = new GroceryControlBackend(rbt, new GroceryCart());
        GroceryControlFrontend fd = new GroceryControlFrontend(backend);

        fd.runCommandLoop();
        String actual = tester.checkOutput();
        assertTrue(actual.contains(expected));
    }

   /**
     * Tests the frontend integration with all 3 roles. Should give frontend data given from AE RBT
     * which was filled with DW data. Sets category to drinks and searches by filters. Exit should
     * print an empty cart.
     */
    @Test
    public void integrationTest2() {
        String expected = "Welcome to the grocery store! This is your cart.Take a look around, see what looks good.\n" +
                "---/x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x/---\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "What category of items would you like to see?\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
                "These items fit your filters: \n" +
                "Mountain Dew\n" +
                "Gatorade\n" +
                "Red Bull\n" +
                "Monster\n" +
                "\n" +
                "You are looking down the aisles:\n" +
                "          1) Search for item by keyword\n" +
                "          2) Set price filter\n" +
                "          3) Set category filter\n" +
                "          4) Search by set filters\n" +
                "\n" +
                "          5) Add an item to your cart\n" +
                "          6) Remove an item from your cart\n" +
                "          7) Look through your cart\n" +
                "          8) Leave the store\n" +
		"Thank you for shopping with us!\n";

        TextUITester tester = new TextUITester("3\nDrinks\n4\n8\n");

        RedBlackTree<IGroceryItem> rbt = new RedBlackTree();
        GroceryItemLoader itemLoader = new GroceryItemLoader();
        ArrayList<IGroceryItem> items = null;
        try {
            items = itemLoader.loadGroceryItems("groceryItems.xml");
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        for (IGroceryItem item: items) {
            rbt.insert(item);
        }

        GroceryControlBackend backend = new GroceryControlBackend(rbt, new GroceryCart());
        GroceryControlFrontend frontend = new GroceryControlFrontend(backend);

        frontend.runCommandLoop();

        String actual = tester.checkOutput();
        assertTrue(actual.contains(expected));
    }

   /**
     * Tests GroceryItemLoader to make sure it load the xml file correctly. 
     */
    @Test
    public void codeReviewOfDataWrangler1() {
	GroceryItemLoader loader = new GroceryItemLoader();
		try {
			assertEquals(loader.loadGroceryItems("groceryItems.xml").get(0).getName(), "Spinach");
			assertEquals(loader.loadGroceryItems("groceryItems.xml").get(1).getName(), "Onions");
			assertEquals(loader.loadGroceryItems("groceryItems.xml").get(2).getName(), "Apple");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

   /**
     * Tests GroceryItem constructor and getters to make sure they work correctly. 
     */
    @Test
    public void codeReviewOfDataWrangler2() {
	GroceryItem item1 = new GroceryItem("Pepsi", "Soda", 2.50, 4);
	GroceryItem item2 = new GroceryItem("Coca-Cola", "Soda", 2.50, 3);
	GroceryItem item3 = new GroceryItem("Swiss", "Dairy", 5.0, 1);
		
	assertEquals(item1.getName(), "Pepsi");
	assertEquals(item1.getCategory(), "Soda");
	assertEquals(item1.getPrice(), 2.50);
	assertEquals(item1.getAmount(), 4);

	assertEquals(item2.getName(), "Coca-Cola");
	assertEquals(item2.getCategory(), "Soda");
	assertEquals(item2.getPrice(), 2.50);
	assertEquals(item2.getAmount(), 3);

    }
}
