package com.francoisgib.kube_service.deployments.mappers;

import com.francoisgib.kube_service.BaseMapper;
import com.francoisgib.kube_service.deployments.models.ContainerPort;
import io.kubernetes.client.openapi.models.V1ContainerPort;
import org.mapstruct.Mapper;

@Mapper
public interface ContainerPortMapper extends BaseMapper<V1ContainerPort, ContainerPort> {
}
