package com.example.order2;

import com.example.order2.product.PurchaseRequest;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive( message = "So luong don hang toi thieu phai la 1")
        BigDecimal amout,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Khong duoc de trong")
        @NotBlank(message = "Khong dc de trong 1")
        String customerId,
        @NotEmpty(message = "You should at leat purchase one product")
        List<PurchaseRequest> products
) {
}
