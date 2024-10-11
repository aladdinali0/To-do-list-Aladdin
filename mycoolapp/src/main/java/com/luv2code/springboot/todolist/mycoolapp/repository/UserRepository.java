package com.luv2code.springboot.todolist.mycoolapp.repository;

import java.util.Optional;

import com.luv2code.springboot.todolist.mycoolapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
