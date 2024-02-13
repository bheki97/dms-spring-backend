package com.bheki97.dmsspringbackend.dto;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import lombok.Data;

@Data
public class AuthResponse {

    private long userId;
    private String email;
    private String firstname;
    private String lastname;
    private String cellNo;
    private String userRole;
    private String jwtToken;

}
