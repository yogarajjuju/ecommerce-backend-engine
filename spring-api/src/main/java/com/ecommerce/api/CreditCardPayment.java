package com.ecommerce.api;

// "implements" means we are sliding this disc into the PaymentProcessor drive
public class CreditCardPayment implements PaymentProcessor {
    
    @Override
    public String pay(double amount) {
        // Here is the actual math/logic for a credit card
        return "💳 Processing $" + amount + " through Stripe Credit Card API...";
    }
}