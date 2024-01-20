package com.bheki97.dmsspringbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "technicians")
public class TechnicianEntity extends UserEntity{

    @Column(name = "technician_id")
    private long technicianId;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private DepartmentEntity department;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private SpecialityEntity speciality;

}
