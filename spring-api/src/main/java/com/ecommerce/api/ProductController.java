package com.ecommerce.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.sql.SQLException;

@RestController
public class ProductController {

    // Create an instance of your Module 2 database manager
    private final InventoryDB db = new InventoryDB();

    @GetMapping("/products")
    public List<Product> getProducts() {
        try {
            // Call the exact database method you verified yesterday!
            return db.getAllProducts();
        } catch (SQLException e) {
            // If the Docker database is turned off, print the error
            e.printStackTrace();
            return null;
        }
    }
}