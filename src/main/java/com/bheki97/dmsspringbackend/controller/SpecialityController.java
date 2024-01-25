package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.entity.SpecialityEntity;
import com.bheki97.dmsspringbackend.service.specialityentitymanager.impl.SpecialityEntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/speciality")
public class SpecialityController {

    @Autowired
    SpecialityEntityManagerImpl specialityEntityManager;

    @PostMapping("/add-spec")
    public SpecialityEntity addSpeciality(@RequestBody SpecialityEntity speciality){
        return specialityEntityManager.addNewSpeciality(speciality);
    }

}
