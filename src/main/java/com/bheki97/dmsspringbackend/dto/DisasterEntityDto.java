package com.bheki97.dmsspringbackend.dto;

import com.bheki97.dmsspringbackend.enums.DisasterType;
import lombok.Data;

import java.io.File;
import java.sql.Timestamp;

@Data
public class DisasterEntityDto {

    private long disasterId;
    private String disasterDesc;
    private DisasterType type;
    private String longitude;
    private String latitude;
    private File imgFile;


    //disaster Report Fields
    private long reportId;
    private long reporterId;
    private long technicianId;
    private Timestamp reportDate;
    private Timestamp delegationDate;
    private Timestamp technicianAttendDate;
    private Timestamp completeDate;
}
