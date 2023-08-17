// --== CS400 Project One File Header ==--
// Name: Sreyas Srivastava
// CSL Username: sreyas
// Email: sssrivastav2@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads all data for the grocery items from an XML file
 * 
 * @author Sreyas Srivastava
 * 
 */
public class GroceryItemLoader implements IGroceryItemLoader {
	
	/**
     * This method loads the list of grocery items from an XML file.
     * @param filepathToXML path to the XML file relative to the executable
     * @return an ArrayList of grocery item objects
     * @throws FileNotFoundException if the XML file is not found
     */
	@Override
	public ArrayList<IGroceryItem> loadGroceryItems(String filepathToXML) throws FileNotFoundException {
		Scanner scnr = new Scanner(new File(filepathToXML));
		ArrayList<IGroceryItem> groceryItems = new ArrayList<>(); // list of all groceryItem objects
		
		while (scnr.hasNext()) {
			if (scnr.nextLine().equals("<item>")) {
				String name = scnr.nextLine().strip().replaceAll("<name>", "")
						.replaceAll("</name>", "");
				String category = scnr.nextLine().strip().replaceAll("<category>", "")
						.replaceAll("</category>", "");
				double price = Double.parseDouble(scnr.nextLine().strip()
						.replaceAll("<price>", "").replaceAll("</price>", ""));
				int amount = Integer.parseInt(scnr.nextLine().strip()
						.replaceAll("<amount>", "").replaceAll("</amount>", ""));
				GroceryItem groceryItem = new GroceryItem(name, category, price, amount);
				groceryItems.add(groceryItem);
			}
		}
		return groceryItems;
	}
}
