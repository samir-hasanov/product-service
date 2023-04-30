package com.alfa.net.productservice.controller;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.request.ProductCreateRequest;
import com.alfa.net.productservice.response.InternalApiResponse;
import com.alfa.net.productservice.response.ProductResponse;
import com.alfa.net.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController(value = "/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductRepositoryService iProductRepositoryService;
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(
            @PathVariable("language") Language language,
    @RequestBody
    ProductCreateRequest productCreateRequest){
      log.debug("[{}][createProduct] -> request: {}",this.getClass().getSimpleName(),productCreateRequest);
        return null;
    }
}
