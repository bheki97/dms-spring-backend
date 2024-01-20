package com.bheki97.dmsspringbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class DepartmentEntity {

    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deptId;
    @Column(name = "dept_name",unique = true)
    private String deptName;
    private boolean active;


}
