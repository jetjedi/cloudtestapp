package com.example.cloudtestapp.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.cloudtestapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
}
