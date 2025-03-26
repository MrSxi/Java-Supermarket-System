
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
	
	private int supplierID;
	private String supplierName;
	//private int numberOfPurchases = 0;
	
	Supplier(int supplierID, String supplierName) {
		this.supplierID = supplierID;
		this.supplierName = supplierName;
	}
	
	public int getSupplierID() {
		return supplierID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	/*public int getNumberOfPurchases() {
		return numberOfPurchases;
	}*/
	
	
	// Method to read Supplier information from file and save into ArrayList
			public static List<Supplier> readSupplierFromFile(String filename) {
		    List<Supplier> SuppliersList = new ArrayList<>();
		    File file = new File(filename);

		    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		        String line;
		        while ((line = reader.readLine()) != null) {
					String[] data = line.split(" ");
					int supplierID = Integer.parseInt(data[0]);
					String supplierName = data[1];
					
					
		            // Create product object and add to list
		            SuppliersList.add(new Supplier(supplierID, supplierName));
		        }
		    } 
		    catch (IOException e) {
		        System.out.println("An error occurred while reading the file: " + e.getMessage());
		    }

		    return SuppliersList;
		}
			
			
			public static void addSupplier(int supplierID, String supplierName, List<Supplier> suppliersList){
				boolean supplierExists = false;
				for (Supplier supplier : suppliersList) {
					if (supplier.getSupplierID() == supplierID) {
						System.out.println("A supplier with the same ID already exists!");
						supplierExists = true;
						break;
					}
				}

				if (!supplierExists) {
					suppliersList.add(new Supplier(supplierID, supplierName));
					System.out.println("Supplier added successfully!");
				}

			}
			
			public static void restockItem(List<Product> ProductListArray, int quantity, String productName, String supplierName) {
				for (Product product : ProductListArray) {
					
					if (productName.equalsIgnoreCase(product.getName())) {
						System.out.println("You have successfully added " + quantity + " to the " + product.getName() + " stock");
						product.setStockQuantity(product.getStockQuantity()+quantity);
						Save.saveSalesFromSupplier(supplierName, quantity, productName, "SalesData.txt");
					}
				}
			}
			
			
			public static void salesReportFromSupplier(List<Product> productList, List<Supplier> supplierList) {
				
				try {
					for (Supplier supplier : supplierList) {
			            File file = new File("SalesData.txt");
			            int sales;
			                String line;
			                System.out.println("Sales report of " + supplier.getSupplierName() + ":");
			                for (Product product : productList) {
			                	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			                	sales = 0;
			                	while ((line = reader.readLine()) != null) {
			                		if (line.toLowerCase().contains(product.getName().toLowerCase())) {
			                			if (line.toLowerCase().contains(supplier.getSupplierName().toLowerCase())) {
			                				String[] data = line.split(" ");
			                				sales += Integer.parseInt(data[0]);
			                			}
			                		}	
			                    }
			        	        System.out.println("    - " + product.getName() + ": " + sales);
			        	        }
			                
			                }
			            }
			        
			    } catch (IOException e) {
			        System.out.println("An error occurred while reading the file: " + e.getMessage());
			    }
				
			    
			}
			
			public static void displaySuppliers() {
				   File file = new File("SupplierData.txt");

			       try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			           String line;
			           System.out.print("\n");
			           while ((line = reader.readLine()) != null) {
			        	   System.out.println(line);
			           }
			       } catch (IOException e) {
			           System.out.println("An error occurred while reading the file: " + e.getMessage());
			       	}
			   }
}
