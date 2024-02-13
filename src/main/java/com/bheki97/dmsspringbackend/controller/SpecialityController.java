package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.entity.SpecialityEntity;
import com.bheki97.dmsspringbackend.service.specialityentitymanager.impl.SpecialityEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/speciality")
@CrossOrigin(origins = "http://localhost:4200/")
public class SpecialityController {

    @Autowired
    SpecialityEntityManagerImpl specialityEntityManager;

    @PostMapping
    public SpecialityEntity[] addSpeciality(@RequestBody SpecialityEntity speciality){
        return specialityEntityManager.addNewSpeciality(speciality);
    }

    @PutMapping("/{specId}")
    public boolean toggleSpeciality(@PathVariable long specId){
        return specialityEntityManager.toggleSpeciality(specId);
    }

    @GetMapping
    public SpecialityEntity[] getAllSpecialities(){

        return specialityEntityManager.getAllSpeciality();
    }

}
