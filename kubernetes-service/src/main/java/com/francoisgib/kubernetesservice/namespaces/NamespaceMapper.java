package com.francoisgib.kubernetesservice.namespaces;

import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NamespaceMapper {
    NamespaceMapper INSTANCE = Mappers.getMapper(NamespaceMapper.class);

    @Mapping(target = "name", source = "namespace.metadata.name")
    @Mapping(target = "uid", source = "namespace.metadata.uid")
    NamespaceDTO toDTO(V1Namespace namespace);

    default List<NamespaceDTO> toDTO(V1NamespaceList namespaceList) {
        return namespaceList.getItems().stream()
                .map(INSTANCE::toDTO).toList();
    }
}