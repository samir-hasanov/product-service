package com.alfa.net.productservice.controller;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.exception.enums.FriendlyMessageCodes;
import com.alfa.net.productservice.exception.utils.FriendlyMessageUtils;
import com.alfa.net.productservice.repository.entity.Product;
import com.alfa.net.productservice.request.ProductCreateRequest;
import com.alfa.net.productservice.request.ProductUpdateRequest;
import com.alfa.net.productservice.response.FriendlyMessage;
import com.alfa.net.productservice.response.InternalApiResponse;
import com.alfa.net.productservice.response.ProductResponse;
import com.alfa.net.productservice.service.IProductRepositoryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{language}/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language")Language language,
                                                           @PathVariable("productId")Long productId){
        log.debug("[{}][getProduct] -> request: {}",this.getClass().getSimpleName(),productId);
                Product product= iProductRepositoryService.getProduct(language,productId);
                ProductResponse productResponse=convertorProductResponse(product);
        log.debug("[{}][getProduct] -> request: {}",this.getClass().getSimpleName(),productResponse);
      return InternalApiResponse.<ProductResponse>builder()
              .httpStatus(HttpStatus.OK)
              .hasError(false)
              .payload(productResponse)
              .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{language}/update/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(@PathVariable("language")Language language,
                                                              @PathVariable("productId") Long productId,
                                                              @RequestBody ProductUpdateRequest productUpdateRequest){

        log.debug("[{}][getProduct] -> request: {}",this.getClass().getSimpleName(),productUpdateRequest);
        Product product=iProductRepositoryService.updateProduct(language,productId,productUpdateRequest);
        ProductResponse productResponse=convertorProductResponse(product);
        log.debug("[{}][getProduct] -> request: {}",this.getClass().getSimpleName(),productResponse);

      return InternalApiResponse.<ProductResponse>builder()
              .friendlyMessage(FriendlyMessage.builder()
                      .title(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.SUCCESS))
                      .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_UPDATED))
                      .build())
              .httpStatus(HttpStatus.OK)
              .hasError(false)
              .payload(productResponse)
              .build();
    }
@ResponseStatus(HttpStatus.OK)
@GetMapping("/{language}/products")
    public InternalApiResponse<List<ProductResponse>> getProducts(@PathVariable("language") Language language){
      log.debug("{}[getProducts]",this.getClass().getSimpleName());
      List<Product> products=iProductRepositoryService.getProducts(language);
      List<ProductResponse> productResponses=convertProductResponseList(products);
        log.debug("{}[getProducts]",this.getClass().getSimpleName());
              return InternalApiResponse.<List<ProductResponse>>builder()
                      .httpStatus(HttpStatus.OK)
                      .hasError(false)
                      .payload(productResponses)
                      .build();
    }

    private List<ProductResponse> convertProductResponseList(List<Product> products){
      return products.stream()
              .map(arg->ProductResponse.builder()
                      .productId(arg.getProductId())
                      .productName(arg.getProductName())
                      .quantity(arg.getQuantity())
                      .price(arg.getPrice())
                      .productCreatedDate(arg.getProductCreateDate().getTime())
                      .productUpdatedDate(arg.getProductUpdateDate().getTime())
                      .build())
              .collect(Collectors.toList());
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
