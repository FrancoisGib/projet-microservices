package com.francoisgib.kubernetesservice.pods;

import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PodMapper {
    PodMapper INSTANCE = Mappers.getMapper(PodMapper.class);

    @Mapping(target = "name", source = "pod.metadata.name")
    @Mapping(target = "uid", source = "pod.metadata.uid")
    @Mapping(target = "containers", source = "pod.spec.containers")
    PodDTO toDTO(V1Pod pod);

    default List<PodDTO> toDTO(V1PodList podList) {
        return podList.getItems().stream()
                .map(INSTANCE::toDTO).toList();
    }
}
