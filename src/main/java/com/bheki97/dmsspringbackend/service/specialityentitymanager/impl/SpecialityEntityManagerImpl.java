package com.bheki97.dmsspringbackend.service.specialityentitymanager.impl;

import com.bheki97.dmsspringbackend.entity.SpecialityEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.SpecialityEntityRepository;
import com.bheki97.dmsspringbackend.service.specialityentitymanager.SpecialityEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityEntityManagerImpl implements SpecialityEntityManager {

    @Autowired
    private SpecialityEntityRepository specRepository;

    @Override
    public SpecialityEntity addNewSpeciality(SpecialityEntity entity) {
        if(entity.getSpecName()==null ||entity.getSpecName().isEmpty()){
            throw new DMSException("Cannot have empty fields");
        }

        if(specRepository.existsBySpecName(entity.getSpecName())){
            throw new DMSException("Speciality with the same name exists");
        }

        entity.setActive(true);
        entity.setSpecId(-1);
        return null;
    }

    @Override
    public List<SpecialityEntity> getAllSpeciality() {
        return specRepository.findAll();
    }

    @Override
    public List<SpecialityEntity> getAllActiveSpeciality() {
        return specRepository.findAllByActive(true);
    }
}
