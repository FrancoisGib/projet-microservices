package com.francoisgib.kube_service.deployments.mappers;

import com.francoisgib.kube_service.BaseMapper;
import com.francoisgib.kube_service.deployments.models.ContainerEnv;
import io.kubernetes.client.openapi.models.V1EnvVar;
import org.mapstruct.Mapper;

@Mapper
public interface ContainerEnvMapper extends BaseMapper<V1EnvVar, ContainerEnv> {
}
