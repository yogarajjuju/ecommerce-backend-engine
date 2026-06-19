package com.ecommerce.api;

import org.springframework.web.bind.annotation.*;

@CrossOrigin // <-- THIS IS THE NEW VIP PASS!
@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/buy")
    public String buyProduct(@RequestBody BuyRequest request) {
        return service.processOrder(request.getProductId(), request.getQuantity());
    }
}