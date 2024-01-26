package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.entity.TechnicianEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.TechnicianEntityRepository;
import com.bheki97.dmsspringbackend.service.technicianentitymanager.TechnicianEntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    TechnicianEntityManager technicianEntityManager;

    @PostMapping("/add-technician")
    public ResponseEntity<?> newTechnician(@RequestBody TechnicianEntity technician){
        try {
            TechnicianEntity addedTechnician = technicianEntityManager.newTechnician(technician);
            return ResponseEntity.ok(addedTechnician);

        } catch (DMSException dmsException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dmsException.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

//    }
//        return technicianEntityManager.newTechnician(technician);
    }

}
