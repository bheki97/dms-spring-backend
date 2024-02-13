package com.bheki97.dmsspringbackend.service.auth_manager.impl;

import com.bheki97.dmsspringbackend.dto.AuthRequest;
import com.bheki97.dmsspringbackend.dto.AuthResponse;
import com.bheki97.dmsspringbackend.config.security.jwt.JwtService;
import com.bheki97.dmsspringbackend.entity.UserEntity;
import com.bheki97.dmsspringbackend.repository.UserEntityRepository;
import com.bheki97.dmsspringbackend.service.auth_manager.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthManagerImpl implements AuthManager {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public AuthResponse authenticateUser(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),request.getPassword()));

        AuthResponse response =  new AuthResponse();
        UserEntity entity = userEntityRepository.findByEmail(request.getUsername()).get();


        setEntityFields(entity,response);
        response.setJwtToken(jwtService.generateToken(request.getUsername()));


        return response;
    }

    private void setEntityFields(UserEntity entity, AuthResponse response) {
        response.setUserId(entity.getUserId());
        response.setFirstname(entity.getFirstname());
        response.setLastname(entity.getLastname());
        response.setCellNo(entity.getCellNo());
        response.setEmail(entity.getEmail());
        response.setUserRole(entity.getUserRole());


    }
}
