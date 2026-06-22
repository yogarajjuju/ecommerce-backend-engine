package com.ecommerce.api;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    public Product getProductFromDB(String id) {
        // 🚨 LOOK HERE: We changed "new Product" to "new PhysicalProduct"
        // We also added the weight (2.5) at the very end!
        return new PhysicalProduct(id, "Mechanical Keyboard", 99.99, 10, 2.5); 
    }

    public void saveNewStockToDB(String id, int newStock) {
        System.out.println("Saved new stock to database: " + newStock);
    }
}