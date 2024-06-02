package com.devjin.springstu.domain.repository;

import com.devjin.springstu.domain.entity.Product;
import com.devjin.springstu.domain.entity.ProductPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductPriceInfoRepository extends JpaRepository<ProductPriceInfo,String> {
    @Override
    Optional<ProductPriceInfo> findById(String s);
    Optional<ProductPriceInfo> findByProduct(Product prod);
    //Optional<Product> findByName(String s);
    //Optional<List<Product>> findAllByNameLike(String s);
}
