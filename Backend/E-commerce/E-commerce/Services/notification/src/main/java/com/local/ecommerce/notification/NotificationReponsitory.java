package com.local.ecommerce.notification;

import com.local.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationReponsitory extends MongoRepository<Notification, String> {
}
