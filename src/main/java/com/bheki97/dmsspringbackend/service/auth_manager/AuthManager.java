package com.bheki97.dmsspringbackend.service.auth_manager;

import com.bheki97.dmsspringbackend.dto.AuthRequest;
import com.bheki97.dmsspringbackend.dto.AuthResponse;

public interface AuthManager {

    AuthResponse authenticateUser(AuthRequest request);
}
