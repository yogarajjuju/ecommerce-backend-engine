package com.ecommerce.api;

// Notice we use a comma to implement MULTIPLE interfaces!
public class CreditCardPayment implements PaymentProcessor, Refundable {
    
    @Override
    public String pay(double amount) {
        return "💳 Processing $" + amount + " through Stripe Credit Card API...";
    }

    @Override
    public String refund(double amount) {
        return "🔄 Refunding $" + amount + " back to the Credit Card.";
    }
}