package com.bheki97.dmsspringbackend.service.departmententitymanager.impl;

import com.bheki97.dmsspringbackend.entity.DepartmentEntity;
import com.bheki97.dmsspringbackend.exception.DMSException;
import com.bheki97.dmsspringbackend.repository.DepartmentEntityRepository;
import com.bheki97.dmsspringbackend.service.departmententitymanager.DepartmentEntityManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentEntityManagerImpl implements DepartmentEntityManger {

    @Autowired
    private DepartmentEntityRepository deptRepository;
    @Override
    public DepartmentEntity addNewDepartment(DepartmentEntity entity) {
        if(entity.getDeptName()==null ||entity.getDeptName().isEmpty()){
            throw new DMSException("Cannot have empty fields");
        }

        if (deptRepository.existsByDeptName(entity.getDeptName())){
            throw new DMSException("Department with the same name exists");
        }


        entity.setActive(true);
        entity.setDeptId(-1);



        return deptRepository.save(entity);
    }

    @Override
    public List<DepartmentEntity> getAllDepartment() {
        return deptRepository.findAll();
    }

    @Override
    public List<DepartmentEntity> getAllActiveDepartment() {
        return deptRepository.findAllByActive(true);
    }
}
