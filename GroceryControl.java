import java.util.ArrayList;
import java.io.FileNotFoundException;

public class GroceryControl {
  public static void main(String[] args) {
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
    GroceryControlFrontend frontend = new GroceryControlFrontend(backend);

    frontend.runCommandLoop();
  }
}
