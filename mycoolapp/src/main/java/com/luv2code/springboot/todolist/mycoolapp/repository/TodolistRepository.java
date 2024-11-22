package com.luv2code.springboot.todolist.mycoolapp.repository;

import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import com.luv2code.springboot.todolist.mycoolapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodolistRepository extends JpaRepository<Todolistproject, Long> {
    List<Todolistproject> findByUser(User user);
}