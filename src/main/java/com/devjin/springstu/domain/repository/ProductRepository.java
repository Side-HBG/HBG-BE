package com.devjin.springstu.domain.repository;

import com.devjin.springstu.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    @Override
    Optional<Product> findById(String s);



    Optional<Product> findByName(String s);
    Optional<Product> findByAppid(Integer s);
    Optional<List<Product>> findAllByAppid(Integer s);
    Optional<List<Product>> findAllByNameLikeIgnoreCase(String s);
}
