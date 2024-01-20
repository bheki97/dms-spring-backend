package com.bheki97.dmsspringbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "specialities")
public class SpecialityEntity {

    @Id
    @Column(name = "spec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long specId;
    @Column(name = "spec_name",unique = true)
    private String specName;
    private boolean active;
}
