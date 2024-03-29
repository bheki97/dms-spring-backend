package com.bheki97.dmsspringbackend.service.departmententitymanager;

import com.bheki97.dmsspringbackend.entity.DepartmentEntity;

import java.util.List;


public interface DepartmentEntityManger {

    DepartmentEntity[] addNewDepartment(DepartmentEntity entity);
    DepartmentEntity[] getAllDepartment();
    DepartmentEntity[] getAllActiveDepartment();
    boolean toggleDepartment(long deptId);
}
