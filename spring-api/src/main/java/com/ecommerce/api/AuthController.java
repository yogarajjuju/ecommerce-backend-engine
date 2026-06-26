package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil; // Bring in the Wristband Printer

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        
        // 1. The Bouncer checks the ID (Hardcoded for now)
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            
            // 2. ID is valid! Print the VIP Wristband
            String vipWristband = jwtUtil.generateToken(request.getUsername());
            
            // 3. Hand the wristband back to the user
            return ResponseEntity.ok(vipWristband);
            
        } else {
            // 4. Fake ID? Kick them out with a 401 Unauthorized Error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ ERROR: Invalid username or password");
        }
    }
}