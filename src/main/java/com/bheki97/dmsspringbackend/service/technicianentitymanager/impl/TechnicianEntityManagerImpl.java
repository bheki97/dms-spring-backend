package com.bheki97.dmsspringbackend.service.technicianentitymanager.impl;

import com.bheki97.dmsspringbackend.entity.DepartmentEntity;
import com.bheki97.dmsspringbackend.entity.SpecialityEntity;
import com.bheki97.dmsspringbackend.entity.TechnicianEntity;
import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.DepartmentEntityRepository;
import com.bheki97.dmsspringbackend.repository.SpecialityEntityRepository;
import com.bheki97.dmsspringbackend.repository.TechnicianEntityRepository;
import com.bheki97.dmsspringbackend.service.technicianentitymanager.TechnicianEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicianEntityManagerImpl implements TechnicianEntityManager {

    @Autowired
    TechnicianEntityRepository technicianEntityRepository;
    @Autowired
    DepartmentEntityRepository departmentEntityRepository;
    @Autowired
    SpecialityEntityRepository specialityEntityRepository;
    @Override
    public TechnicianEntity newTechnician(TechnicianEntity technician) {
        checkForNullAndEmptyField(technician);
        if (technicianEntityRepository.existsByEmail(technician.getEmail())){
            throw new DMSException( "This Email is already in use !");
        }

        DepartmentEntity department = departmentEntityRepository.findById(technician.getDepartment().getDeptId())
                .orElseThrow(() -> new IllegalArgumentException("Department does not exists"));
        SpecialityEntity speciality = specialityEntityRepository.findById(technician.getSpeciality().getSpecId())
                .orElseThrow(() -> new IllegalArgumentException("specialityEntityRepository"));


 //       if (){
//           throw new DMSException("Department does not exists");
//       }
//        if (!specialityEntityRepository.existsById(technician.getSpeciality().getSpecId())){
//            throw new DMSException("Department does not exists");
//        }
        technician.setDepartment(department);
        technician.setSpeciality(speciality);
        return technicianEntityRepository.save(technician);
    }

    private void checkForNullAndEmptyField(TechnicianEntity entity) {
        if(
                entity.getEmail()==null
                        ||entity.getEmail().isEmpty()
                        ||entity.getPassword()==null
                        ||entity.getPassword().isEmpty()
                        ||entity.getUserRole() ==null
                        ||entity.getUserRole().isEmpty()
                        ||entity.getCellNo()==null
                        ||entity.getCellNo().isEmpty()
                        ||entity.getFirstname()==null
                        ||entity.getFirstname().isEmpty()
                        ||entity.getLastname()==null
                        ||entity.getLastname().isEmpty()
                        ||entity.getDepartment() == null
                        ||entity.getSpeciality() == null
        ){
            throw new DMSException("Cannot have any of the user field Empty");
        }
    }
}
