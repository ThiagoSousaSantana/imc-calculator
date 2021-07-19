package com.tabajaracompany.imccalculator.controllers;

import com.tabajaracompany.imccalculator.dtos.LoginRequest;
import com.tabajaracompany.imccalculator.dtos.LoginResponse;
import com.tabajaracompany.imccalculator.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        var login = loginRequest.authenticationToken();
        try{
            var authentication =  authenticationManager.authenticate(login);
            var token = jwtUtil.generateToken(authentication);
            return ResponseEntity.ok(new LoginResponse(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
