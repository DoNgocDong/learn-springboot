package com.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Category))
            return false;

        return id != null && id.equals( ((Category) o).id );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
