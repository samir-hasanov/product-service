package com.alfa.net.productservice.service.impl;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.exception.enums.FriendlyMessageCodes;
import com.alfa.net.productservice.exception.exceptions.ProductNotCreatedException;
import com.alfa.net.productservice.repository.ProductRepository;
import com.alfa.net.productservice.repository.entity.Product;
import com.alfa.net.productservice.request.ProductCreateRequest;
import com.alfa.net.productservice.request.ProductUpdateRequest;
import com.alfa.net.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
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
   throw new ProductNotCreatedException(language, FriendlyMessageCodes.RRODUCT_NOTCREATED_EXCEPTION,"product request : "+productCreateRequest.toString());
        }

    }

    @Override
    public Product getProduct(Language language, Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        return null;
    }
}
