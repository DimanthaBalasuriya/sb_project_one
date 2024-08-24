package com.example.product_service.repo;

import com.example.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM Product WHERE product_id=?1", nativeQuery = true)
    Product getProductById(Integer productId);

}
