package com.bheki97.dmsspringbackend.controller;

import com.bheki97.dmsspringbackend.dto.AuthRequest;
import com.bheki97.dmsspringbackend.dto.AuthResponse;
import com.bheki97.dmsspringbackend.service.auth_manager.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthManager authManager;

    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest  request){
        return authManager.authenticateUser(request);
    }

}
