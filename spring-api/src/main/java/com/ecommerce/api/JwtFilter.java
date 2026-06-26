package com.ecommerce.api;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component // Tells Spring to keep this Checkpoint turned on
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Look at the HTTP Header for the VIP Wristband
        String authHeader = request.getHeader("Authorization");

        // 2. Security Standard: Token headers always start with the word "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            
            // 3. Cut off the first 7 characters ("Bearer ") to get the raw JWT string
            String token = authHeader.substring(7);

            try {
                // 4. Scan the wristband using our secret key
                String username = jwtUtil.extractUsername(token);

                // 5. If the name is legit, open the VIP doors!
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    
                    UsernamePasswordAuthenticationToken vipPass = 
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    
                    // We tell the server: "This user is fully verified. Let them through."
                    SecurityContextHolder.getContext().setAuthentication(vipPass);
                }
            } catch (Exception e) {
                // Hacker tried to forge a wristband! The scanner rejects it silently.
                System.out.println("❌ Fake or Expired Wristband Detected!");
            }
        }

        // 6. Let the request continue to the next door
        filterChain.doFilter(request, response);
    }
}