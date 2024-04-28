package com.devjin.springstu.domain.repository;

import com.devjin.springstu.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    @Override
    Optional<Product> findById(String s);
    Optional<Product> findByName(String s);
}
