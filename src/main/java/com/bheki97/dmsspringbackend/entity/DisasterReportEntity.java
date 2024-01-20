package com.bheki97.dmsspringbackend.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "disaster_reports")
public class DisasterReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String reportId;
    @OneToOne
    @JoinColumn(name = "technician_id")
    private UserEntity technician;
    @Column(name = "report_date",nullable = false)
    private Timestamp reportDate;
    @Column(name = "delegation_date")
    private Timestamp delegationDate;
    @Column(name = "attend_date")
    private Timestamp technicianAttendDate;
    @Column(name = "complete_date")
    private Timestamp completeDate;

}
