
public class GroceryItemBD implements IGroceryItem {
	String name;
	String category;
	double price;
	
	public GroceryItemBD(String name, String category, double price) {
		this.name = name;
		this.category = category;
		this.price = price;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return category;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return 1;
	}

        @Override
	public void setAmount(int amt) {
		// TODO Auto-generated method stub
	}

	@Override
	public int compareTo(IGroceryItem item) {
		// TODO Auto-generated method stub
		return 0;
	}
}
