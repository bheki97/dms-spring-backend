package com.bheki97.dmsspringbackend.dto;

import lombok.Data;

@Data
public class TechnicianDto {

    private long userId;
    private long technicianId;
    private String firstname;
    private String lastname;
    private String email;
    private String cellNo;
    private long deptId;
    private long specId;
    private String deptName;
    private String specName;

}
