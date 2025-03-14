import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;


public class StockManagement {
    

    public static int getMaxProducts(Scanner scanner) {
        int maxProducts;
        do {
            System.out.print("Enter the maximum number of products to store in the system: ");
            maxProducts = scanner.nextInt();
        } while (maxProducts < 0);
        return maxProducts;
    }

   
    public static Product[] addProduct(Product[] products, Scanner scanner) {
        String productName;
        Refrigerator refrigerator = new Refrigerator();
        System.out.println("\nHow many products would you like to add?");
        int numProducts = scanner.nextInt();
        scanner.nextLine(); 

        products = Arrays.copyOf(products, products.length + numProducts);

        for (int i = products.length - numProducts; i < products.length; i++) {
            System.out.println("\nPlease select a product to add:");
            System.out.println("1. Refrigerator");
            System.out.println("2. TV");

            int addProduct = scanner.nextInt();
            scanner.nextLine(); 

            switch (addProduct) {
                case 1:
                    productName = "Refrigerator";
                    System.out.println(productName);
                    System.out.println("Please enter the refrigerator itemNum:");
                    int itemNum = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    System.out.println("Please enter the refrigerator name:");
                    String itemName = scanner.nextLine();

                    System.out.println("Please enter the refrigerator quantity:");
                    int itemQuantity = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    System.out.println("Please enter the refrigerator price:");
                    double itemPrice = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character

                    System.out.println("Please enter the refrigerator door design:");
                    String doorDesign = scanner.nextLine();

                    System.out.println("Please enter the refrigerator color:");
                    String color = scanner.nextLine();

                    System.out.println("Please enter the refrigerator capacity:");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    refrigerator.setDoorDesign(doorDesign);
                    refrigerator.setColor(color);
                    refrigerator.setCapacity(capacity);

                    // Create new refri object with input values
                    products[i] = new Refrigerator(itemNum, itemName, itemQuantity, itemPrice, doorDesign, color, capacity);
                    System.out.println(products[i]);

                    break;
                case 2:
                    productName = "TV";
                    System.out.println(productName);
                    // Prompt user to enter TV details
                    System.out.println("Please enter the TV itemNum:");
                    int newItemNum = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character
                    System.out.println("Please enter the TV name:");
                    String newItemName = scanner.nextLine();
                    System.out.println("Please enter the TV quantity:");
                    int newItemQuantity = scanner.nextInt();
                    System.out.println("Please enter the TV price:");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); 		// buffer
                    System.out.println("Please enter the screen type:");
                    String newScreenType = scanner.nextLine();
                    System.out.println("Please enter the resolution:");
                    String newResolution = scanner.nextLine();
                    System.out.println("Please enter the display size:");
                    int newDisplaySize = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character
                    
                    products[i] = new TV(newItemNum, newItemName, newItemQuantity, newPrice, newScreenType, newResolution, newDisplaySize);
                    System.out.println(products[i]);
                  
                    break;
              
                
                
                default:
                    System.out.println("Invalid choice. Please try again. Only number 1 or 2 allowed!");
                    break;
            }
        }
        
        System.out.println("Product(s) added successfully.");
        System.out.println("\n");
        return products;
    }
    
    
    
    
    public static int displayMenu(Scanner scanner) {
        System.out.println("1. View products");
        System.out.println("2. Add stock");
        System.out.println("3. Deduct stock");
        System.out.println("4. Discontinue product");
        System.out.println("0. Exit");
        int choice;
        do {
            System.out.print("Please enter a menu option: ");
            choice = scanner.nextInt();
        } while (choice < 0 || choice > 4);
        return choice;
    
    }
    
    public static void viewProducts(Product[] products) {
    
         for (int i = 1; i < products.length; i++) {
            System.out.println(products[i]);
            System.out.println("\n");
        }
    }
    
    public static void addStock(Product[] products, Scanner scanner) {
        int numProducts, newStock, currentStock = 0;
        
        do {
            System.out.print("Enter the number of products to add stock to: ");
            numProducts = scanner.nextInt();
        } while (numProducts < 0);

        for (int i = 0; i < numProducts; i++) {
            System.out.print("Enter the ID of the product to add stock to: ");
            int num = scanner.nextInt();
            boolean found = false;

            for (Product product : products) {
                if (product != null && product.getItemNum() == num) {
                    currentStock = product.getItemQuantity();

                    do {
                        System.out.printf("Enter the amount of stock to add to product %d (current stock: %d): ", num, currentStock);
                        newStock = scanner.nextInt();
                        product.add(newStock);
                    } while (newStock < 0);

                    System.out.printf("Stock updated: product " + product.getItemName() + " now has %d units in stock.\n \n", product.getItemQuantity());
                    found = true;
                    break; // exit the loop when product found
                }
            }

            if (!found) {
                System.out.println("Invalid item number. Please try again");
            }
        }
    }

       	

    public static void deductStock(Product[] products, Scanner scanner) {
        int numProducts, newStock, currentStock = 0;
        
        do {
            System.out.print("Enter the number of products to deduct stock from: ");
            numProducts = scanner.nextInt();
        } while (numProducts < 0);

        for (int i = 0; i < numProducts; i++) {
            System.out.print("Enter the ID of the product to deduct stock from: ");
            int num = scanner.nextInt();
            boolean found = false;

            for (Product product : products) {
                if (product != null && product.getItemNum() == num) {
                    currentStock = product.getItemQuantity();

                    do {
                        System.out.printf("Enter the amount of stock to deduct from product %d (current stock: %d): ", num, currentStock);
                        newStock = scanner.nextInt();
                        product.deduct(newStock);
                    } while (newStock < 0);

                    System.out.printf("Stock updated: product " + product.getItemName() + " now has %d units in stock.\n \n", product.getItemQuantity());
                    found = true;
                    break; // exit the loop once the matching product is found
                }
            }

            if (!found) {
                System.out.println("Invalid item number. Please try again");
            }
        }
    }

    
    public static void setItemStatus(Product[] products, Scanner scanner) {
        int numProducts;
        String newStatus;
        
        do {
            System.out.print("Enter the number of products to update status for: ");
            numProducts = scanner.nextInt();
        } while (numProducts < 0);

        for (int i = 0; i < numProducts; i++) {
            System.out.print("Enter the ID of the product to update status for: ");
            int num = scanner.nextInt();
            boolean found = false;

            for (Product product : products) {
                if (product != null && product.getItemNum() == num) {
                    do {
                    	System.out.print(product.getItemName()+ "'s status now is " + product.getItemStatus() + ": ");
                        System.out.print("Enter the new status (active/discontinued) for product " + product.getItemName() + ": ");
                        newStatus = scanner.next();
                        if (newStatus.equalsIgnoreCase("active") || newStatus.equalsIgnoreCase("discontinued")) {
                            product.setItemStatus(newStatus.equalsIgnoreCase("active"));
                        } else {
                            System.out.println("Invalid status entered. Please enter 'active' or 'discontinued'.");
                        }
                    } while (!newStatus.equalsIgnoreCase("active") && !newStatus.equalsIgnoreCase("discontinued"));

                    System.out.printf("Status updated: product " + product.getItemName() + " is now %s.\n \n", product.getItemStatus());
                    found = true;
                    break; // exit the loop once the matching product is found
                }
            }

            if (!found) {
                System.out.println("Invalid item number. Please try again");
            }
        }
    }


    
    //main method
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        
        //Welcome message and group member
        Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		System.out.println("Welcome to the SMS " + formatter.format(date));

		 ArrayList<String> member = new ArrayList<String>();
		 member.add("Ng Vyjan");
		 member.add("Kasey Chang");
		 member.add("Poon Jin Yang");
		 member.add("Ng Yan Jun");
	     
	    // Collections.sort in ascending order.
	     Collections.sort(member);
	     System.out.println("Group Members: " + member + "\n");
        
      int maxProducts = getMaxProducts(scanner);
        Product[] products = new Product[maxProducts];
        

        
        products = addProduct(products, scanner);
        // display the menu and handle user input
        int menuChoice;
        do {
            menuChoice = displayMenu(scanner);

            switch (menuChoice) {
                case 1:
                    // display the contents of the products array
                	viewProducts(products);
                    break;
                    
                case 2:
                    // add stock to a product
                	addStock(products, scanner);
                    break;
                case 3:
                    // deduct stock from a product
                  deductStock(products, scanner);
                    break;
                case 4:
                    // discontinue a product
                	setItemStatus(products, scanner);
                    break;
                case 0:
                    System.out.println("Exiting program....");
                    break;
                default:
                    System.out.println("Invalid menu choice. Please try again!");
            }
        } while (menuChoice != 0);

        scanner.close();
    }
            
}