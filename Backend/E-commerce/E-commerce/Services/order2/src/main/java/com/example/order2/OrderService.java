package com.example.order2;

import com.example.order2.exception.BusinessException;
import com.example.order2.customer.CustomerClient;
import com.example.order2.kafka.OrderConfirmation;
import com.example.order2.kafka.OrderProducer;
import com.example.order2.orderLine.OrderLineRequest;
import com.example.order2.orderLine.OrderLineService;
import com.example.order2.payment.PaymentClient;
import com.example.order2.payment.PaymentRequest;
import com.example.order2.product.ProductClient;
import com.example.order2.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public Integer createOrder(@Valid OrderRequest orderRequest) {
        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Can't create order :: No Customer exists with the provideid ID"));
        var purchasedProduct = this.productClient.purchaseProducts(orderRequest.products());

        var order = this.repository.save(orderMapper.toOrder(orderRequest));

        for( PurchaseRequest purchaseRequest: orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()

                    )
            );
        }

        var paymentRequest = new PaymentRequest(
          orderRequest.amout(),
          orderRequest.paymentMethod(),
          order.getId(),
          order.getReference(),
          customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amout(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProduct
                )
        );

        // send the order confirmation --> notification -ms
        return order.getId();
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Khong tim thay don hang co Id: %d", orderId)));
    }
}
