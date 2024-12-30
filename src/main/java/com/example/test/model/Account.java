package com.example.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email-format incorrect")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(
        min = 5, max = 20,
        message = "Password length must be 5-20 char")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Account))
            return false;

        return id != null && id.equals( ((Account) o).id );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
