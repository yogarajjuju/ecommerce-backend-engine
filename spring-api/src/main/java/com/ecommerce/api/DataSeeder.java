package com.ecommerce.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository repository;

    public DataSeeder(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // ✨ THE FIX: Explicitly search for our specific keyboard!
        Product existingProduct = repository.findBySku("P001");

        if (existingProduct == null) {
            System.out.println("🌱 P001 not found! Injecting Mk-1 Pro Mechanical Keyboard...");

            Product keyboard = new Product("P001", "Mk-1 Pro Mechanical Keyboard", 99.99, 10);
            repository.save(keyboard);

            System.out.println("✅ Starting inventory saved to database!");
        } else {
            System.out.println("🌲 P001 already exists in the Database. Stock is currently: " + existingProduct.getStock());
        }
    }
}