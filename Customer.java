
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
	
	private int customerID;
	private String customerName;
	//private int numberOfSales = 0;
	
	Customer() {}
	Customer(int customerID, String name) {
		
	this.customerID=customerID;
	this.customerName=name;
	}
	
	
	public int getCustomerID(){
		return  customerID;
	}
	public String getCustomerName(){
		return  customerName;
	}
	/*public int getNumberOfSales(){
		return  numberOfSales;
	}
	public void setNumberOfSales(int numberOfSales) {
		this.numberOfSales = numberOfSales;
	}*/

	
	public static void buyItem(List<Product> ProductListArray, List<Customer> CustomerListArray, String customerName, String name, int quantity) {

		for (Product product : ProductListArray) {
			if (name.equalsIgnoreCase(product.getName())) {
				if (product.getStockQuantity() < quantity) {
					System.out.println("Sorry, we do not have sufficcient quantity to complete this transaction");
				}
				else {   
					System.out.println("You have successfully bought " + product.getName());
					product.setStockQuantity(product.getStockQuantity()-quantity);
					Save.saveSalesByCustomers(customerName, quantity, name, "SalesData.txt");
				}
			} 
		}
		/*for(Customer customer : CustomerListArray) {
			
			if (customerName.equalsIgnoreCase(customer.getCustomerName())) {
				customer.setNumberOfSales(customer.getNumberOfSales() + quantity);
			}	
		}*/
	}

	
	/*public static void salesReport(List<Product> productList, String filename) {
	    try {
	        for (Product product : productList) {
	            File file = new File(filename);
	            int sales = 0;
	            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    if (line.toLowerCase().contains(product.getName().toLowerCase())) {
	                        String[] data = line.split(" ");
	                        sales += Integer.parseInt(data[0]);
	                    }
	                }
	                System.out.println("There have been sold " + sales + " of " + product.getName());
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("An error occurred while reading the file: " + e.getMessage());
	    }
	}*/
	
	
	public static void salesReportByCustomers(List<Product> productList, List<Customer> customerList) {
		
		try {
			for (Customer customer : customerList) {
	            File file = new File("SalesData.txt");
	            int sales;
	                String line;
	                System.out.println("Sales report for " + customer.getCustomerName() + ":");
	                for (Product product : productList) {
	                	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                	sales = 0;
	                	while ((line = reader.readLine()) != null) {
	                		if (line.toLowerCase().contains(product.getName().toLowerCase())) {
	                			if (line.toLowerCase().contains(customer.getCustomerName().toLowerCase())) {
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
	     
	
	
	// Method to read Customer information from file and save into ArrayList
		public static List<Customer> readCustomerFromFile(String filename) {
	    List<Customer> CustomerList = new ArrayList<>();
	    File file = new File(filename);

	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
				String[] data = line.split(" ");
				int customerID = Integer.parseInt(data[0]);
				String customerName = data[1];
				
	            // Create product object and add to list
	            CustomerList.add(new Customer(customerID, customerName));
	        }
	    } 
	    catch (IOException e) {
	        System.out.println("An error occurred while reading the file: " + e.getMessage());
	    }

	    return CustomerList;
	}
		
		public static void addCustomer(int customerID,String customerName,List<Customer> customerList){
			boolean customerExists = false;
			for (Customer customer : customerList) {
				if (customer.getCustomerName() == customerName) {
					System.out.println("A customer with the same name already exists!");
					customerExists = true;
					break;
				}
			}

			if (!customerExists) {
				customerList.add(new Customer(customerID, customerName));
				System.out.println("Customer added successfully!");
			}
			else{
				System.out.println("Customer Already exists!");
			}

		}
		
		
		public static void deleteCustomer(List<Customer> customerList, String customerName) {
			   
			   Iterator<Customer> iterator = customerList.iterator();
		       while (iterator.hasNext()) {
		           Customer customer = iterator.next();
		           if (customerName.equalsIgnoreCase(customer.getCustomerName())) {
		               iterator.remove(); // Remove the current product using the iterator
		           }
		       }
		       System.out.println("You have successfully deleted the customer !!");
		       deleteFromSales(customerName);
		   }
		
		public static void deleteFromSales(String customerName) {
			   String tempFile = "tempFile.txt";
		       File oldFile = new File("SalesData.txt");
		       File newFile = new File(tempFile);
		       
		       try (BufferedReader reader = new BufferedReader(new FileReader(oldFile));
		               BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {

		              String line;

		              while ((line = reader.readLine()) != null) {
		            	  if (!line.toLowerCase().contains(customerName.toLowerCase())) {
		            		  writer.write(line);
		            		  writer.newLine();
		            	  }
		              }
		              writer.flush();
		              reader.close();
		              writer.close();
		              oldFile.delete();
		              File dump = new File ("SalesData.txt");
		              newFile.renameTo(dump);
		       } catch (IOException e) {
		           System.out.println("An error occurred while reading the file: " + e.getMessage());
		       }
		   }
		
		public static void displayCustomers() {
			   File file = new File("CustomerData.txt");

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