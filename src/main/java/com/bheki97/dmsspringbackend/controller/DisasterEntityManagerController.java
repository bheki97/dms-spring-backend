package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.dto.DisasterEntityDto;
import com.bheki97.dmsspringbackend.service.disasterentitymanager.DisasterEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/disaster")
public class DisasterEntityManagerController {

    @Autowired
    private DisasterEntityManager disasterEntityManager;

    @PostMapping
    public DisasterEntityDto reportNewDisaster(@RequestBody DisasterEntityDto dto){
//        System.out.println(dto);
        return disasterEntityManager.reportNewDisaster(dto);
    }

    @GetMapping
    public DisasterEntityDto[] getAllDisasters(){
        return disasterEntityManager.getAllDisasters();
    }

    @GetMapping("/test")
    public String getPath() throws IOException {
        ResourceLoader resourceLoader = new PathMatchingResourcePatternResolver();
        System.out.println(Path.of("disaster-images").toAbsolutePath().toString());
        return null;
    }
}
