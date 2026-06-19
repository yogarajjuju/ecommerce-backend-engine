package com.ecommerce.api;

import org.springframework.stereotype.Repository;

// @Repository tells Spring: "This is the pantry. It only handles database stuff."
@Repository
public class ProductRepository {

    // (Imagine your JDBC Database connection code lives inside here now)

    public Product getProductFromDB(String id) {
        // Fetches product from MySQL
        return new Product(id, "Mechanical Keyboard", 99.99, 10); // Simulated DB fetch
    }

    public void saveNewStockToDB(String id, int newStock) {
        // Sends the UPDATE SQL command to MySQL
        System.out.println("Saved new stock to database: " + newStock);
    }
}