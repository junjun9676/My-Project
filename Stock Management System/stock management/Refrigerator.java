
public class Refrigerator extends Product {
	private String doorDesign;
	private String color;
	private int capacity;


	//Parameterized constructor
	public Refrigerator(int itemNum, String itemName, int itemQuantity, double itemPrice,
			String doorDesign, String color, int capacity) {
		
		super(itemNum, itemName, itemQuantity, itemPrice);
		
		 this.setDoorDesign(doorDesign);
		 this.setColor(color);
		 this.setCapacity(capacity);
	}
	
	//Default constructor
	public Refrigerator() {
		this(0, "unknown", 0, 0, "unknown", "unknown", 0);
		this.setItemStatus(true);
		this.doorDesign = "";
	}
	
	//Getter-Accessor methods
	public String getDoorDesign() {
		return doorDesign;
	}
	public void setDoorDesign(String doorDesign) {
		this.doorDesign = doorDesign;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return (
			"Item number\t\t: #" + super.getItemNum() +
			"\nProduct name\t\t: " + super.getItemName() +
			"\nDoor design\t\t: " + getDoorDesign() +
			"\nColor\t\t: " + getColor() +
			"\nCapacity (in Litres)\t: " + getCapacity() + "L" +
			"\nQuantity available\t: " + super.getItemQuantity() + " units"+
			"\nPrice (RM)\t\t: RM" + super.getItemPrice() +
			"\nInventory value (RM)\t: RM" + super.getTotalInvVal() +
			"\nProduct status\t\t: " + super.getItemStatus()
		);
	}
}
	
