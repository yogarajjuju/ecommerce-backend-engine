package com.ecommerce.api;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
@PostMapping("/buy")
    public String buyProduct(@RequestBody BuyRequest request) {
        
        PaymentProcessor myPaymentMethod;

        // The Waiter reads the JSON slip and grabs the correct disc!
        if ("Bitcoin".equalsIgnoreCase(request.getPaymentType())) {
            myPaymentMethod = new BitcoinPayment();
        } else if ("GiftCard".equalsIgnoreCase(request.getPaymentType())) {
            myPaymentMethod = new GiftCardPayment();
        } else {
            // Default to Credit Card if they don't specify
            myPaymentMethod = new CreditCardPayment(); 
        }
        
        // Hand the ID, Quantity, and dynamically chosen Disc to the Chef!
        return service.processOrder(request.getProductId(), request.getQuantity(), myPaymentMethod);
    }


    // A quick test route we can hit from the browser!
    @GetMapping("/test-refund")
    public String testRefund() {
        
        // 1. A customer wants a refund, but they paid with a Gift Card!
        PaymentProcessor myPayment = new GiftCardPayment();

        // 2. The Customer Service code safely checks the label first
        if (myPayment instanceof Refundable) {
            
            // If it has the Refundable tag, we are allowed to press the button
            Refundable refundablePayment = (Refundable) myPayment;
            return refundablePayment.refund(99.99);
            
        } else {
            
            // If it doesn't have the tag, we stop immediately. No crash!
            return "🛑 STOP: Gift Cards do not have a refund button. Server saved from crashing!";
            
        }
    }
} // <-- This is the curly bracket that was missing!