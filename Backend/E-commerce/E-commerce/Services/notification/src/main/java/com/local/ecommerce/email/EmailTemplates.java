package com.local.ecommerce.email;

import lombok.Getter;

public enum EmailTemplates {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),

    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.subject = subject;
        this.template = template;
    }
}
