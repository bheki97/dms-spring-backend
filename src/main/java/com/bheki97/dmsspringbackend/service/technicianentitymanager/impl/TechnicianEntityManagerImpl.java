package com.bheki97.dmsspringbackend.service.technicianentitymanager.impl;

import com.bheki97.dmsspringbackend.dto.TechnicianDto;
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

import java.security.SecureRandom;
import java.util.List;

@Service
public class TechnicianEntityManagerImpl implements TechnicianEntityManager {

    private final static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

    @Autowired
    TechnicianEntityRepository technicianEntityRepository;
    @Autowired
    DepartmentEntityRepository departmentEntityRepository;
    @Autowired
    SpecialityEntityRepository specialityEntityRepository;
    @Override
    public TechnicianDto[] newTechnician(TechnicianDto dto) {
        checkForNullAndEmptyField(dto);
        if (technicianEntityRepository.existsByUserEmail(dto.getEmail())){
            throw new DMSException( "This Email is already in use !");
        }



        DepartmentEntity department = departmentEntityRepository.findById(dto.getDeptId())
                .orElseThrow(() -> new DMSException("Department does not exists"));
        SpecialityEntity speciality = specialityEntityRepository.findById(dto.getSpecId())
                .orElseThrow(() -> new DMSException("Department does not exists"));

        TechnicianEntity entity = createEntity(dto,department,speciality);
        technicianEntityRepository.save(entity);

        return getAllTechnicians();
    }

    @Override
    public TechnicianDto[] getAllTechnicians() {
        List<TechnicianDto> list = technicianEntityRepository.findAll()
                .stream().map(this::toDto).toList();
        TechnicianDto[] arr = new TechnicianDto[list.size()];


        return list.toArray(arr);
    }

    private TechnicianDto toDto(TechnicianEntity entity) {
        TechnicianDto dto = new TechnicianDto();

        dto.setUserId(entity.getUser().getUserId());
        dto.setCellNo(entity.getUser().getCellNo());
        dto.setTechnicianId(entity.getTechnicianId());
        dto.setFirstname(entity.getUser().getFirstname());
        dto.setLastname(entity.getUser().getLastname());
        dto.setEmail(entity.getUser().getEmail());
        dto.setDeptId(entity.getDepartment().getDeptId());
        dto.setDeptName(entity.getDepartment().getDeptName());
        dto.setSpecId(entity.getSpeciality().getSpecId());
        dto.setSpecName(entity.getSpeciality().getSpecName());
        System.out.println(dto);
        return dto;
    }

    private TechnicianEntity createEntity(TechnicianDto dto, DepartmentEntity department, SpecialityEntity speciality) {
        TechnicianEntity entity = new TechnicianEntity();
        entity.setDepartment(department);
        entity.setSpeciality(speciality);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setFirstname(dto.getFirstname());
        userEntity.setLastname(dto.getLastname());
        userEntity.setEmail(dto.getEmail());
        userEntity.setCellNo(dto.getCellNo());
        userEntity.setUserRole("technician");
        userEntity.setPassword(generatePassword());
        entity.setUser(userEntity);


        return entity;
    }

    private String generatePassword(){
        StringBuilder randomString = new StringBuilder(10);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(index));
        }

        return randomString.toString();
    }

    private void checkForNullAndEmptyField(TechnicianDto entity) {
        if(
                entity.getEmail()==null
                        ||entity.getEmail().isEmpty()
                        ||entity.getCellNo()==null
                        ||entity.getCellNo().isEmpty()
                        ||entity.getFirstname()==null
                        ||entity.getFirstname().isEmpty()
                        ||entity.getLastname()==null
                        ||entity.getLastname().isEmpty()
        ){
            throw new DMSException("Cannot have any of the user field Empty");
        }
    }
}
