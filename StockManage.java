//Hazim's Notes:
//1. Fixed issue with View Suppliers not resetting after action is done
//2. Added auto save feature after every action instead of after closing the program
//3. [WIP] Added a login menu


import java.util.List;
import java.util.Scanner;

public class StockManage {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE  = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[0m";


    private static List<Product> productsList;
    private static List<Customer> customersList;
    private static List<Supplier> suppliersList;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize the lists
        productsList = Product.readProductsFromFile("ProductData.txt");
        customersList = Customer.readCustomerFromFile("CustomerData.txt");
        suppliersList = Supplier.readSupplierFromFile("SupplierData.txt");

    
        // Display the main menu

        displayLogin();
        Save.saveAllProducts(productsList, "ProductData.txt");
        Save.saveAllCustomers(customersList, "CustomerData.txt");
        Save.saveAllSuppliers(suppliersList, "SupplierData.txt");

    }
    private static void displayLogin(){
        System.out.println(RED + " = = = =  Welcome  = = = = " + RESET);
        System.out.println("\nLogin as: \n");
        System.out.println("1.   Manager");
        System.out.println("2.   Customer");
        System.out.println("3.   Exit");
        int choice;
        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayManagerMenu();
                    Save.saveAllProducts(productsList, "ProductData.txt");
                    Save.saveAllCustomers(customersList, "CustomerData.txt");
                    Save.saveAllSuppliers(suppliersList, "SupplierData.txt");
                    break;
                case 2:
                    displayCustomerMenu();
                    Save.saveAllProducts(productsList, "ProductData.txt");
                    Save.saveAllCustomers(customersList, "CustomerData.txt");
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Please enter a valid number.");
                    break;
                }
            }
        while (choice != 1 && choice != 2 && choice != 3); 
            
        
        
    }
    private static void displayManagerMenu() {
        System.out.println("===== Stock Management System =====");
        System.out.println("\n1. View Stock Levels");
        System.out.println("2. View Sales Reports");
        System.out.println("3. Manage Products");
        System.out.println("4. Manage Customers");
        System.out.println("5. Manage Suppliers");
        System.out.println("6. Logout");
        System.out.print("\nEnter your choice: ");

        int choice;
        do { 
        	choice = scanner.nextInt();
        	scanner.nextLine(); // Consume the newline character
	        switch (choice) {
	            case 1:
	                viewStockLevels();
	                break;
	            case 2:
	                viewSalesReports();
	                break;
	            case 3:
	                manageProducts();
                    Save.saveAllProducts(productsList, "ProductData.txt");
	                break;
	            case 4:
	                manageCustomers();
                    Save.saveAllCustomers(customersList, "CustomerData.txt");
	                break;
	            case 5:
	                manageSuppliers();
                    Save.saveAllSuppliers(suppliersList, "SupplierData.txt");
	                break;
	            case 6:
                displayLogin();
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again !!");
	        }
        } while (choice !=1 && choice !=2 && choice !=3 && choice !=4 && choice !=5 && choice !=6);
    }
    private static void displayCustomerMenu(){
        System.out.println("===== Customer =====");
        System.out.println("\n1. View Products");
        System.out.println("2. buy Products");
        System.out.println("3. Logout");
        System.out.print("\nEnter your choice: ");

        int choice;
        do { 
        	choice = scanner.nextInt();
        	scanner.nextLine(); // Consume the newline character
	        switch (choice) {
                case 1:
                Product.displayProducts();
                System.out.println("Press any key to go back");
                scanner.nextLine();
                displayCustomerMenu();
                break;
	            case 2:
	                buyProducts();
                    Save.saveAllProducts(productsList, "ProductData.txt");
                    Save.saveAllCustomers(customersList, "CustomerData.txt");
	                break;
                case 3:
                displayLogin();
	            default:
	                System.out.println("Invalid choice. Please try again !!");
	        }
        } while (choice !=1 && choice !=2 && choice !=3);
    }
    private static void buyProducts() {
        System.out.println("\n===== Buy Products =====");
        System.out.print("Enter the name of the product you want to buy: ");
        String productName = scanner.nextLine();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        Customer.buyItem(productsList, customersList, customerName, productName, quantity);
        Save.saveAllProducts(productsList, "ProductData.txt");

        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
        displayCustomerMenu();
    }

    private static void viewStockLevels() {
        System.out.println("\n===== Stock Levels =====");
        Product.displayStockLevels(productsList);
        Product.alertLowStock(productsList);

        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
        displayManagerMenu();
    }

    private static void viewSalesReports() {
        System.out.println("\n===== Sales Reports =====");
        System.out.println("\n1. Sales Report of Customers");
        System.out.println("2. Sales Report of Suppliers");
        System.out.print("\nEnter your choice: ");

        int choice;
        do {
        	choice = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character
	
	        switch (choice) {
	            case 1:
	                Customer.salesReportByCustomers(productsList, customersList);
	                break;
	            case 2:
	                Supplier.salesReportFromSupplier(productsList, suppliersList);
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again !!");
	        }
        } while (choice != 1 && choice != 2);

        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
        displayManagerMenu();
    }

    private static void manageProducts() {
        System.out.println("\n===== Manage Products =====");
        System.out.println("\n1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Search for product");
        System.out.println("5. View products");
        System.out.print("\nEnter your choice: ");
        int choice; 
        do {
        	choice = scanner.nextInt();
        	scanner.nextLine(); // Consume the newline character

        	switch (choice) {
            	case 1:
            		addProduct();
                    Save.saveAllProducts(productsList, "ProductData.txt");
            		break;
            	case 2:
            		updateProduct();
                    Save.saveAllProducts(productsList, "ProductData.txt");

            		break;
	            case 3:
	                deleteProduct();
                    Save.saveAllProducts(productsList, "ProductData.txt");

	                break;
	            case 4:
	                searchForProduct();
	                break;
	            case 5:
	                Product.displayProducts();
	                break;    
	            default:
	                System.out.println("Invalid choice. Please try again !!");
        	}       
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5); 

        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
        displayManagerMenu();
    }

    private static void addProduct() {
        System.out.print("\nEnter product ID: ");
        int productID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter stock quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product.addProduct(productID, name, description, price, stockQuantity, productsList);
    }

    private static void updateProduct() {
    	System.out.println("\n===== Update Products =====");
        System.out.println("\nPlease enter the name of the product you want to update: ");
        String productName = scanner.nextLine();
        System.out.println("What do you want to update?");
        System.out.println("1. " + productName + " ID");
        System.out.println("2. " + productName + " name");
        System.out.println("3. " + productName + " description");
        System.out.println("4. " + productName + " price");
        System.out.print("\nEnter your choice: ");
        int choice;
        do {
        	choice = scanner.nextInt();
        	scanner.nextLine(); // Consume newline character
	        switch (choice) {
	        	case 1 :
	        		System.out.println("What is the new ID?");
	        		int newID = scanner.nextInt();
	        		Product.updateProductID(productsList, productName, newID);
	        		break;
	        	case 2 : 
	        		System.out.println("What is the new name?");
	        		String newName = scanner.nextLine();
	        		Product.updateName(productsList, productName, newName);
	        		break;
	        	case 3 :
	        		System.out.println("What is the new description?");
	        		String newDes = scanner.nextLine();
	        		Product.updateDescription(productsList, productName, newDes);
	        		break;
	        	case 4 : 
	        		System.out.println("What is the new price?");
	        		int newPrice = scanner.nextInt();
	        		Product.updatePrice(productsList, productName, newPrice);
	        		break;
	        	default:
	                System.out.println("Invalid choice. Please try again !!");	
	        }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);   
    }

    private static void deleteProduct() {
        System.out.print("\nEnter the name of the product you want to delete: ");
        String productName = scanner.nextLine();

        Product.deleteProduct(productsList, productName);
    }

    private static void searchForProduct() {
    	System.out.println("\n===== Product existance in stock =====");
        System.out.println("\nBy what do you want to search?");
        System.out.println("1. By name");
        System.out.println("2. By category");
        System.out.println("3. By ID");
        System.out.print("\nEnter your choice: ");
        int choice;
        do {
        	choice = scanner.nextInt();
        	scanner.nextLine(); // Consume newline character
	        switch (choice) {
	        	case 1 : 
	        		System.out.println("What is the name of the product?");
	        		String productName;
	        		productName = scanner.nextLine();
	        		Product.searchProductByName(productName);
	        		break;
	        	case 2 : 
	        		System.out.println("What is the category of the product?");
	        		String productDes = scanner.nextLine();
	        		Product.searchProductByCategory(productDes);
	        		break;
	        	case 3 : 
	        		System.out.println("What is the ID of the product?");
	        		int productID = scanner.nextInt();
	        		Product.searchProductByID(productID);
	        		break;
	        	default:
	                System.out.println("Invalid choice. Please try again !!");	
	        }
        } while (choice != 1 && choice != 2 && choice != 3);
    }
    
    
    private static void manageCustomers() {
        System.out.println("\n===== Manage Customers =====");
        System.out.println("\n1. Add Customer");
        System.out.println("2. Delete Customer");
        System.out.println("3. View Customers");
        System.out.print("\nEnter your choice: ");

        int choice;
        do {
        	choice = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character
	
	        switch (choice) {
	            case 1:
	                addCustomer();
                    Save.saveAllCustomers(customersList, "CustomerData.txt");
	                break;
	            case 2 :
	            	deleteCustomer();
                    Save.saveAllCustomers(customersList, "CustomerData.txt");

	            	break;
	            case 3 :
	            	Customer.displayCustomers();
	            	break;	
	            default:
	                System.out.println("Invalid choice. Please try again !!");
	        }
        } while (choice != 1 && choice != 2 && choice != 3);    

        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
        displayManagerMenu();
    }

    private static void addCustomer() {
        System.out.print("\nEnter customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        scanner.nextLine(); // Consume the newline character
        Customer.addCustomer(customerID, customerName, customersList);
    }

    private static void deleteCustomer() {
        System.out.print("\nEnter the name of the customer you want to delete: ");
        String customerName = scanner.nextLine();
        scanner.nextLine(); // Consume the newline character
        Customer.deleteCustomer(customersList, customerName);
    }
	
   
    private static void manageSuppliers() {
        System.out.println("\n===== Manage Suppliers =====");
        System.out.println("\n1. Add Supplier");
        System.out.println("2. Restock Products");
        System.out.println("3. View Suppliers");
        System.out.print("\nEnter your choice: ");

        int choice;
        do { 
        	choice = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character
	
	        switch (choice) {
	            case 1:
	                addSupplier();
	                break;
	            case 2:
	                restockProducts();
	                break;    
	            case 3:
	                Supplier.displaySuppliers();
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again !!");
	        }
        } while (choice != 1 && choice != 2 && choice != 3);    

        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
        displayManagerMenu();
    }

    private static void addSupplier() {
        System.out.print("\nEnter supplier ID: ");
        int supplierID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
		System.out.print("Enter supplier name: ");
		String supplierName = scanner.nextLine();
        scanner.nextLine(); // Consume the newline character
		Supplier.addSupplier(supplierID, supplierName, suppliersList);
		}
    
    
    private static void restockProducts() {
    	System.out.println("\n===== Restock Products =====");
        System.out.print("\nPlease enter the name of the product you want to restock: ");
        String productName = scanner.nextLine();
        System.out.print("Please enter the name of the supplier you want to buy from: ");
        String supplierName = scanner.nextLine();
        System.out.print("Please enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Supplier.restockItem(productsList, quantity, productName, supplierName);
    }
	}