package com.bheki97.dmsspringbackend.controller;


import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;

@RestController
public class DisasterEntityManagerController {


    @GetMapping("/test")
    public String getPath() throws IOException {
        ResourceLoader resourceLoader = new PathMatchingResourcePatternResolver();
        System.out.println(Path.of("disaster-images").toAbsolutePath().toString());
        return null;
    }
}
