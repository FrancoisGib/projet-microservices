package com.francoisgib.kube_service.deployments.mappers;

import com.francoisgib.kube_service.BaseMapper;
import com.francoisgib.kube_service.deployments.models.DeploymentTemplate;
import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1ContainerPort;
import io.kubernetes.client.openapi.models.V1EnvVar;
import io.kubernetes.client.openapi.models.V1PodTemplateSpec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ContainerPortMapper.class, ContainerEnvMapper.class})
public interface DeploymentTemplateMapper extends BaseMapper<V1PodTemplateSpec, DeploymentTemplate> {
    DeploymentTemplateMapper INSTANCE = Mappers.getMapper(DeploymentTemplateMapper.class);

    @Override
    @Mapping(target = "name", source = "templateSpec.metadata.name")
    @Mapping(target = "labels", source = "templateSpec.metadata.labels")
    @Mapping(target = "env", source = "templateSpec.spec.containers", qualifiedByName = "containerListToContainerEnv")
    @Mapping(target = "image", source = "templateSpec.spec.containers", qualifiedByName = "containerListToContainerImage")
    @Mapping(target = "ports", source = "templateSpec.spec.containers", qualifiedByName = "containerListToContainerPorts")
    DeploymentTemplate toDTO(V1PodTemplateSpec templateSpec);

    @Named("containerListToContainerImage")
    default String containerListToContainerImage(List<V1Container> containers) {
        return containers.get(0).getImage();
    }

    @Named("containerListToContainerPorts")
    default List<V1ContainerPort> containerListToContainerPorts(List<V1Container> containers) {
        return containers.get(0).getPorts();
    }

    @Named("containerListToContainerEnv")
    default List<V1EnvVar> containerListToContainerEnv(List<V1Container> containers) {
        return containers.get(0).getEnv();
    }
}
