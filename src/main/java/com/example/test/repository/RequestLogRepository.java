package com.example.test.repository;

import com.example.test.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;

@RepositoryRestResource
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    ResponseEntity<RequestLog> create(RequestLog requestLog);
}
