package com.local.auth_service.dto;

public record CustomerRequest(String id, String firstname, String lastname, String email, Address address) {
}
