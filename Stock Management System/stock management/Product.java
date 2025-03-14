
public abstract class Product {
	private int itemNum; 
	private String itemName; 
	private int itemQuantity; 
	private double itemPrice; 
	private boolean itemStatus; 
	
	
	//Parameterized constructor
	public Product(int itemNum, String itemName, int itemQuantity, double itemPrice){
		if(itemNum>999999 ||itemNum<0) {
			this.itemNum = 0;
			System.out.println("Please ensure product number is within 0 - 999999");
		}
		else {
			this.itemNum = itemNum; 
		}
		this.itemName = itemName;
		if(itemQuantity <0) {
			this.itemQuantity = 0; 
			System.out.println("Invalid item quantitiy");
		}
		else {
			this.itemQuantity = itemQuantity;
		}
		if(itemPrice <0) {
			this.itemPrice = 0; 
			System.out.println("Invalid price provided!");
		}
		else {
			this.itemPrice = itemPrice; 
		}
		this.itemStatus = true; 
	}
	
	//Default constructor
	public Product() {
		this(0,"unknown",0,0);
		itemStatus =true; 
	}
	
	//Getter-Accessor methods	
	public int getItemNum() {
		return itemNum; 
	}
	public String getItemName() {
		return itemName; 
	}
	public int getItemQuantity() {
		return itemQuantity; 
	}
	public double getItemPrice() {
		return itemPrice; 
	}
	public String getItemStatus() {
		if(itemStatus) {
			return("Available");
		}
		else
			return("Discontinued");
	}
	
	//Setter-Mutator methods
	public void setItemNum(int itemNum) {
		if(itemNum>999999 ||itemNum<0) {
			System.out.println("Please ensure product number is within 0 - 999999");
		}
		else {
			this.itemNum = itemNum; 
		}
	}
	public void setItemName(String itemName) {
		this.itemName = itemName; 
	}
	public void setItemQuantity(int itemQuantity) {
		if(itemQuantity <0) {
			System.out.println("Invalid item quantitiy");
		}
		else {
			this.itemQuantity = itemQuantity;
		}
	}
	public void setItemPrice(double itemPrice) {
		if(itemPrice <0) {
			System.out.println("Invalid price provided!");
		}
		else {
			this.itemPrice = itemPrice; 
		}
	}
	public void setItemStatus(boolean itemStatus) {
		this.itemStatus = itemStatus; 
	}
	
	//Retrieve inventory value
	public double getTotalInvVal() {
		return (itemQuantity*itemPrice); 
	}
	
	//Add stock amount
	public void add(int addition) {
		if(itemStatus == true) {
			itemQuantity+=addition;
		}
		else
			System.out.println("Error\nItem discontinued!");
	}
	
	//Deduct stock amount
	public void deduct(int deduction) {
		if(itemStatus == true) {
			itemQuantity-=deduction; 
			if (itemQuantity <0) {
				itemQuantity = 0; 
			}
		}
		else
			System.out.println("Error\nItem discontinued!");
		
	}

	
	public String toString() {
		return (
				"Item number\t\t: #"+getItemNum() +
				"\nProduct name\t\t: "+ getItemName() +
				"\nQuantity available\t: "+getItemQuantity() +" units"+
				"\nPrice(RM)\t\t: RM" + getItemPrice() +
				"\nInventory value (RM)\t: RM"+ getTotalInvVal() +
				"\nProduct status\t\t: "+ getItemStatus());
	}
}
