// --== CS400 Project Two File Header ==--
// Name: Michael Deng
// CSL Username: mdeng
// Email: madeng@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: <any optional extra notes to your grader>

import static org.junit.jupiter.api.Assertions.*;
import java.lang.Math;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Implements tests for GroceryControlBackend which relies on a dummy RedBlackTreeBD class. 
 * Tests will not work with proper RBT implementation
 * @author Michael Deng
 *
 */
class BackendDeveloperTest {
	protected GroceryControlBackend _instance = null;

	@BeforeEach
	public void createInstance() {
		_instance = new GroceryControlBackend(new RedBlackTreeBD(), new GroceryCart());
	}

	/**
	 * Tests setters and getters for price filter
	 */
	@Test
	void test1() {
		// Randomly generate prices to get and set
		for (int i = 0; i < 10; i++) {
			double value = Integer.MAX_VALUE * Math.random();
			_instance.setPriceFilter(value);
			assertEquals(value, _instance.getPriceFilter());
		}
	}

	/**
	 * Tests setters and getters for category filter
	 */
	@Test
	void test2() {
		//Tests some random category names
		assertEquals(null, _instance.getCategoryFilter());
		_instance.setCategoryFilter("dfafpoijfsaop");
		assertEquals("dfafpoijfsaop", _instance.getCategoryFilter());
		_instance.setCategoryFilter("Produce");
		assertEquals("Produce", _instance.getCategoryFilter());
		_instance.setCategoryFilter("this is a sentence, but also a cateogry");
		assertEquals("this is a sentence, but also a cateogry", _instance.getCategoryFilter());
	}

	/**
	 * Tests searchByFilters with no filters set
	 */
	@Test
	void test3() {
		//Used to create dummy RBT to compare lists.
		ArrayList<IGroceryItem> list1 = new ArrayList<IGroceryItem>();
		list1.add(new GroceryItemBD("name1", "category1", 1.0));
		list1.add(new GroceryItemBD("name2", "category2", 5.));
		list1.add(new GroceryItemBD("name3", "category3", 10.));

		_instance = new GroceryControlBackend(new RedBlackTreeBD(list1, null, null), new GroceryCart());
		assertEquals(_instance.searchByFilters(), list1);
	}

	/**
	 * Tests searchByFilters with filters set
	 */
	@Test
	void test4() {
		//Initialize lists to create dummy RBT
		ArrayList<IGroceryItem> list1 = new ArrayList<IGroceryItem>();
		list1.add(new GroceryItemBD("name1", "category1", 1.0));
		list1.add(new GroceryItemBD("name2", "category2", 5.));
		list1.add(new GroceryItemBD("name3", "category3", 10.));
		list1.add(new GroceryItemBD("name4", "category1", 100));

		ArrayList<IGroceryItem> list2 = new ArrayList<IGroceryItem>();
		list2.add(new GroceryItemBD("name1", "category1", 1.0));
		list2.add(new GroceryItemBD("name2", "category2", 5.));

		ArrayList<IGroceryItem> list3 = new ArrayList<IGroceryItem>();
		list3.add(new GroceryItemBD("name1", "category1", 1.0));

		_instance = new GroceryControlBackend(new RedBlackTreeBD(list1, list2, list3), new GroceryCart());

		// Price filter only
		_instance.setPriceFilter(6.);
		assertEquals(_instance.searchByFilters(), list2);
		_instance.setPriceFilter(1);
		assertEquals(_instance.searchByFilters(), list3);

		// Category filter only
		_instance.setPriceFilter(Integer.MAX_VALUE); // reset to no price filter
		_instance.setCategoryFilter("category1");
		GroceryItemBD item1 = (GroceryItemBD) _instance.searchByFilters().get(0);
		GroceryItemBD item2 = (GroceryItemBD) _instance.searchByFilters().get(1);
		assertEquals(item1.getName(), "name1");
		assertEquals(item1.getCategory(), "category1");
		assertEquals(item1.getPrice(), 1.);
		assertEquals(item2.getName(), "name4");
		assertEquals(item2.getCategory(), "category1");
		assertEquals(item2.getPrice(), 100.);

		// Both filters
		_instance.setPriceFilter(6); // list 2
		_instance.setCategoryFilter("category1"); // only item1
		item1 = (GroceryItemBD) _instance.searchByFilters().get(0);
		assertEquals(item1.getName(), "name1");
		assertEquals(item1.getCategory(), "category1");
		assertEquals(item1.getPrice(), 1.);
		assertEquals(1, _instance.searchByFilters().size());
	}

	/**
	 * Tests searchByKeyword
	 */
	@Test
	void test5() {
		//Initializes lists for dummy RBT
		ArrayList<IGroceryItem> list1 = new ArrayList<IGroceryItem>();
		list1.add(new GroceryItemBD("name1", "category1", 1.0));
		list1.add(new GroceryItemBD("name2", "category2", 5.));
		list1.add(new GroceryItemBD("name2 duplicate", "category1", 100));
		list1.add(new GroceryItemBD("name3", "category3", 10.));
		list1.add(new GroceryItemBD("name4", "category1", 100));

		ArrayList<IGroceryItem> list2 = new ArrayList<IGroceryItem>();
		list2.add(new GroceryItemBD("name1", "category1", 1.0));
		list2.add(new GroceryItemBD("name2", "category2", 5.));

		ArrayList<IGroceryItem> list3 = new ArrayList<IGroceryItem>();
		list3.add(new GroceryItemBD("name1", "category1", 1.0));

		_instance = new GroceryControlBackend(new RedBlackTreeBD(list1, list2, list3), new GroceryCart());

		// No filters set
		// Full name of item
		GroceryItemBD item1 = (GroceryItemBD) _instance.searchByKeyword("name1").get(0); // Should only be one item in
																							// arraylist
		assertEquals(item1.getName(), "name1");
		assertEquals(item1.getCategory(), "category1");
		assertEquals(item1.getPrice(), 1.);
		assertEquals(1, _instance.searchByKeyword("name1").size());
		// Part of the name of the item
		item1 = (GroceryItemBD) _instance.searchByKeyword("e1").get(0); // Should only be one item in arraylist
		assertEquals(item1.getName(), "name1");
		assertEquals(item1.getCategory(), "category1");
		assertEquals(item1.getPrice(), 1.);
		assertEquals(1, _instance.searchByKeyword("name1").size());

		// Filters set and not set
		// No price filter, no category filter
		item1 = (GroceryItemBD) _instance.searchByKeyword("name2").get(0);
		GroceryItemBD item2 = (GroceryItemBD) _instance.searchByKeyword("name2").get(1);
		assertEquals(item1.getName(), "name2");
		assertEquals(item1.getCategory(), "category2");
		assertEquals(item1.getPrice(), 5);
		assertEquals(item2.getName(), "name2 duplicate");
		assertEquals(item2.getCategory(), "category1");
		assertEquals(item2.getPrice(), 100);
		assertEquals(2, _instance.searchByKeyword("name2").size());
		// Price filter set, no category filter
		_instance.setPriceFilter(6);
		item1 = (GroceryItemBD) _instance.searchByKeyword("name2").get(0);
		assertEquals(item1.getName(), "name2");
		assertEquals(item1.getCategory(), "category2");
		assertEquals(item1.getPrice(), 5);
		assertEquals(1, _instance.searchByKeyword("name2").size());
		// No price filter, category filter
		_instance.setPriceFilter(Integer.MAX_VALUE);
		_instance.setCategoryFilter("category2");
		item1 = (GroceryItemBD) _instance.searchByKeyword("name2").get(0);
		assertEquals(item1.getName(), "name2");
		assertEquals(item1.getCategory(), "category2");
		assertEquals(item1.getPrice(), 5);
		assertEquals(1, _instance.searchByKeyword("name2").size());
		// again
		_instance.setCategoryFilter("category1");
		item1 = (GroceryItemBD) _instance.searchByKeyword("name2").get(0);
		assertEquals(item1.getName(), "name2 duplicate");
		assertEquals(item1.getCategory(), "category1");
		assertEquals(item1.getPrice(), 100);
		assertEquals(1, _instance.searchByKeyword("name2").size());
		// both filters set
		_instance.setPriceFilter(6);
		assertEquals(new ArrayList<IGroceryItem>(), _instance.searchByKeyword("name2"));

		_instance.setCategoryFilter("category2");
		item1 = (GroceryItemBD) _instance.searchByKeyword("name2").get(0);
		assertEquals(item1.getCategory(), "category2");
		assertEquals(item1.getPrice(), 5);
		assertEquals(1, _instance.searchByKeyword("name2").size());

	}
	
	@Test
    /**
     * Test method for checking if rbt insert properly rotates with cases 2 into
     * case 1
     */
    public void backendDeveloperIntegrationTest1() {
        // Case 2 into Case 1
        RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
        IGroceryItem item1 = new GroceryItem("item1", "cat1", 10, 1);
        IGroceryItem item2 = new GroceryItem("item2", "cat2", 8, 1);
        IGroceryItem item3 = new GroceryItem("item3", "cat3", 9, 1);
        testerTree.insert(item1);
        testerTree.insert(item2);
        testerTree.insert(item3);

        assertEquals("[ (item3, cat3, 9.0, 1), (item2, cat2, 8.0, 1), (item1, cat1, 10.0, 1) ]",
                testerTree.toLevelOrderString());

        testerTree = new RedBlackTree<IGroceryItem>();
        IGroceryItem item4 = new GroceryItem("item4", "cat1", 10, 1);
        IGroceryItem item5 = new GroceryItem("item5", "cat1", 12, 1);
        IGroceryItem item6 = new GroceryItem("item6", "cat1", 11, 1);
        testerTree.insert(item4);
        testerTree.insert(item5);
        testerTree.insert(item6);

        assertEquals("[ (item6, cat1, 11.0, 1), (item4, cat1, 10.0, 1), (item5, cat1, 12.0, 1) ]",
                testerTree.toLevelOrderString());
    }

    @Test
    /**
     * Test method for checking if rbt insert works properly/rotates properly with
     * 10 randomly priced items
     */
    public void backendDeveloperIntegrationTest2() {
        IGroceryItem items[] = { new GroceryItem("item1", "cat1", 62, 1), new GroceryItem("item1", "cat1", 72, 1),
                new GroceryItem("item1", "cat1", 68, 1), new GroceryItem("item1", "cat1", 89, 1),
                new GroceryItem("item1", "cat1", 35, 1), new GroceryItem("item1", "cat1", 63, 1),
                new GroceryItem("item1", "cat1", 86, 1), new GroceryItem("item1", "cat1", 69, 1),
                new GroceryItem("item1", "cat1", 77, 1), new GroceryItem("item1", "cat1", 1, 1),
                new GroceryItem("item1", "cat1", 25, 1), new GroceryItem("item1", "cat1", 41, 1) };

        RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
        for (IGroceryItem item : items) {
            testerTree.insert(item);
        }

        assertEquals(
                "[ (item1, cat1, 68.0, 1), (item1, cat1, 62.0, 1), (item1, cat1, 86.0, 1), (item1, cat1, 25.0, 1), (item1, cat1, 63.0, 1), (item1, cat1, 72.0, 1), (item1, cat1, 89.0, 1), (item1, cat1, 1.0, 1), (item1, cat1, 35.0, 1), (item1, cat1, 69.0, 1), (item1, cat1, 77.0, 1), (item1, cat1, 41.0, 1) ]",
                testerTree.toLevelOrderString());

    }


}
