package com.reactive.demo.bookManagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("audit_logs")
public class AuditLogs {
    @Id
    private Long id;
    private String action;
    private LocalDate timestamp;

    public AuditLogs() {}

    // All-argument constructor
    public AuditLogs(Long id, String action, LocalDate timestamp) {
        this.id = id;
        this.action = action;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
