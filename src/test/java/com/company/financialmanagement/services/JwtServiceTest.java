package com.company.financialmanagement.services;

import com.company.financialmanagement.dtos.AuthenticationDTO;
import com.company.financialmanagement.dtos.AuthenticationRegisterDTO;
import com.company.financialmanagement.models.Token;
import com.company.financialmanagement.models.User;
import com.company.financialmanagement.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtServiceTest {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

   private AuthenticationRegisterDTO registerDTO;
   private AuthenticationDTO authenticationDTO;
    @BeforeEach
    public void initTest() {
         registerDTO = AuthenticationRegisterDTO.builder()
                .email("administrator@email.com")
                .lastname("administrator")
                .firstname("adm")
                .password("1234")
                .build();

        authenticationDTO = AuthenticationDTO.builder()
                .email("administrator@email.com")
                .password("1234")
                .build();
    }

    @Test
    public void shouldRegisterNewUser() {
        Token token = authenticationService.register(registerDTO);
        User user = assertDoesNotThrow(() -> userRepository.findByEmail("administrator@email.com").orElseThrow());

        assertNotNull(user);
        assertNotNull(token);
        assertEquals(registerDTO.getEmail(), user.getEmail());
    }

    @Test
    public void shouldAuthenticateUser() {
        authenticationService.register(registerDTO);
        Token token = authenticationService.authenticate(authenticationDTO);

        assertNotNull(token);
    }
}
