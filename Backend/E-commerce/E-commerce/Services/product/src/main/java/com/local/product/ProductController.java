package com.local.product;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/apt/v1/products")
//@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<Integer> createProduct(
        @RequestBody @Valid ProductRequest productRequest
    ) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }



    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequests
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequests));
    }


    @GetMapping("{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
