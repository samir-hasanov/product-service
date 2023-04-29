package com.alfa.net.productservice.service;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.repository.entity.Product;
import com.alfa.net.productservice.request.ProductCreateRequest;
import com.alfa.net.productservice.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {
    Product createProduct(Language language, ProductCreateRequest productCreateRequest);
    Product getProduct(Language language,Long productId);
    List<Product> getProducts(Language language);
    Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest);
    Product deleteProduct(Language language,Long productId);
}
