package com.francoisgib.dockerservice;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DockController {
    private final DockerClient dockerClient;

    @GetMapping
    public List<Image> getAllImages() {
        return dockerClient.listImagesCmd().exec();
    }
}
