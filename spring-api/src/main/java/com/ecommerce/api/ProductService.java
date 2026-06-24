package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public String processOrder(String productId, int quantity, PaymentProcessor paymentMethod) {

        // 1. Ask the Database (MySQL) for the exact row matching the SKU
        Product product = repository.findBySku(productId);

        // 2. Safety Check: Does the product exist in the database?
        if (product == null) {
            return "❌ ERROR: Product not found in the Database!";
        }

        // 3. Safety Check: Do we have enough stock in the warehouse?
        if (product.getStock() < quantity) {
            return "❌ ERROR: Not enough stock! Only " + product.getStock() + " left.";
        }

        // --- THE PAYMENT LOGIC ---
        String paymentResult = paymentMethod.pay(product.getPrice() * quantity);

        // 4. THE DATABASE UPDATE!
        product.setStock(product.getStock() - quantity);
        repository.save(product);

        return "✅ SUCCESS! " + paymentResult + " Remaining DB Stock: " + product.getStock();
    }
}