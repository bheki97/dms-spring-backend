package com.bheki97.dmsspringbackend.controller;


import com.bheki97.dmsspringbackend.entity.DepartmentEntity;
import com.bheki97.dmsspringbackend.service.departmententitymanager.DepartmentEntityManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/department")
public class DepartmentEntityController {

    @Autowired
    DepartmentEntityManger departmentEntityManger;

    @PostMapping("/add-dept")
    public DepartmentEntity addDepartment(@RequestBody DepartmentEntity dept){
        return departmentEntityManger.addNewDepartment(dept);
    }

}
