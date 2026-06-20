package com.ecommerce.api;

public class BitcoinPayment implements PaymentProcessor {
    
    @Override
    public String pay(double amount) {
        // Here is the actual math/logic for crypto
        return "🪙 Transferring $" + amount + " to Bitcoin Wallet...";
    }
}