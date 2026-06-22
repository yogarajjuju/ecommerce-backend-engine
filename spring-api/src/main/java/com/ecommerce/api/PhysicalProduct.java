package com.ecommerce.api;

// It inherits the basic Product stuff, AND it promises it can be shipped.
public class PhysicalProduct extends Product implements Shippable {
    
    private double weight;

    public PhysicalProduct(String id, String name, double price, int stock, double weight) {
        super(id, name, price, stock); // Passes the basic info back to the main blueprint
        this.weight = weight;
    }

    @Override
    public double getShippingWeight() {
        return this.weight;
    }
}