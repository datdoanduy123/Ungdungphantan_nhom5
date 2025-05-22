package com.example.order2.kafka;

import com.example.order2.PaymentMethod;
import com.example.order2.customer.CustomerReponse;
import com.example.order2.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerReponse customer,
        List<PurchaseResponse> products
) {
}
