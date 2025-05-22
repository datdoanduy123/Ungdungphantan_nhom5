package com.local.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer

) {
}
