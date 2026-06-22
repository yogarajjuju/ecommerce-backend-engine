package com.ecommerce.api;

// It inherits the basic Product stuff, but notice it does NOT implement Shippable!
public class DigitalProduct extends Product {
    
    private String downloadLink;

    public DigitalProduct(String id, String name, double price, int stock, String downloadLink) {
        super(id, name, price, stock);
        this.downloadLink = downloadLink;
    }

    public String getDownloadLink() {
        return this.downloadLink;
    }
}