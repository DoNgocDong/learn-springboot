package com.example.test.model;

import com.example.test.dtos.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private UserRole role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AccountEntity))
            return false;

        return id != null && id.equals( ((AccountEntity) o).id );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
