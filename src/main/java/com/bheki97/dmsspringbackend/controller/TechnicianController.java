package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.dto.TechnicianDto;
import com.bheki97.dmsspringbackend.entity.TechnicianEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.service.technicianentitymanager.TechnicianEntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technician")
@CrossOrigin(origins = "http://localhost:4200/")
public class TechnicianController {

    @Autowired
    TechnicianEntityManager technicianEntityManager;

    @PostMapping
    public TechnicianDto[] addNewTechnician(@RequestBody TechnicianDto technician){
        return technicianEntityManager.newTechnician(technician);
    }

    @GetMapping("/user/{userId}")
    public TechnicianDto getTechnicianByUserId(@PathVariable long userId){
        return technicianEntityManager.getTechnicianByUserId(userId);
    }

    @GetMapping
    public TechnicianDto[] getAllTechnicians(){
        return technicianEntityManager.getAllTechnicians();
    }

}
