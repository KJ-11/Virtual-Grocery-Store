// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class contains junit tests to check the functionality of the methods in 
 * the classes GroceryItem and GroceryItemLoader
 * 
 * @author Sreyas Srivastava
 *
 */
public class DataWranglerTests {
	
	/**
	 * This method checks if the getName(), getCategory(), getPrice(), getAmount() and 
	 * setAmount() methods of the GroceryItem class work correctly
	 */
	@Test
	public void test1() {
		GroceryItem testGroceryItem = new GroceryItem("Apple", "Produce", 1.20, 4);
		
		// test for getName() method
		Assertions.assertEquals(testGroceryItem.getName(), "Apple", "Test Failed: getName() "
				+ "method of GroceryItem class did not return the correct value");
		
		// test for getCategory() method
		Assertions.assertEquals(testGroceryItem.getCategory(), "Produce", "Test Failed: "
				+ "getCategory() method of GroceryItem class did not return the correct "
				+ "value");
		
		// test for getPrice() method
		Assertions.assertEquals(testGroceryItem.getPrice(), 1.20, "Test Failed: "
				+ "getPrice() method of GroceryItem class did not return the correct value");
		
		// test for getAmount() method
		Assertions.assertEquals(testGroceryItem.getAmount(), 4, "Test Failed: "
				+ "getAmount() method of GroceryItem class did not return the correct "
				+ "value");
		
		// test for setAmount() method
		testGroceryItem.setAmount(7);
		Assertions.assertEquals(testGroceryItem.getAmount(), 7, "Test Failed: "
				+ "setAmount() method of GroceryItem class did not set the amount "
				+ "correctly ");
	}
	
	/**
	 * This method checks if the compareTo() method of the GroceryItem class work correctly
	 */
	@Test
	public void test2() {
		GroceryItem testGroceryItem1 = new GroceryItem("Apple", "Produce", 1.20, 4);
		GroceryItem testGroceryItem2 = new GroceryItem("Cheetos", "Snacks", 4.19, 8);
		GroceryItem testGroceryItem3 = new GroceryItem("Turkey", "Deli", 5.49, 1);
		
		Assertions.assertEquals(testGroceryItem1.compareTo(testGroceryItem2), -1, "Test "
				+ "Failed: compareTo() method of GroceryItem class did not return the "
				+ "correct value");
		
		Assertions.assertEquals(testGroceryItem3.compareTo(testGroceryItem2), 1, "Test "
				+ "Failed: compareTo() method of GroceryItem class did not return the "
				+ "correct value");
		
		Assertions.assertEquals(testGroceryItem1.compareTo(testGroceryItem1), 0, "Test "
				+ "Failed: compareTo() method of GroceryItem class did not return the "
				+ "correct value");
	}
	
	/**
	 * This method checks if the loadGroceryItems() method of the GroceryItemLoader class 
	 * functions correctly if the file path is invalid
	 */
	@Test
	public void test3() {
		GroceryItemLoader testLoader = new GroceryItemLoader();
		try {
			System.out.println(testLoader.loadGroceryItems("invalidFile.xml"));
			Assertions.fail("Test Failed: No exception thrown by loadGroceryItems() method "
					+ "of GroceryItemLoader class when the file path is invalid");
		} catch (FileNotFoundException e) {
			Assertions.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail("Unexpected exception thrown by loadGroceryItems() method of "
					+ "GroceryItemLoader class when the file path is invalid");
		}
	}
	
	/**
	 * This method checks if the loadGroceryItems() method of the GroceryItemLoader class
	 * functions correctly when the file path is valid
	 */
	@Test
	public void test4() {
		GroceryItemLoader testLoader = new GroceryItemLoader();
		try {
			Assertions.assertEquals(testLoader.loadGroceryItems("groceryItems.xml").get(0)
					.getName(), "Spinach", "Test Failed: loadGroceryItems() method of the "
					+ "GroceryItemLoader class did not work correctly when the file path "
					+ "is valid");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assertions.fail("Test Failed: FileNotFoundException thrown by "
					+ "loadGroceryItems() method of GroceryItemLoader class for test4");
		}
	}
	
	/**
	 * This method checks if the loadGroceryItems() method of the GroceryItemLoader class
	 * loads all the values from the XML file correctly
	 */
	@Test
	public void test5() {
		GroceryItemLoader testLoader = new GroceryItemLoader();
		String expected = "[(Spinach, Produce, 3.97, 5), (Onions, Produce, 2.99, 5), "
				+ "(Apple, Produce, 1.2, 4), (Bananas, Produce, 4.99, 2), "
				+ "(Turkey, Deli, 5.49, 1), (Bacon, Deli, 3.72, 4), (Salami, Deli, 5.11, 5)"
				+ ", (Sausages, Deli, 3.49, 10), (Cheetos, Snacks, 4.19, 8), "
				+ "(Doritos, Snacks, 4.5, 2), (Pringles, Snacks, 2.19, 10), "
				+ "(Goldfish, Snacks, 5.45, 6), (Honey Mustard, Ingredients, 3.61, 7), "
				+ "(Chili Powder, Ingredients, 3.98, 4), (Cheese, Ingredients, 2.15, 5), "
				+ "(Salt, Ingredients, 1.5, 2), (Gatorade, Drinks, 1.75, 5), "
				+ "(Mountain Dew, Drinks, 1.25, 3), (Red Bull, Drinks, 2.79, 9), "
				+ "(Monster, Drinks, 3.15, 6)]";

		try {
			Assertions.assertEquals(testLoader.loadGroceryItems("groceryItems.xml")
					.toString(), expected, "Test Failed: loadGroceryItems() method of the "
					+ "GroceryItemLoader class did not return all the grocery items "
					+ "from the XML files correctly");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assertions.fail("Test Failed: FileNotFoundException thrown by "
					+ "loadGroceryItems() method of GroceryItemLoader class for test5");
		}
	}
	
	/**
	 * Test to see if the program works correctly after integration
	 */
	@Test
	public void test1Integration() {
		GroceryItemLoader loader = new GroceryItemLoader();
		ArrayList<IGroceryItem> items = null;
		try {
			items = loader.loadGroceryItems("groceryItems.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		RedBlackTree<IGroceryItem> rbt = new RedBlackTree<>();
		GroceryCart cart = new GroceryCart();
		for (IGroceryItem item : items) {
			rbt.insert(item);
		}
		GroceryControlBackend backend = new GroceryControlBackend(rbt, cart);
		
		GroceryItem testItem1 = new GroceryItem("Cheetos", "Snacks", 4.19, 1);
		GroceryItem testItem2 = new GroceryItem("Gatorade", "Drinks", 1.75, 5);
		GroceryItem testItem3 = new GroceryItem("Cheese", "Ingredients", 2.15, 5);
		try {
			backend.getItem(testItem1);
			backend.getItem(testItem2);
			backend.getItem(testItem3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// check if the items are added correctly to cart
		Assertions.assertEquals(backend.getCartSize(), 3, "Test Failed: grocery items are "
				+ "not added to cart correctly");
		
		// check if the cart is correct
		String expectedCart = "[(Cheetos, Snacks, 4.19, 1), (Gatorade, Drinks, 1.75, 1), "
				+ "(Cheese, Ingredients, 2.15, 1)]";
		
		Assertions.assertEquals(expectedCart, backend.returnItems().toString(), 
				"Test Failed: grocery items are not added to cart correctly");
	}
	
	/**
	 * Test to see if the program works correctly after integration
	 */
	@Test
	public void test2Integration() {
		GroceryItemLoader loader = new GroceryItemLoader();
		ArrayList<IGroceryItem> items = null;
		try {
			items = loader.loadGroceryItems("groceryItems.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		RedBlackTree<IGroceryItem> rbt = new RedBlackTree<>();
		GroceryCart cart = new GroceryCart();
		for (IGroceryItem item : items) {
			rbt.insert(item);
		}
		GroceryControlBackend backend = new GroceryControlBackend(rbt, cart);
		
		GroceryItem testItem1 = new GroceryItem("Cheetos", "Snacks", 4.19, 1);
		GroceryItem testItem2 = new GroceryItem("Gatorade", "Drinks", 1.75, 5);
		GroceryItem testItem3 = new GroceryItem("Cheese", "Ingredients", 2.15, 5);
		rbt.insert(testItem1);
		rbt.insert(testItem2);
		rbt.insert(testItem3);
		
		// check if the grocery items are present in the red-black tree
		Assertions.assertTrue(rbt.contains(testItem1), "Test Failed: grocery items are not "
				+ "added to the red black tree correctly");
		Assertions.assertTrue(rbt.contains(testItem2), "Test Failed: grocery items are not "
				+ "added to the red black tree correctly");
		Assertions.assertTrue(rbt.contains(testItem3), "Test Failed: grocery items are not "
				+ "added to the red black tree correctly");
		
		GroceryItem newTestItem1 = new GroceryItem("Gatorade", "Drinks", 1.75, 4);
		rbt.remove(testItem1);
		
		// check if the quantity is updated when a grocery item is removed from the red
		// black tree and added to cart
		Assertions.assertTrue(rbt.contains(newTestItem1), "Test Failed: grocery items are "
				+ "not removed from the red black tree and added to the cart correctly");
	}
	
	/**
	 * Test to see if the searchByItemName() method developed by the FrontendDeveloper 
	 * works correctly
	 */
	@Test
	public void test1CodeReviewOfFrontendDeveloper() {
		TextUITester tester = new TextUITester("1\nCoca-Cola\n8\n");
		GroceryControlFrontend control = new GroceryControlFrontend();
		control.runCommandLoop();
		
		String expected = "The store has coca-cola in stock.\n";
		String actual = tester.checkOutput();
		Assertions.assertTrue(actual.contains(expected), "Test Failed: searchByItemName() "
				+ "method developed by the FrontendDeveloper did not work correctly");
	}
	
	/**
	 * Test to see if the filterSearch() method developed by the FrontendDeveloper 
	 * works correctly
	 */
	@Test
	public void test2CodeReviewOfFrontendDeveloper() {
		TextUITester tester = new TextUITester("4\n8\n");
		GroceryControlFrontend control = new GroceryControlFrontend();
		control.runCommandLoop();
		
		String expected = "These items fit your filters: \nCoca-Cola\nPepsi\nSwiss Cheese\n";
		String actual = tester.checkOutput();
		Assertions.assertTrue(actual.contains(expected), "Test Failed: filterSearch() "
				+ "method developed by the FrontendDeveloper did not work correctly");
	}
	
}
