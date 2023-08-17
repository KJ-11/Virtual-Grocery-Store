import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {

  /**
   * Test method for checking if the tree self balances correctly and works correctly when inserting
   * various groceryItems
   */
  @Test
  public void test1() {
    RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
    IGroceryItem apple = new GroceryItemAE("apple", "fruits", 0.99, 5);
    IGroceryItem banana = new GroceryItemAE("banana", "fruits", 1.99, 5);
    IGroceryItem celery = new GroceryItemAE("celery", "vegetables", 2.99, 5);
    IGroceryItem lettuce = new GroceryItemAE("lettuce", "vegetables", 3.99, 5);
    IGroceryItem tomato = new GroceryItemAE("tomato", "vegetables", 4.99, 5);
    testerTree.insert(apple);
    testerTree.insert(banana);
    testerTree.insert(celery);
    testerTree.insert(lettuce);
    testerTree.insert(tomato);
    String expected = "[ banana, apple, lettuce, celery, tomato ]";
    String actual = "[ ";
    actual = actual + testerTree.root.data.getName() + ", ";
    actual = actual + testerTree.root.leftChild.data.getName() + ", ";
    actual = actual + testerTree.root.rightChild.data.getName() + ", ";
    actual = actual + testerTree.root.rightChild.leftChild.data.getName() + ", ";
    actual = actual + testerTree.root.rightChild.rightChild.data.getName() + " ";
    actual = actual + "]";

    Assertions.assertEquals(expected, actual,
        "Test1 failed: tree does not look as expected(levelOrderString) "
            + "when a few grocery items have been added.");
    Assertions.assertEquals(1, testerTree.root.blackHeight,
        "Test1 failed: root color does not equal black.");
    Assertions.assertEquals(1, testerTree.root.rightChild.blackHeight,
        "Test1 failed: node lettuce doesn't have the correct color.");
    Assertions.assertEquals(0, testerTree.root.rightChild.leftChild.blackHeight,
        "Test1 failed: node celery doesn't have the correct color.");
    Assertions.assertEquals(0, testerTree.root.rightChild.rightChild.blackHeight,
        "Test1 failed: node tomato doesn't have the correct color.");
  }

  /**
   * Test method for checking if the remove method works correctly and decrements the amount
   * available of a pre-existing item in the tree
   */
  @Test
  public void test2() {
    RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
    IGroceryItem apple = new GroceryItemAE("apple", "fruits", 0.99, 5);
    testerTree.insert(apple);
    testerTree.remove(apple);
    int expected = 4;
    int actual = testerTree.root.data.getAmount();
    Assertions.assertEquals(expected, actual,
        "Test2 failed: tree does not return the expected amount available after removing an item.");
    Assertions.assertEquals(1, testerTree.root.blackHeight,
        "Test2 failed: root color does not equal black.");

    IGroceryItem banana = new GroceryItemAE("banana", "fruits", 1.99, 5);
    IGroceryItem celery = new GroceryItemAE("celery", "vegetables", 2.99, 5);

    testerTree.insert(banana);
    testerTree.insert(celery);
    testerTree.remove(celery);
    expected = 4;
    actual = testerTree.root.rightChild.data.getAmount();
    Assertions.assertEquals(expected, actual,
        "Test2 failed: tree does not return the expected amount available after removing an item.");
  }

  /**
   * Test method for checking if the tree works correctly and increments the amount available of a
   * pre-existing item in the tree
   */
  @Test
  public void test3() {
    RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
    IGroceryItem apple = new GroceryItemAE("apple", "fruits", 0.99, 5);
    testerTree.insert(apple);
    testerTree.insert(apple);
    int expected = 6;
    int actual = testerTree.root.data.getAmount();
    Assertions.assertEquals(expected, actual,
        "Test3 failed: tree does not return the expected amount available after "
            + "inserting a duplicate item.");
    Assertions.assertEquals(1, testerTree.root.blackHeight,
        "Test3 failed: root color does not equal black.");

    IGroceryItem banana = new GroceryItemAE("banana", "fruits", 1.99, 5);
    IGroceryItem celery = new GroceryItemAE("celery", "vegetables", 2.99, 5);

    testerTree.insert(banana);
    testerTree.insert(celery);
    testerTree.insert(celery);
    expected = 6;
    actual = testerTree.root.rightChild.data.getAmount();
    Assertions.assertEquals(expected, actual,
        "Test3 failed: tree does not return the expected amount available after inserting"
            + " a duplicate item.");
  }

  /**
   * Test method for checking if the size and isEmpty methods work as intended.
   */
  @Test
  public void test4() {
    RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
    IGroceryItem apple = new GroceryItemAE("apple", "fruits", 0.99, 5);
    IGroceryItem banana = new GroceryItemAE("banana", "fruits", 1.49, 5);

    int expected = 0;
    int actual = testerTree.size();
    boolean expected_2 = true;
    boolean actual_2 = testerTree.isEmpty();

    Assertions.assertEquals(expected, actual,
        "Test4: tree does not return the expected size when called on an empty tree.");
    Assertions.assertEquals(expected_2, actual_2,
        "Test4: isEmpty method does not return true when called on an empty tree.");

    testerTree.insert(apple);
    expected = 1;
    expected_2 = false;
    actual = testerTree.size();
    actual_2 = testerTree.isEmpty();

    Assertions.assertEquals(expected, actual,
        "Test4: tree does not return the expected size when called on a tree with 1 item.");
    Assertions.assertEquals(expected_2, actual_2,
        "Test4: isEmpty method does not return false when called on a tree with 1 item.");

    // tests if duplicate item doesn't create a duplicate node, only updates the
    // 'amount available'
    testerTree.insert(apple);
    expected = 1;
    expected_2 = false;
    actual = testerTree.size();
    actual_2 = testerTree.isEmpty();

    Assertions.assertEquals(expected, actual,
        "Test4: tree does not return the expected size when called on a tree with 1 item.");
    Assertions.assertEquals(expected_2, actual_2,
        "Test4: isEmpty method does not return false when called on a tree with 1 item.");

    testerTree.insert(banana);
    expected = 2;
    expected_2 = false;
    actual = testerTree.size();
    actual_2 = testerTree.isEmpty();

    Assertions.assertEquals(expected, actual,
        "Test4: tree does not return the expected size when called on a tree with 2 items.");
    Assertions.assertEquals(expected_2, actual_2,
        "Test4: isEmpty method does not return false when called on a tree with 2 items.");

  }

  /**
   * Test method for checking if the filter method works as intended when there are items in both
   * subtrees of a particular node
   */
  @Test
  public void test5() {
    RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
    IGroceryItem apple = new GroceryItemAE("apple", "fruits", 0.99, 5);
    IGroceryItem banana = new GroceryItemAE("banana", "fruits", 1.49, 5);
    IGroceryItem celery = new GroceryItemAE("celery", "vegetables", 0.49, 5);
    IGroceryItem lettuce = new GroceryItemAE("lettuce", "vegetables", 0.25, 5);
    IGroceryItem tomato = new GroceryItemAE("tomato", "vegetables", 0.75, 5);
    testerTree.insert(apple);
    testerTree.insert(banana);
    testerTree.insert(celery);
    testerTree.insert(lettuce);
    testerTree.insert(tomato);
    ArrayList<IGroceryItem> expected = new ArrayList<IGroceryItem>();
    expected.add(apple);
    expected.add(celery);
    expected.add(lettuce);
    expected.add(tomato);
    ArrayList<IGroceryItem> actual = testerTree.filter(0.99);

    Assertions.assertEquals(expected, actual,
        "Test4 failed: filter method doesn't return the expected arraylist of groceryitems "
            + "when called on a tree with various items");
  }

  @Test
  /**
   * Integration test 1
   */
  public void integrationTest1() {

    // tests if turkey, bananas, and chili powder get searched correctly in the rbt and added to the
    // cart

    RedBlackTree<IGroceryItem> rbt = new RedBlackTree();
    GroceryItemLoader itemLoader = new GroceryItemLoader();
    ArrayList<IGroceryItem> items = null;
    try {
      items = itemLoader.loadGroceryItems("groceryItems.xml");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    for (IGroceryItem item : items) {
      rbt.insert(item);
    }

    GroceryControlBackend backend = new GroceryControlBackend(rbt, new GroceryCart());
    ArrayList<IGroceryItem> expected = new ArrayList<IGroceryItem>();
    expected.add(backend.searchByKeyword("Turkey").get(0));
    expected.add(backend.searchByKeyword("Bananas").get(0));
    expected.add(backend.searchByKeyword("Chili Powder").get(0));

    try {
      backend.getItem(backend.searchByKeyword("Turkey").get(0));
      backend.getItem(backend.searchByKeyword("Bananas").get(0));
      backend.getItem(backend.searchByKeyword("Chili Powder").get(0));
      for (int i = 0; i < backend.returnItems().size(); i++) {
        Assertions.assertEquals(expected.get(i).getName(), backend.returnItems().get(i).getName());
        Assertions.assertEquals(expected.get(i).getPrice(),
            backend.returnItems().get(i).getPrice());
        Assertions.assertEquals(expected.get(i).getCategory(),
            backend.returnItems().get(i).getCategory());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  /**
   * Integration test 2
   */
  public void integrationTest2() {

    // test method for seeing if remove method works correctly in the cart

    RedBlackTree<IGroceryItem> rbt = new RedBlackTree();
    GroceryItemLoader itemLoader = new GroceryItemLoader();
    ArrayList<IGroceryItem> items = null;
    try {
      items = itemLoader.loadGroceryItems("groceryItems.xml");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    for (IGroceryItem item : items) {
      rbt.insert(item);
    }

    GroceryControlBackend backend = new GroceryControlBackend(rbt, new GroceryCart());
    ArrayList<IGroceryItem> expected = new ArrayList<IGroceryItem>();

    expected.add(backend.searchByKeyword("Spinach").get(0));
    expected.add(backend.searchByKeyword("Onions").get(0));
    expected.add(backend.searchByKeyword("Honey Mustard").get(0));

    try {
      backend.getItem(backend.searchByKeyword("Spinach").get(0));
      backend.getItem(backend.searchByKeyword("Onions").get(0));
      backend.getItem(backend.searchByKeyword("Honey Mustard").get(0));
      for (int i = 0; i < backend.returnItems().size(); i++) {
        Assertions.assertEquals(expected.get(i).getName(), backend.returnItems().get(i).getName());
        Assertions.assertEquals(expected.get(i).getPrice(),
            backend.returnItems().get(i).getPrice());
        Assertions.assertEquals(expected.get(i).getCategory(),
            backend.returnItems().get(i).getCategory());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    int value_before = backend.searchByKeyword("Honey Mustard").get(0).getAmount();

    backend.returnItem(backend.returnItems().get(2)); // removes honey mustard from cart and adds
                                                      // back to
                                                      // store

    Assertions.assertEquals(value_before + 1,
        backend.searchByKeyword("Honey Mustard").get(0).getAmount());

  }

  @Test
  /**
   * Code Review test 1 of backend developer
   */
  public void CodeReviewOfBackendDeveloper1() {
    // tests searchByFilters method

    ArrayList<IGroceryItem> expected = new ArrayList<IGroceryItem>();
    expected.add(new GroceryItem("apple", "fruits", 0.99, 5));
    expected.add(new GroceryItem("banana", "fruits", 0.49, 5));
    expected.add(new GroceryItem("celery", "vegetables", 1.49, 5));



    RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
    testerTree.insert(expected.get(0));
    testerTree.insert(expected.get(1));
    testerTree.insert(expected.get(2));

    GroceryControlBackend backend = new GroceryControlBackend(testerTree, new GroceryCart());


    // case 1: no filters set, should return everything in list
    Assertions.assertEquals(backend.searchByFilters(), expected);

    // case 2: category filter set
    backend.setCategoryFilter("vegetables");
    ArrayList<IGroceryItem> expected4 = new ArrayList<IGroceryItem>();
    expected4.add(expected.get(2));
    Assertions.assertEquals(backend.searchByFilters(), expected4);

    backend.setCategoryFilter("fruits");
    ArrayList<IGroceryItem> expected2 = new ArrayList<IGroceryItem>();
    expected2.add(expected.get(0));
    expected2.add(expected.get(1));
    Assertions.assertEquals(backend.searchByFilters(), expected2);

    // case 3: both price and category filter set
    backend.setPriceFilter(0.99);
    ArrayList<IGroceryItem> expected3 = new ArrayList<IGroceryItem>();
    expected3.add(expected.get(0));
    expected3.add(expected.get(1));
    Assertions.assertEquals(backend.searchByFilters(), expected3);
  }

  @Test
  /**
   * Code Review test 1 of backend developer
   */
  public void CodeReviewOfBackendDeveloper2() {
    // tests searchByKeyword method
    ArrayList<IGroceryItem> expected1 = new ArrayList<IGroceryItem>();
    ArrayList<IGroceryItem> expected2 = new ArrayList<IGroceryItem>();
    ArrayList<IGroceryItem> expected3 = new ArrayList<IGroceryItem>();

    IGroceryItem item1 = new GroceryItem("name_one", "category_one", 1.0, 5);

    expected2.add(item1);

    expected3.add(item1);
    expected3.add(new GroceryItem("name_two", "category_two", 0.5, 5));
    expected3.add(new GroceryItem("name_three", "category_three", 1.5, 5));


    RedBlackTree<IGroceryItem> testerTree = new RedBlackTree<IGroceryItem>();
    testerTree.insert(expected3.get(0));
    testerTree.insert(expected3.get(1));
    testerTree.insert(expected3.get(2));


    GroceryControlBackend backend = new GroceryControlBackend(testerTree, new GroceryCart());

    // case 1: no items match
    Assertions.assertEquals(expected1, backend.searchByKeyword("Apple"));

    // case 2: one item matches
    Assertions.assertEquals(expected2, backend.searchByKeyword("name_one"));

    // case 3: multiple matches
    Assertions.assertEquals(expected3, backend.searchByKeyword("name"));


  }

}
