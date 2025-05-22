package com.local.customer.Exception;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException{

    private final String msg;
}
