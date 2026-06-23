package com.ecommerce.api;

// A specific remote ONLY for things that can be refunded!
public interface Refundable {
    String refund(double amount);
}