package com.francoisgib.project_service;

import java.util.List;

public interface BaseMapper<T, DTO> {
    DTO toDTO(T object);
    List<DTO> toDTO(List<T> objects);
}
