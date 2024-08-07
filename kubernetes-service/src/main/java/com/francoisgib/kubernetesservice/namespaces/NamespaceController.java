package com.francoisgib.kubernetesservice.namespaces;

import com.francoisgib.kubernetesservice.MessageService;
import io.kubernetes.client.openapi.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/namespaces")
@Slf4j
public class NamespaceController {
    private final NamespaceService namespaceService;

    private final MessageService messageService;

    @GetMapping
    public List<NamespaceDTO> getAllNamespaces() throws ApiException {
        return NamespaceMapper.INSTANCE.toDTO(namespaceService.getAllNamespaces());
    }

    @GetMapping("/{namespaceName}")
    public NamespaceDTO getNamespace(@PathVariable String namespaceName) throws ApiException {
        return NamespaceMapper.INSTANCE.toDTO(namespaceService.getNamespace(namespaceName));
    }

    @PostMapping("/{namespaceName}")
    public NamespaceDTO createNamespace(@PathVariable String namespaceName) throws ApiException {
        messageService.sendLogMessage("Namespace created : " + namespaceName);
        return NamespaceMapper.INSTANCE.toDTO(namespaceService.createNamespace(namespaceName));
    }

    @DeleteMapping("/{namespaceName}")
    public ResponseEntity<Void> deleteNamespace(@PathVariable String namespaceName) throws ApiException {
        HttpStatus status = HttpStatus.OK;
        try {
            namespaceService.deleteNamespace(namespaceName);
            messageService.sendLogMessage("Namespace deleted : " + namespaceName);
        }
        catch (ApiException exception) {
            status = HttpStatus.valueOf(exception.getCode());
            messageService.sendLogMessage("Error deleting namespace : " + namespaceName);
        }
        return new ResponseEntity<>(status);
    }
}
