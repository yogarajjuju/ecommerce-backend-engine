package com.ecommerce.api;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    // @PostMapping means this URL is for receiving data, not just giving it.
    @PostMapping("/buy")
    public String buyProduct(@RequestBody BuyRequest request) {
        try {
            // 1. Find the product in the database using the ID they sent
            Product item = db.getProduct(request.getProductId());
            
            if (item == null) {
                return "❌ FAILED: Product ID does not exist!";
            }

            // 2. Try to reduce the stock. (This will throw an error if stock is 0!)
            item.reduceStock(request.getQuantity());

            // 3. If the math worked, permanently save the new stock number to MySQL
            db.updateStockInDB(item.getId(), item.getStock());

            return "✅ SUCCESS! You bought " + request.getQuantity() + " of " + item.getName();

        } catch (InsufficientStockException e) {
            // If they try to buy 5 monitors but we only have 2, this catches it!
            return "❌ FAILED: " + e.getMessage();
        }
    }
    // 1. The Window Tag: The {id} tells Spring that part of the URL is a variable!
    @GetMapping("/products/{id}")
    
    // 2. The Method: @PathVariable grabs the "P001" from the URL and hands it to Java
    public Product getSingleProduct(@PathVariable String id) {
        
        // 3. Ask the database for that specific ID and hand it out the window
        return db.getProduct(id);
    }
}