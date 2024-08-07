package com.francoisgib.dockerservice;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DockerController {
    private final DockerClient dockerClient;

    @GetMapping
    public List<Image> getAllImages() {
        return dockerClient.listImagesCmd().exec().stream().toList();
    }

    @GetMapping("/{organization}")
    public List<Image> getImagesByOrganization(@PathVariable String organization) {
        return getAllImages().stream()
                .filter(image -> Arrays.stream(image.getRepoTags())
                        .anyMatch(tagName -> tagName.split(":")[0].equals(organization))).toList();
    }
}
