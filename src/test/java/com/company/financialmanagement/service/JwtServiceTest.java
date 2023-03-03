package com.company.financialmanagement.service;

import com.company.financialmanagement.dto.AuthenticationRegisterDTO;
import com.company.financialmanagement.model.User;
import com.company.financialmanagement.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
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
