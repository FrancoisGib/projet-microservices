package com.francoisgib.project_service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface BaseMapper<T, DTO> {
    DTO toDTO(T object);
    List<DTO> toDTO(List<T> objects);
    Set<DTO> toDTO(Set<T> objects);

    default PageDTO<DTO> toDTO(Page<T> objects) {
        Page<DTO> page = objects.map(this::toDTO);
        return new PageDTO<>(page.getTotalPages(), page.getTotalElements(), page.getContent());
    }
}
