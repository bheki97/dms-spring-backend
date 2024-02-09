package com.bheki97.dmsspringbackend.dto;

import com.bheki97.dmsspringbackend.enums.DisasterType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CompleteDisasterDto {

    private Timestamp reportDate;
    private Timestamp completeDate;
    private DisasterType type;
    private String imgContent;
}
