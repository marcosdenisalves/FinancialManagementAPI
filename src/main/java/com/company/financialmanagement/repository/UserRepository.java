package com.company.financialmanagement.repository;

import com.company.financialmanagement.model.User;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
