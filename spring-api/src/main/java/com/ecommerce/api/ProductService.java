package com.ecommerce.api;

import org.springframework.stereotype.Service;

// @Service tells Spring: "This is the Chef. Put all the business logic here."
@Service
public class ProductService {

    // The Chef needs access to the Pantry (Repository)
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // The main brain logic
    public String processOrder(String productId, int quantity) {
        // 1. Ask the pantry for the item
        Product item = repository.getProductFromDB(productId);

        if (item == null) {
            return "❌ FAILED: Product ID does not exist!";
        }

        try {
            // 2. Do the math
            item.reduceStock(quantity);

            // 3. Tell the pantry to save the new number
            repository.saveNewStockToDB(item.getId(), item.getStock());

            return "✅ SUCCESS! You bought " + quantity + " of " + item.getName();

        } catch (Exception e) {
            return "❌ FAILED: " + e.getMessage();
        }
    }
}