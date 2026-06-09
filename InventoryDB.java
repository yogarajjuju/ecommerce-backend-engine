import java.util.*;

public class InventoryDB{
    private Map<String ,Product> database;
    // This is our in-memory database table.
    // Key = String (Product ID), Value = Product (The actual object)

    public InventoryDB(){
        this.database = new HashMap<>();
    }
// Method to add a new product to our database
    public void addProduct(Product product){
        database.put(product.getId(), product);
    }
    // Method to fetch a product (O(1) Time Complexity!) 
    public  Product getProduct(String id){
        return database.get(id);
    }
    public void printAllInventory() {
        System.out.println("\n--- 📦 Store Inventory ---");
        // Loop through all values in the HashMap
        for (Product p : database.values()) {
            System.out.println(p); // This automatically calls your custom toString() method!
        }
    }

}