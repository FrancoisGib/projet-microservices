package com.francoisgib.kube_service.deployments.mappers;

import com.francoisgib.kube_service.BaseMapper;
import com.francoisgib.kube_service.deployments.models.DeploymentDTO;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = DeploymentTemplateMapper.class)
public interface DeploymentMapper extends BaseMapper<V1Deployment, DeploymentDTO> {
    DeploymentMapper INSTANCE = Mappers.getMapper(DeploymentMapper.class);

    @Mapping(target = "uid", source = "deployment.metadata.uid")
    @Mapping(target = "name", source = "deployment.metadata.name")
    @Mapping(target = "namespace", source = "deployment.metadata.namespace")
    @Mapping(target = "creationTimestamp", source = "deployment.metadata.creationTimestamp")
    @Mapping(target = "labels", source = "deployment.metadata.labels")
    @Mapping(target = "spec.template", source = "deployment.spec.template")
    DeploymentDTO toDTO(V1Deployment deployment);

    default List<DeploymentDTO> toDTO(V1DeploymentList deploymentList) {
        return deploymentList.getItems().stream()
                .map(INSTANCE::toDTO).toList();
    }
}
