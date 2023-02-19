package com.company.financialmanagement.service;

import com.company.financialmanagement.model.User;
import com.company.financialmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserService {

    @Autowired
    private UserRepository repository;
    public void save(User user) {
        repository.save(user);
    }
}
