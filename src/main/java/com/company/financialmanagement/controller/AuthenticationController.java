package com.company.financialmanagement.controller;

import com.company.financialmanagement.dto.AuthenticationDTO;
import com.company.financialmanagement.model.Token;
import com.company.financialmanagement.service.AuthenticationService;
import com.company.financialmanagement.dto.AuthenticationRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<Token> register(@RequestBody AuthenticationRegisterDTO request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Token> register(@RequestBody AuthenticationDTO request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
