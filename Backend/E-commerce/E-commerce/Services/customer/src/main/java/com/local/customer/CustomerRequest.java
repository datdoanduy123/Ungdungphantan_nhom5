package com.local.customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
public record CustomerRequest
        (String id,
         @NotNull(message = "Ho khong duoc trong")
         String firstname,

         @NotNull(message = "Ten khong duoc trong")
         String lastname,

         @Email(message = "Ban phai dien Email")
         String email,

         @NotNull(message = "Ban phai dien dia chi")
         Address address) {

}
