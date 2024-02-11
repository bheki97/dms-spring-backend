package com.bheki97.dmsspringbackend.dto;

import com.bheki97.dmsspringbackend.entity.UserEntity;
import lombok.Data;

@Data
public class AuthResponse {

    private UserEntity userHolder;
    private String jwtToken;

}
