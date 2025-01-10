package com.example.test.dtos.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

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
}
