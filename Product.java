
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Product {
	
	private int productID;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private int sales;

	
    // Constructor
    Product(){}
    Product(int productID, String name, String description, double price, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
 
   //getters & setters
   public int getProductID() {
	   return productID;
   }
   public String getName() {
	   return name;
   }
   public String getDescription() {
	   return description;
   }
   public double getPrice() {
	   return price;
   }
   public int getStockQuantity() {
	   return stockQuantity;
   }
   public int getSales() {
       return sales;
   }

   // setters
   public void setProductID(int productID) {
	   this.productID=productID;
   }
   public void setName(String name) {
	   this.name=name;
   }
   public void setDescription(String description) {
	   this.description = description;
   }
   public void setPrice(double price) {
	   this.price=price;
   }
   public void setStockQuantity(int stockQuantity) {
	   this.stockQuantity=stockQuantity;
   }
   public void setSales(int sales) {
       this.sales=sales;;
   }

      
 // Display stock levels
   public static void displayStockLevels(List<Product> ProductListArray) {
	   for (int i=0; i<ProductListArray.size();i++) {
		   System.out.print(ProductListArray.get(i).getName()+"'s Stock Quantity is: ");
		   System.out.println(ProductListArray.get(i).getStockQuantity());
		   
	   	}
   }

   
// Alert for low stock levels
   public static void alertLowStock(List<Product>  ProductListArray) {
	   for (int i=0; i<ProductListArray.size();i++) {
		   if (ProductListArray.get(i).getStockQuantity()<=5) {
			   System.out.println("[!] LOW STOCK LEVEL DETECTED ON: " + ProductListArray.get(i).getName());
		   }  
	   }
   }

   
// Method to read product information from file and save into ArrayList
   public static List<Product> readProductsFromFile(String filename) {
       List<Product> productList = new ArrayList<>();
       File file = new File(filename);

       try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
           String line;
           while ((line = reader.readLine()) != null) {
               String[] data = line.split(" ");
               int productID = Integer.parseInt(data[0]);
               String name = data[1];
               String description = data[2];
               double price = Double.parseDouble(data[3]);
               int stockQuantity = Integer.parseInt(data[4]);

               // Create product object and add to list
               productList.add(new Product(productID, name, description, price, stockQuantity));
           }
       } 
       catch (IOException e) {
           System.out.println("An error occurred while reading the file: " + e.getMessage());
       }

       return productList;
   }
 

// Method to add new products
   public static void addProduct(int productID, String name, String description, double price, int stockQuantity, List<Product> productList) {
       boolean productExists = false;
       for (Product product : productList) {
           if (product.getProductID() == productID) {
               System.out.println("A product with the same ID already exists!");
               productExists = true;
               break;
           }
       }

       if (!productExists) {
           productList.add(new Product(productID, name, description, price, stockQuantity));
           System.out.println("Product added successfully!");
       }
   }
   
   
   
   public static void searchProductByName(String productName) {
	    File file = new File("ProductData.txt");

	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        boolean found = false;
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.toLowerCase().contains(productName.toLowerCase())) {
	                System.out.println("The product is available in stock !!");
	                found = true;
	                break; // Once the product is found, exit the loop
	            }
	        }
	        if (!found) {
	            System.out.println("The product is not available in stock !!");
	        }
	    } catch (IOException e) {
	        System.out.println("An error occurred while reading the file: " + e.getMessage());
	    }
	}

   
   
   
   public static void searchProductByCategory(String productDescription) {
	    File file = new File("ProductData.txt");

	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        boolean found = false;
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.toLowerCase().contains(productDescription.toLowerCase())) {
	                System.out.println("There are products of this category in stock !!");
	                found = true;
	                break; // Once a product of the specified category is found, exit the loop
	            }
	        }
	        if (!found) {
	            System.out.println("There aren't any products of this category in stock !!");
	        }
	    } catch (IOException e) {
	        System.out.println("An error occurred while reading the file: " + e.getMessage());
	    }
	}

   
   
   
   public static void searchProductByID(int productID) {
	    File file = new File("ProductData.txt");

	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	        boolean found = false;
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.contains(String.valueOf(productID))) {
	                System.out.println("The product is available in stock !!");
	                found = true;
	                break; // Exit the loop once the product with the specified ID is found
	            }
	        }
	        if (!found) {
	            System.out.println("The product is not available in stock !!");
	        }
	    } catch (IOException e) {
	        System.out.println("An error occurred while reading the file: " + e.getMessage());
	    }
	}

   
   
   
   public static void updateProductID(List<Product> productList, String productName, int newID) {
	   
	   for(Product product : productList) {
		   if (productName.equalsIgnoreCase(product.getName())) {
			   product.setProductID(newID);
		   }
	   }
       System.out.println(productName + "'s ID updated successfully !!");
   }
   
   
   public static void updateName(List<Product> productList, String productName, String newName) {
	   
	   for(Product product : productList) {
		   if (productName.equalsIgnoreCase(product.getName())) {
			   product.setName(newName);
		   }
	   }
       System.out.println(productName + "'s name updated successfully !!");
   }

   public static void updateDescription(List<Product> productList, String productName, String newDes) {
	   
	   for(Product product : productList) {
		   if (productName.equalsIgnoreCase(product.getName())) {
			   product.setDescription(newDes);
		   }
	   }
       System.out.println(productName + "'s description updated successfully !!");
   }


   public static void updatePrice(List<Product> productList, String productName, double newPrice) {
	   
	   for(Product product : productList) {
		   if (productName.equalsIgnoreCase(product.getName())) {
			   product.setPrice(newPrice);
		   }
	   }
       System.out.println(productName + "'s price updated successfully !!");
   }

   
   
   public static void deleteProduct(List<Product> productList, String productName) {
	   
	   Iterator<Product> iterator = productList.iterator();
       while (iterator.hasNext()) {
           Product product = iterator.next();
           if (productName.equalsIgnoreCase(product.getName())) {
               iterator.remove(); // Remove the current product using the iterator
           }
       }
       System.out.println("You have successfully deleted the product !!");
       deleteFromSales(productName);
   }
   
   
   public static void deleteFromSales(String productName) {
	   String tempFile = "tempFile.txt";
       File oldFile = new File("SalesData.txt");
       File newFile = new File(tempFile);
       
       try (BufferedReader reader = new BufferedReader(new FileReader(oldFile));
               BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {

              String line;

              while ((line = reader.readLine()) != null) {
            	  if (!line.toLowerCase().contains(productName.toLowerCase())) {
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
   
   public static void displayProducts() {
	   File file = new File("ProductData.txt");
        System.out.println("ID, Name, Describtion, Price, Stock");
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