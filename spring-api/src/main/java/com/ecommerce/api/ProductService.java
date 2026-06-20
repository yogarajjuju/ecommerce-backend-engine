package com.ecommerce.api;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // 🚨 LOOK HERE: We added "PaymentProcessor paymentMethod" to the arguments!
    public String processOrder(String productId, int quantity, PaymentProcessor paymentMethod) {
        Product item = repository.getProductFromDB(productId);

        if (item == null) {
            return "❌ FAILED: Product ID does not exist!";
        }

        try {
            item.reduceStock(quantity);
            repository.saveNewStockToDB(item.getId(), item.getStock());

            // 🚨 LOOK HERE: The Chef just presses the "pay" button on the Disc Drive!
            // He has NO IDEA if it's Bitcoin, Credit Card, or Apple Pay. 
            // He just knows the disc is guaranteed to have a .pay() method.
            double totalCost = item.getPrice() * quantity;
            String receipt = paymentMethod.pay(totalCost);

            return "✅ SUCCESS! You bought " + quantity + " of " + item.getName() + " | " + receipt;

        } catch (Exception e) {
            return "❌ FAILED: " + e.getMessage();
        }
    }
}