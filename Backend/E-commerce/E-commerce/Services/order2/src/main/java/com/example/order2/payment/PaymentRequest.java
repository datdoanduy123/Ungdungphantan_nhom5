package com.example.order2.payment;

import com.example.order2.PaymentMethod;
import com.example.order2.customer.CustomerReponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerReponse customer

) {
}
