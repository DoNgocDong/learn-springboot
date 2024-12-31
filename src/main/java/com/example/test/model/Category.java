package com.example.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "category_name is required")
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
