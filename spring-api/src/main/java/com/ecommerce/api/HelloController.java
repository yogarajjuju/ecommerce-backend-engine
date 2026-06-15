package com.ecommerce.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController tells Spring: "This class is a Drive-Thru window!"
@RestController
public class HelloController {

    // @GetMapping tells Spring: "If a user visits my-website.com/hello, run this method!"
    @GetMapping("/hello")
    public String sayHello() {
        return "🔥 BOOM! Your Spring Boot API is officially live on the internet!";
    }
}