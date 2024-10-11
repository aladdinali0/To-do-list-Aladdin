package com.luv2code.springboot.todolist.mycoolapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.todolist.mycoolapp.models.ERole;
import com.luv2code.springboot.todolist.mycoolapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {
    Optional<Role> findByName(ERole name);
}
