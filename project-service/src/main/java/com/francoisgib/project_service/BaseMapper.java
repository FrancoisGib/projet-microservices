package com.francoisgib.project_service;

import java.util.List;
import java.util.Set;

public interface BaseMapper<T, DTO> {
    DTO toDTO(T object);
    List<DTO> toDTO(List<T> objects);
    Set<DTO> toDTO(Set<T> objects);
}
