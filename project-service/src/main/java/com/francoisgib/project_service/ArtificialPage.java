package com.francoisgib.project_service;

import lombok.Getter;

import java.util.List;

@Getter
public class ArtificialPage<T> {
    private final List<T> content;
    private final int totalElements;
    private final int totalPages;

    public ArtificialPage(List<T> content, int pageNumber, int pageSize) {
        this.content = content.subList(pageSize * pageNumber, Math.min(content.size(), pageSize * (pageNumber + 1)));
        this.totalElements = content.size();
        this.totalPages = (content.size() / pageSize) + 1;
    }
}
