package com.example.order2.product;

import jakarta.validation.constraints.*;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @Positive(message = "Quantity is mandatory")
        double quantity

) {
}
