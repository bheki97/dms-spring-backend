package com.bheki97.dmsspringbackend.entity;

import com.bheki97.dmsspringbackend.enums.DisasterType;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "disasters")
public class DisasterEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "disaster_id")
    private long disasterId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id",nullable = false)
    private DisasterReportEntity reportEntity;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private UserEntity reporter;

    @Column(name = "disaster_desc",nullable = false)
    private String disasterDesc;
    @Column(nullable = false)
    private DisasterType type;
    @Column(nullable = false)
    private String longitude;
    @Column(nullable = false)
    private String latitude;
    private String location;
    @Column(name = "img_path",nullable = false)
    private String imgPath;

}
