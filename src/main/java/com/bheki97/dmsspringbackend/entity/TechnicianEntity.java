package com.bheki97.dmsspringbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "technicians")
public class TechnicianEntity {

    @Id
    @Column(name = "technician_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long technicianId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private DepartmentEntity department;
    @ManyToOne
    @JoinColumn(name = "spec_id")
    private SpecialityEntity speciality;

}
