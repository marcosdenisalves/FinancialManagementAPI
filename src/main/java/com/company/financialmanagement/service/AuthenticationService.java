package com.company.financialmanagement.service;

import com.company.financialmanagement.dto.AuthenticationDTO;
import com.company.financialmanagement.exception.AlreadyRegisteredUseException;
import com.company.financialmanagement.exception.NotFoundException;
import com.company.financialmanagement.model.Token;
import com.company.financialmanagement.dto.AuthenticationRegisterDTO;
import com.company.financialmanagement.enums.Role;
import com.company.financialmanagement.model.User;
import com.company.financialmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtService jwtService;

    public Token register(AuthenticationRegisterDTO request) {
        Optional<User> optional = repository.findByEmail(request.getEmail());
        if (optional.isPresent()) throw new AlreadyRegisteredUseException("The email "+ optional.get().getEmail() + " already is registered");

        var user = User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return Token.builder()
                .token(jwtToken)
                .build();
    }

    public Token authenticate(AuthenticationDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow(() ->
                new NotFoundException(request.getEmail() + " not found"));
        var jwtToken = jwtService.generateToken(user);

        return Token.builder()
                .token(jwtToken)
                .build();    }
}
