package com.ecommerce.api;

// Notice it says "interface" instead of "class"!
public interface PaymentProcessor {
    
    // This is the contract. Every game disc MUST have a "pay" method.
    String pay(double amount);
    
}