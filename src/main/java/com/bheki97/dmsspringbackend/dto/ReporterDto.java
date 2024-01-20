package com.bheki97.dmsspringbackend.dto;

import lombok.Data;

@Data
public class ReporterDto {

    private long reporterId;
    private String firstname;
    private String lastname;
    private String cellNo;
    private String email;

}
