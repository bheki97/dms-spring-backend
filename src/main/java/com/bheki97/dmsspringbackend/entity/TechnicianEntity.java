package com.bheki97.dmsspringbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "technicians")
public class TechnicianEntity extends UserEntity{

    @Column(name = "technician_id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long technicianId;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private DepartmentEntity department;
    @ManyToOne
    @JoinColumn(name = "spec_id")
    private SpecialityEntity speciality;

}
