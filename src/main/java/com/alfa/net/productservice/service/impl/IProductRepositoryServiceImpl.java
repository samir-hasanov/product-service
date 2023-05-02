package com.alfa.net.productservice.service.impl;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.exception.enums.FriendlyMessageCodes;
import com.alfa.net.productservice.exception.exceptions.ProductNotCreatedException;
import com.alfa.net.productservice.exception.exceptions.ProductNotFoundException;
import com.alfa.net.productservice.repository.ProductRepository;
import com.alfa.net.productservice.repository.entity.Product;
import com.alfa.net.productservice.request.ProductCreateRequest;
import com.alfa.net.productservice.request.ProductUpdateRequest;
import com.alfa.net.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class IProductRepositoryServiceImpl implements IProductRepositoryService {
    private final ProductRepository productRepository;
    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
        log.info("[{} createProduct] -> request : {} "
                ,this.getClass().getSimpleName(),productCreateRequest);
        try{
     Product product=Product.builder()
             .productName(productCreateRequest.getProductName())
             .quantity(productCreateRequest.getQuantity())
             .price(productCreateRequest.getPrice())
             .deleted(false)
             .build();
     Product productResponse=productRepository.save(product);
     log.debug("[{}][createProduct] -> request: {}",this.getClass().getSimpleName(),productResponse);
     return productResponse;

        }catch (Exception exception){
   throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION,"product request : "+productCreateRequest.toString());
        }

    }

    @Override
    public Product getProduct(Language language, Long productId) {
        log.info("[{}][getProduct] -> request : {}",this.getClass().getSimpleName(),productId);
        Product product=productRepository.getByProductIdAndDeletedFalse(productId);
        if(Objects.isNull(product)){
   throw new ProductNotFoundException(language,FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION,"product not found");
        }
        log.info("[{}][getProduct] -> request : {}",this.getClass().getSimpleName(),product);

        return product;
    }

    @Override
    public List<Product> getProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        log.info("[{}][updateProduct] -> request : {}",this.getClass().getSimpleName(),productUpdateRequest);
        Product product=productRepository.getProduct(language,productId);
        product.setProductName(productUpdateRequest.getProductName());
        product.setQuantity(productUpdateRequest.getQuantity());
        product.setPrice(productUpdateRequest.getPrice());
        product.setProductCreateDate(product.getProductCreateDate());
        product.setProductUpdateDate(new Date());
        Product productResponse=productRepository.save(product);
        log.info("[{}][updatedProduct] -> request : {}",this.getClass().getSimpleName(),productResponse);
        return productResponse;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        return null;
    }
}
