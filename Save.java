import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Save {
	
	// Method to save all products
		public static void saveAllProducts(List<Product> productList, String filename) {
			File file = new File(filename);

		    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

		    	for (Product product : productList) {
		    		String productInfo = product.getProductID() + " " + product.getName() + " " +
                        product.getDescription() + " " + product.getPrice() + " " +
                        product.getStockQuantity();
						writer.write(productInfo);
						writer.newLine();
		    	}
		    } catch (IOException e) {
		           System.out.println("An error occurred while writing to the file: " + e.getMessage());
		       }
		}
		
		
		// Method to save all customers
				public static void saveAllCustomers(List<Customer> customerList, String filename) {
					File file = new File(filename);

				    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

				    	for (Customer customer : customerList) {
				    		String customerInfo = customer.getCustomerID() + " " + customer.getCustomerName();
								writer.write(customerInfo);
								writer.newLine();
				    	}
				    } catch (IOException e) {
				           System.out.println("An error occurred while writing to the file: " + e.getMessage());
				       }
				}
				
				
				// Method to save all suppliers
				public static void saveAllSuppliers(List<Supplier> supplierList, String filename) {
					File file = new File(filename);

				    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

				    	for (Supplier supplier : supplierList) {
				    		String supplierInfo = supplier.getSupplierID() + " " + supplier.getSupplierName();
								writer.write(supplierInfo);
								writer.newLine();
				    	}
				    } catch (IOException e) {
				           System.out.println("An error occurred while writing to the file: " + e.getMessage());
				       }
				}
				
				
				
				// Method to save sales transactions
				public static void saveSalesByCustomers(String customerName, int quantity, String name, String filename) {
					File file = new File(filename);
					
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){

						String salesInfo = quantity + " kg's of " + name + " have been soled to " + customerName;
						writer.write(salesInfo);
						writer.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
				
				// Method to save sales transactions
				public static void saveSalesFromSupplier(String supplierName, int quantity, String productName, String filename) {
					File file = new File(filename);
					
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){

						String salesInfo = quantity + " kg's of " + productName + " have been bought from " + supplierName;
						writer.write(salesInfo);
						writer.newLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
}

    