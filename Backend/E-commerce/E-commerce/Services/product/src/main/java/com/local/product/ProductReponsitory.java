package com.local.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReponsitory extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productId);
}
