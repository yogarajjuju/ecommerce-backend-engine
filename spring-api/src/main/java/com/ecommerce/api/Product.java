package com.ecommerce.api;

import jakarta.persistence.*; // <-- Import the JPA Translator library!

@Entity // 1. Tells JPA: "Translate this class into a MySQL Table!"
@Table(name = "products") // 2. Names the table inside the database
public class Product {

    @Id // 3. Tells JPA: "This is the Primary Key (The unique ID)"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells MySQL to auto-count the ID (1, 2, 3...)
    private Long id;

    @Column(unique = true) // Ensures we can't accidentally have two products with the same SKU
    private String sku; // e.g., "P001"

    private String name;
    private double price;
    private int stock;

    // 🚨 IMPORTANT: JPA absolutely requires a blank, empty constructor to work!
    public Product() {}

    // Your normal constructor
    public Product(String sku, String name, double price, int stock) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // --- GETTERS ---
    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // --- SETTERS ---
    public void setId(Long id) { this.id = id; }
    public void setSku(String sku) { this.sku = sku; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}