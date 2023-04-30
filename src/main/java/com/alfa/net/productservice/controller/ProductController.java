package com.alfa.net.productservice.controller;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.exception.enums.FriendlyMessageCodes;
import com.alfa.net.productservice.exception.utils.FriendlyMessageUtils;
import com.alfa.net.productservice.repository.entity.Product;
import com.alfa.net.productservice.request.ProductCreateRequest;
import com.alfa.net.productservice.response.FriendlyMessage;
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
       Product product= iProductRepositoryService.createProduct(language,productCreateRequest);
       ProductResponse productResponse= convertorProductResponse(product);
      log.debug("[{}][createProduct] -> request: {}",this.getClass().getSimpleName(),productResponse);
      return InternalApiResponse.<ProductResponse>builder()
              .friendlyMessage(FriendlyMessage
                      .builder()
                      .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                      .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                      .build())
              .httpStatus(HttpStatus.CREATED)
              .hasError(false)
              .payload(productResponse)
              .build();


    }

  private ProductResponse convertorProductResponse(Product product) {
    return ProductResponse.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .quantity(product.getQuantity())
            .price(product.getPrice())
            .productCreatedDate(product.getProductCreateDate().getTime())
            .productUpdatedDate(product.getProductUpdateDate().getTime())
            .build();
  }
}
