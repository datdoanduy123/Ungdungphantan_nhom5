package com.local.auth_service.dto;

public record RegisterRequest(String email, String password, String firstname, String lastname, Address address) {
}
