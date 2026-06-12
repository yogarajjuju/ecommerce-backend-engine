import java.util.*;

public class Main {
    public static void main(String[] args){
        InventoryDB storeDB = new InventoryDB();

        storeDB.addProduct(new Product("P001", "Mechanical Keyboard", 120.50, 10));
        storeDB.addProduct(new Product("P002", "Wireless Mouse", 45.00, 25));
        storeDB.addProduct(new Product("P003", "Curved Monitor", 300.00, 5));

        // 4. Create the User's Shopping Cart
        List<Product> cart = new ArrayList<>();
        System.out.println("\n--- 🛒 Adding items to cart ---");

        Product itemToBuy = storeDB.getProduct("P001"); // The Keyboard
        
        if (itemToBuy != null) {
            try {
                // Changed to 1 so the purchase succeeds and goes into the cart!
                int quantityToBuy = 1; 
                System.out.println("Attempting to buy " + quantityToBuy + " of " + itemToBuy.getName() + "...");
                
                itemToBuy.reduceStock(quantityToBuy); 
                cart.add(itemToBuy);
                System.out.println("✅ Successfully added to cart!");

            } catch (InsufficientStockException e) {
                System.out.println("❌ FAILED: " + e.getMessage());
            }
        }

        // 5. NEW: The Checkout Process (Polymorphism)
        System.out.println("\n--- 💳 Checkout Process ---");
        if (!cart.isEmpty()) {
            // Calculate total price
            double totalAmount = 0;
            for (Product p : cart) {
                totalAmount += p.getPrice();
            }
            System.out.println("Total Amount Due: $" + totalAmount);

            // POLYMORPHISM IN ACTION:
            // Notice how the variable type is the Interface (PaymentProcessor), 
            // but the object we create is the specific implementation.
            PaymentProcessor processor;

            // Scenario A: User pays with Credit Card
            processor = new CreditCardPayment("1234-5678-9012-3456");
            processor.processPayment(totalAmount);

            // Scenario B: User changes their mind and pays with UPI
            processor = new UPIPayment("yogaraj@upi");
            processor.processPayment(totalAmount);
        }

        // 6. Show final inventory
        System.out.println("\n--- 📦 Final Store Inventory ---");
        storeDB.printAllInventory();
    }
}