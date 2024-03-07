package com.attendy.attendy.service.interfaces;

import com.attendy.attendy.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Object> verifyToken(HttpServletRequest request);
    ResponseEntity<Object> login(LoginRequest loginRequest);
}
