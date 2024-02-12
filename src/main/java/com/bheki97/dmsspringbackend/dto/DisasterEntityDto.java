package com.bheki97.dmsspringbackend.dto;

import com.bheki97.dmsspringbackend.enums.DisasterType;
import lombok.Data; 


@Data
public class DisasterEntityDto {

    private long disasterId;
    private String disasterDesc;
    private DisasterType type;
    private String longitude;
    private String latitude;
    private String imgFileName;
    private String imgFileContent;
    private String location;

    //disaster Report Fields
    private ReporterDto reporter;
    private DisasterReportDto reportDto;
}
