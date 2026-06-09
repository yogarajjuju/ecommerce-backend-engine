import java.util.*;
public  class Main{
    public static void main(String[] args){
        InventoryDB  storeDB= new InventoryDB();

        storeDB.addProduct(new Product("P001", "Mechanical Keyboard", 120.50, 10));
        storeDB.addProduct(new Product("P002", "Wireless Mouse", 45.00, 25));
        storeDB.addProduct(new Product("P003", "Curved Monitor", 300.00, 5));
        

    // 4. Create the User's Shopping Cart (ArrayList)
        List<Product> cart = new ArrayList<>();
        System.out.println("\n--- 🛒 Adding items to cart ---");

        Product itemToBuy = storeDB.getProduct("P001"); // The Keyboard (Stock: 10)
        
        if (itemToBuy != null) {
            try {
                // Let's try to buy 20 keyboards! This should trigger our exception.
                int quantityToBuy = 20; 
                System.out.println("Attempting to buy " + quantityToBuy + " of " + itemToBuy.getName() + "...");
                
                itemToBuy.reduceStock(quantityToBuy); 
                cart.add(itemToBuy);
                System.out.println("✅ Successfully added to cart!");

            } catch (InsufficientStockException e) {
                // If the error is thrown, the code jumps straight here
                System.out.println("❌ FAILED: " + e.getMessage());
            }
        }

        // 5. Show inventory again to prove the stock didn't go negative
        storeDB.printAllInventory();


    }
}