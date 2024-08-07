package com.francoisgib.kubernetesservice.deployments;

import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeploymentMapper {
    DeploymentMapper INSTANCE = Mappers.getMapper(DeploymentMapper.class);

    @Mapping(target = "name", source = "deployment.metadata.name")
    @Mapping(target = "uid", source = "deployment.metadata.uid")
    DeploymentDTO toDTO(V1Deployment deployment);

    default List<DeploymentDTO> toDTO(V1DeploymentList deploymentList) {
        return deploymentList.getItems().stream()
                .map(INSTANCE::toDTO).toList();
    }
}
