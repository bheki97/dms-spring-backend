package com.bheki97.dmsspringbackend.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DisasterReportDto {
    private long disasterReportId;
    private TechnicianDto technicianDto;
    private Timestamp reportDate;
    private Timestamp delegationDate;
    private Timestamp technicianAttendDate;
    private Timestamp completeDate;

}
