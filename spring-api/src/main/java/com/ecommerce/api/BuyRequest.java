package com.ecommerce.api;

public class BuyRequest {
    private String productId;
    private int quantity;

    // Getters and Setters are MANDATORY here! 
    // Spring Boot uses them under the hood to automatically translate the JSON into this Java object.
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}