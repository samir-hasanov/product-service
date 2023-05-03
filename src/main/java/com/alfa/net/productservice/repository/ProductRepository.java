package com.alfa.net.productservice.repository;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
Product getByProductIdAndDeletedFalse(Long productId);
List<Product> getAllByDeletedFalse();
Product getProductByProductId(Long productId);

}
