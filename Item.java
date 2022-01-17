public class Item {
	private String name;
	private int quantity;
	
	public Item(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public Item(String name) {
		this.name = name;
	}

	public String toString() {
		return "Name: " + name + "\tQuantity: " + quantity;
	}

	public boolean equals(Object other) {
		Item otherItem = (Item) other;
		
		if (this.name.equals(otherItem.name))
        {
            return true;
        }
        return false;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}