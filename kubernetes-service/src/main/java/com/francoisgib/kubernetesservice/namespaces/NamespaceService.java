package com.francoisgib.kubernetesservice.namespaces;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class NamespaceService {
    private final CoreV1Api kubernetesCoreAPI;

    public V1NamespaceList getAllNamespaces() throws ApiException {
        return kubernetesCoreAPI.listNamespace().execute();
    }

    public V1Namespace getNamespace(String namespace) throws ApiException {
        return kubernetesCoreAPI.readNamespace(namespace).execute();
    }

    public V1Namespace createNamespace(String namespace) throws ApiException {
        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName(namespace);
        metadata.setUid(UUID.randomUUID().toString());
        V1Namespace newNamespace = new V1Namespace().metadata(metadata);
        return kubernetesCoreAPI.createNamespace(newNamespace).execute();
    }

    public void deleteNamespace(String namespaceName) throws ApiException {
        kubernetesCoreAPI.deleteNamespace(namespaceName).execute();
    }
}
