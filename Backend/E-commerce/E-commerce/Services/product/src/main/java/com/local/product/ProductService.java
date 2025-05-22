package com.local.product;

import com.local.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductReponsitory productReponsitory;
    private final ProductMapper productMapper;

    public Integer createProduct( ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productReponsitory.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts( List<ProductPurchaseRequest> productPurchaseRequest) {
        var productIds = productPurchaseRequest
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productReponsitory.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exits");
        }
        var storeRequest = productPurchaseRequest
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for( int i = 0; i <storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storeRequest.get(i);
            if( product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("khong co du so luong cho::" +productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productReponsitory.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productReponsitory.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productReponsitory.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
