package com.example.test.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLog extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "method is required")
    private String method;

    @NotBlank(message = "req url is required")
    private String url;

    @NotBlank(message = "status_code is required")
    private int status;

    @Column(name = "ms_process")
    @NotBlank(message = "time process is required")
    private int time;

    private String ip;
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RequestLog))
            return false;

        return id != null && id.equals( ((RequestLog) o).id );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
