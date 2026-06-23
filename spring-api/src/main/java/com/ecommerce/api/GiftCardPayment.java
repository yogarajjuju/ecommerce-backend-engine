
package com.ecommerce.api;

public class GiftCardPayment implements PaymentProcessor {
    
    @Override
    public String pay(double amount) {
        return "🎁 Processing $" + amount + " using Store Gift Card...";
    }
    
    // We don't even write a refund method! It's completely safe.
}