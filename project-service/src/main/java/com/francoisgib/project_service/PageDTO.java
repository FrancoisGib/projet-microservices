package com.francoisgib.project_service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageDTO<T> {
    private long totalPages;
    private long totalElements;
    private List<T> content;
}
