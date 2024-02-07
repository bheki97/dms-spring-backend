package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.dto.AssignTechnicianDto;
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

    @PostMapping("/assign-tech")
    public boolean assignTechnician(@RequestBody AssignTechnicianDto dto){
        return disasterEntityManager.assignTechnicianToDisaster(dto);
    }

    @GetMapping("/attend/{reportId}")
    public boolean attendDisaster(@PathVariable long reportId){
        return disasterEntityManager.attendDisaster(reportId);
    }

    @GetMapping("/technician/{technicianId}")
    public DisasterEntityDto[] getAllDisastersOfTechnician(@PathVariable long technicianId){
        return disasterEntityManager.getDisastersAssignToTechnician(technicianId);
    }


    @GetMapping
    public DisasterEntityDto[] getAllReportedDisaster(){
        return disasterEntityManager.getAllDisasters();
    }
    @GetMapping("/{reporterId}")
    public DisasterEntityDto[] getAllReportedDisasterById(@PathVariable long reporterId){
        return disasterEntityManager.getAllMyReportedDisasters(reporterId);
    }

    @GetMapping("/incomplete/{technicianId}")
    public DisasterEntityDto[] getIncompleteTechnicianDisasters(@PathVariable long technicianId){
        return disasterEntityManager.getAllTechnicianActiveDisasters(technicianId);
    }
    @GetMapping("/complete/{technicianId}")
    public DisasterEntityDto[] getCompleteTechnicianDisasters(@PathVariable long technicianId){
        return disasterEntityManager.getAlltechnicianCompletedDisasters(technicianId);
    }

    @GetMapping("/resolve/{reportId}")
    public boolean resolveDisaster(@PathVariable long reportId){
        return disasterEntityManager.notifyCompletion(reportId);
    }

    @GetMapping("/test")
    public String getPath() throws IOException {
        ResourceLoader resourceLoader = new PathMatchingResourcePatternResolver();
        System.out.println(Path.of("disaster-images").toAbsolutePath().toString());
        return null;
    }
}
