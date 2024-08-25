package com.francoisgib.project_service;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Permission {
    @Column(unique = true, nullable = false)
    private String name;
}
