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
        
        // 1. Grab the Bitcoin disc
        PaymentProcessor myPaymentMethod = new BitcoinPayment();
        
        // 2. Hand ALL THREE things to the Chef (ID, Quantity, AND the Disc)
        return service.processOrder(request.getProductId(), request.getQuantity(), myPaymentMethod);
    }
} // <-- This is the curly bracket that was missing!