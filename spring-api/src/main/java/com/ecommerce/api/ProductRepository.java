package com.ecommerce.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // ✨ THE MAGIC TRICK ✨
    // We don't write any SQL. Just by naming the method "findBySku", 
    // Hibernate automatically writes this SQL in the background: 
    // SELECT * FROM products WHERE sku = ?
    Product findBySku(String sku);
}