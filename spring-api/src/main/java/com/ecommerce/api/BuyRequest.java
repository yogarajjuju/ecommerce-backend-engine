package com.ecommerce.api;

public class BuyRequest {
    private String productId;
    private int quantity;
    private String paymentType; // <-- NEW!

    // Getters
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public String getPaymentType() { return paymentType; } // <-- NEW!

    // Setters
    public void setProductId(String productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; } // <-- NEW!
}