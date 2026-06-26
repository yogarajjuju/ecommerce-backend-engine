package com.ecommerce.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // The exact same Secret Ink!
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 3600000;

    // 🖨️ THE PRINTER (Unchanged)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // 🔍 NEW: THE SCANNER (Reads the token)
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // We use the exact same key to decrypt it!
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Returns the username ("admin")
    }
}