// --== CS400 Project Two File Header ==--
// Name: Henry Burke
// CSL Username: hburke
// Email: hpburke@wisc.edu
// Lecture #: 001 @11:00am
// Notes to Grader: hopefully this cart doesn't have squeaky wheels

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Frontend to grocery store application.
 */
public class GroceryControlFrontend implements IGroceryControlFrontend {

	private IGroceryControlBackend backend;

	public GroceryControlFrontend(IGroceryControlBackend backend) {
		this.backend = backend;
	}

	public GroceryControlFrontend() {
		this.backend = new GroceryControlBackendFD();
	}

	private Scanner inputScanner = new Scanner(System.in); // initialize user input scanner
	private IGroceryCart cart = new GroceryCart(); // initialize grocery cart array

	/**
	 * Use user input to send user to different menus.
	 */
	@Override
	public void runCommandLoop() {
		try {
			System.out.println("Welcome to the grocery store! This is your cart."
					+ "Take a look around, see what looks good." + "\n---/x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x/---");
			boolean appUsed = true;
			while (appUsed) {
				displayMainMenu();
				int input = inputScanner.nextInt();

				if (input == 1) {
					inputScanner.nextLine();
					searchByItemName();
				} else if (input == 2) {
					inputScanner.nextLine();
					setPriceFilter();
				} else if (input == 3) {
					inputScanner.nextLine();
					setCategoryFilter();
				} else if (input == 4) {
					filterSearch();
				} else if (input == 5) {
					inputScanner.nextLine();
					addCartItem();
				} else if (input == 6) {
					inputScanner.nextLine();
					removeCartItem();
				} else if (input == 7) {
					printCart();
				} else if (input != 8) {
					System.out.println("Please enter a valid integer option\n");
				} else if (input == 8) {
					appUsed = false;
				}
			}
			System.out.println("Thank you for shopping with us!");
			System.out.println("You bought: " + backend.returnItems().toString());
			double output = Math.floor(backend.returnPrice() * 100) / 100;
			System.out.println("Total Cart Price: $" + output);

			inputScanner.close();
		} catch (InputMismatchException e) {
			inputScanner.close();
			System.out.println("Invalid Input");
		}
	}

	/**
	 * Displays the main menu to the user.
	 */
	@Override
	public void displayMainMenu() {
		System.out.print("\nYou are looking down the aisles:\n" + "          1) Search for item by keyword\n"
				+ "          2) Set price filter\n" + "          3) Set category filter\n"
				+ "          4) Search by set filters\n" + "\n" + "          5) Add an item to your cart\n"
				+ "          6) Remove an item from your cart\n" + "          7) Look through your cart\n"
				+ "          8) Leave the store\n");
	}

	/**
	 * Searches the store for a specific item by name.
	 */
	@Override
	public void searchByItemName() {
		String input;
		System.out.print("What item would you like to search for?\n");
		input = inputScanner.nextLine();
		String output = "";

		for (int i = 0; i < backend.searchByKeyword(input).size(); i++) {
			output += backend.searchByKeyword(input).get(i).getName() + ", ";
		}

		if (output.length() != 0) {
			output = output.substring(0, output.length() - 2);
			System.out.println("The store has " + output.toLowerCase() + " in stock.");
		} else {
			System.out.println("Item is not available in this store.");
		}
	}

	/**
	 * Searches the store for any items under a specific price.
	 */
	@Override
	public void setPriceFilter() {
		double input;
		System.out.print("What is the maximum price you would like to pay?\n");
		input = inputScanner.nextDouble();

		if (input >= 0) {
			backend.setPriceFilter(input);
		} else {
			System.out.println("Invalid price");
		}
	}

	/**
	 * Sets a category filter to search for items by.
	 */
	@Override
	public void setCategoryFilter() {
		String input;
		System.out.print("What category of items would you like to see?\n");
		input = inputScanner.nextLine();

		backend.setCategoryFilter(input);
	}

	/**
	 * Searches the store based on your set filters.
	 */
	@Override
	public void filterSearch() {
		String output = "";
		ArrayList<IGroceryItem> filtered = backend.searchByFilters();
		for (int i = 0; i < filtered.size(); i++) {
			output += filtered.get(i).getName() + "\n";
		}

		if (output.length() == 0) {
			System.out.println("No items fit your filters");
		} else {
			System.out.println("These items fit your filters: ");
			System.out.println(output.substring(0, output.length() - 1));
		}
	}

	/**
	 * Adds an item to the cart array.
	 */
	@Override
	public void addCartItem() {
		String input;
		System.out.print("What item would you like to add to your cart?\n");
		input = inputScanner.nextLine().toUpperCase();

		ArrayList<IGroceryItem> list = (backend.searchByKeyword(input));

		boolean exist = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().toUpperCase().equals(input.toUpperCase())) {
				try {
					backend.getItem(list.get(i));
				} catch (Exception e) {
					System.out.println("Store is out of stock");
				}
				System.out.println("Item added to cart.");
				exist = true;
			}
		}
		if (!exist) {
			System.out.println("Item does not exist.");
		}
	}

	/**
	 * Removes an item from the grocery cart.
	 */
	@Override
	public void removeCartItem() {
		String input;
		System.out.print("What item would you like to remove to your cart?\n");
		input = inputScanner.nextLine().toUpperCase();

		ArrayList<IGroceryItem> list = (backend.returnItems());

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().toUpperCase().equals(input)) {
				backend.returnItem(list.get(i));
				System.out.println("Item removed.");
			} else {
				System.out.println("This item is not in your cart.");
			}
		}
	}

	/**
	 * Prints out the cart items.
	 */
	@Override
	public void printCart() {
		ArrayList<IGroceryItem> output = backend.returnItems();
		System.out.println(output.size());
		String print = "";

		for (int i = 0; i < output.size(); i++) {
			print += (output.get(i).getName()) + ", ";
		}

		if (print.length() <= 2) {
			System.out.println("Cart is empty.");
		} else {
			System.out.println(print.substring(0, print.length() - 2));
		}
	}
}
