package com.company.financialmanagement.service;

import com.company.financialmanagement.dto.AuthenticationRegisterDTO;
import com.company.financialmanagement.model.User;
import com.company.financialmanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtServiceTest {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;

    private AuthenticationRegisterDTO registerDTO;
    @BeforeEach
    public void initTest() {
         registerDTO = AuthenticationRegisterDTO.builder()
                .email("administrator@email.com")
                .lastname("administrator")
                .firstname("adm")
                .password("1234")
                .build();
    }

    @Test
    public void testIfUserHasBeenRegistered() {
        authenticationService.register(registerDTO);

        User user = assertDoesNotThrow(() -> userRepository.findByEmail("administrator@email.com").orElseThrow());
        assertEquals(registerDTO.getEmail(), user.getEmail());
    }
}
