package com.bheki97.dmsspringbackend.dto;

import lombok.Data;

@Data
public class UserEntityDto {

    private long userId;
    private String firstname;
    private String lastname;
    private String cellNo;
    private String email;

}
