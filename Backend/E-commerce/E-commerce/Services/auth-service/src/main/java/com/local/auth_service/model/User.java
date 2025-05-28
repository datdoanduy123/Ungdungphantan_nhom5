package com.local.auth_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true) private String email;
    private String password;
    @Enumerated(EnumType.STRING) private Role role;
}
