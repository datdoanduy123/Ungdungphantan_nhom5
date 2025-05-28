package com.local.ecommerce.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        BigDecimal amount
) {
}
