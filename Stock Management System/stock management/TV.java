
public class TV extends Product {
	private String screenType;
	private String resolution;
	private int displaySize;
	
	
	//Parameterized constructor
	public TV(int itemNum, String itemName, int itemQuantity, double itemPrice, 
			String screenType, String resolution, int displaySize) {
		
		super(itemNum, itemName, itemQuantity, itemPrice);
		
		this.setScreenType(screenType);
		this.setResolution(resolution);
	    this.setDisplaySize(displaySize); 		
	}
	
	//Default constructor
	public TV() {
		this(0, "unknown", 0, 0, "unknown", "unknown", 0);
		 this.setItemStatus(true);
	}
	
	//Getter-Accessor methods
	public String getScreenType() {
		return screenType;
	}
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public int getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}
	
	@Override
	public String toString() {
		return (
			"Item number\t\t: #" + super.getItemNum() +
			"\nProduct name\t\t: " + super.getItemName() +
			"\nScreen type\t\t: " + getScreenType() +
			"\nResolution\t\t: " + getResolution() +
			"\nDisplay size: \t " + getDisplaySize() +
			"\nQuantity available\t: " + super.getItemQuantity() + " units"+
			"\nPrice (RM)\t\t: RM" + super.getItemPrice() +
			"\nInventory value (RM)\t: RM" + super.getTotalInvVal() +
			"\nProduct status\t\t: " + super.getItemStatus()
		);
	}

	
}
