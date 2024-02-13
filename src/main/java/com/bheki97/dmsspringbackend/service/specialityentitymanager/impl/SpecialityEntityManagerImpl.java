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
    public SpecialityEntity[] addNewSpeciality(SpecialityEntity entity) {
        if(entity.getSpecName()==null ||entity.getSpecName().isEmpty()){
            throw new DMSException("Cannot have empty fields");
        }

        if(specRepository.existsBySpecName(entity.getSpecName())){
            throw new DMSException("Speciality with the same name exists");
        }
        if(entity.getSpecId()!=0){
            throw new DMSException("Invalid Speciality Id");
        }

        entity.setActive(true);
        specRepository.save(entity);

        return getAllSpeciality();
    }

    @Override
    public SpecialityEntity[] getAllSpeciality() {
       List<SpecialityEntity> list = specRepository.findAll();
       SpecialityEntity[] arr = new SpecialityEntity[list.size()];

        return list.toArray(arr);
    }

    @Override
    public boolean toggleSpeciality(long specId) {

        SpecialityEntity entity = specRepository
                .findById(specId).orElseThrow( ()->new DMSException("Speciality does not it"));

        entity.setActive(!entity.isActive());

        specRepository.save(entity);
        return true;
    }

    @Override
    public SpecialityEntity[] getAllActiveSpeciality() {


        return (SpecialityEntity[]) specRepository.findAllByActive(true).toArray();
    }
}
