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
    public DepartmentEntity[] addNewDepartment(DepartmentEntity entity) {
        if(entity.getDeptName()==null ||entity.getDeptName().isEmpty()){
            throw new DMSException("Cannot have empty fields");
        }

        if (deptRepository.existsByDeptName(entity.getDeptName())){
            throw new DMSException("Department with the same name exists");
        }

        if(entity.getDeptId() != 0){
            throw new DMSException("Invalid department Id");
        }

        entity.setActive(true);
        deptRepository.save(entity);
        return getAllDepartment();
    }

    @Override
    public DepartmentEntity[] getAllDepartment() {

        List<DepartmentEntity> list = deptRepository.findAll();
        DepartmentEntity[] arr = new DepartmentEntity[list.size()];

        return list.toArray(arr);
    }

    @Override
    public DepartmentEntity[] getAllActiveDepartment() {
        List<DepartmentEntity> list = deptRepository.findAllByActive(true);
        DepartmentEntity[] arr = new DepartmentEntity[list.size()];

        return list.toArray(arr);}
}
