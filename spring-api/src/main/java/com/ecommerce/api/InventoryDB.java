package com.ecommerce.api;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class InventoryDB {
    
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";

    public InventoryDB() {
        // Test the connection when the app starts
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ [LOG] Connected to MySQL Database successfully!");
        } catch (SQLException e) {
            System.out.println("❌ [LOG] Database Connection Failed!");
            e.printStackTrace();
        }
    }

    // Fetch a single product from the database
    public Product getProduct(String id) {
        String sql = "SELECT * FROM inventory WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id); // Replaces the '?' with the product ID
            ResultSet rs = pstmt.executeQuery();

            // If we found a row in the database, convert it into a Java Object
            if (rs.next()) {
                return new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if not found
    }

    // Print everything in the database
    public void printAllInventory() {
        System.out.println("\n--- 📦 Live Store Inventory (MySQL) ---");
        String sql = "SELECT * FROM inventory";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                Product p = new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                System.out.println(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NEW: Update the database after someone buys something
    public void updateStockInDB(String id, int newStock) {
        String sql = "UPDATE inventory SET stock = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, newStock);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // NEW METHOD: Fetches all products and packages them into a List for the Web API
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                Product p = new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                );
                products.add(p); // Put each product into the box
            }
        }
        return products; // Hand the box to Spring Boot!
    }
}