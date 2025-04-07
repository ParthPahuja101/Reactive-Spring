package com.reactive.demo.bookManagement.repo;

import com.reactive.demo.bookManagement.domain.AuditLogs;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends ReactiveCrudRepository<AuditLogs, Long> {
}
