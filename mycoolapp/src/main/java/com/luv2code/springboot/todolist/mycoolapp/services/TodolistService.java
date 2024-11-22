package com.luv2code.springboot.todolist.mycoolapp.services;

import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import com.luv2code.springboot.todolist.mycoolapp.models.DeletionResponse;
import com.luv2code.springboot.todolist.mycoolapp.models.User;

import java.util.List;

public interface TodolistService {
    List<Todolistproject> findTasksByUser(User user); // remove this later ?

    Todolistproject save(Todolistproject theTodolistproject);

    DeletionResponse deleteAllForUser(User user);

    Todolistproject deleteAll();

    Todolistproject findById(int taskId);

    String deleteById(int taskId);

    void markAsComplete(int taskId);


}