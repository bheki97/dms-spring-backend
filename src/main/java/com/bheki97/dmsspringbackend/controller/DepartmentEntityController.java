package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.entity.DepartmentEntity;
import com.bheki97.dmsspringbackend.service.departmententitymanager.DepartmentEntityManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "http://localhost:4200/")
public class DepartmentEntityController {

    @Autowired
    DepartmentEntityManger departmentEntityManger;

    @PostMapping
    public DepartmentEntity[] addDepartment(@RequestBody DepartmentEntity dept){
        System.out.println(dept.getDeptName() + " " + " " + dept.isActive()+ dept.getDeptId());
        return departmentEntityManger.addNewDepartment(dept);
    }

    @GetMapping
    public DepartmentEntity[] getAllDepartments(){
        return departmentEntityManger.getAllDepartment();
    }

}
