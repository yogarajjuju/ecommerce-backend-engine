import java.util.*;

public class Main {
    public static void main(String[] args){
        // 1. Boot up database connection
        InventoryDB storeDB = new InventoryDB();
        
        // We deleted the addProduct lines because the data is already in MySQL!
        storeDB.printAllInventory();

        // 2. Create the User's Shopping Cart
        List<Product> cart = new ArrayList<>();
        System.out.println("\n--- 🛒 Adding items to cart ---");

        Product itemToBuy = storeDB.getProduct("P001"); // The Keyboard
        
        if (itemToBuy != null) {
            try {
                int quantityToBuy = 1; 
                System.out.println("Attempting to buy " + quantityToBuy + " of " + itemToBuy.getName() + "...");
                
                itemToBuy.reduceStock(quantityToBuy); // Reduces local Java object
                cart.add(itemToBuy);
                
                // NEW: Actually tell MySQL to update the stock permanently!
                storeDB.updateStockInDB(itemToBuy.getId(), itemToBuy.getStock());
                
                System.out.println("✅ Successfully added to cart and updated DB!");

            } catch (InsufficientStockException e) {
                System.out.println("❌ FAILED: " + e.getMessage());
            }
        }

        // 3. The Checkout Process
        System.out.println("\n--- 💳 Checkout Process ---");
        if (!cart.isEmpty()) {
            double totalAmount = 0;
            for (Product p : cart) {
                totalAmount += p.getPrice();
            }
            System.out.println("Total Amount Due: $" + totalAmount);

            PaymentProcessor processor = new CreditCardPayment("1234-5678-9012-3456");
            processor.processPayment(totalAmount);
        }

        // 4. Show final inventory directly from MySQL
        storeDB.printAllInventory();
    }
}