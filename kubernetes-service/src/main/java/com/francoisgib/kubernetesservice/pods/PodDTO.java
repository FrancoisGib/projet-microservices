package com.francoisgib.kubernetesservice.pods;

import com.francoisgib.kubernetesservice.KubernetesResourceDTO;
import io.kubernetes.client.openapi.models.V1Container;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PodDTO extends KubernetesResourceDTO {
    private List<V1Container> containers;
}
