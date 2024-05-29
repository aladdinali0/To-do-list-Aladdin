package com.luv2code.springboot.todolist.mycoolapp.dao;
import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;

import java.util.List;

public interface TodolistDao {

    List<Todolistproject> findAll();

    Todolistproject save(Todolistproject theTodolistproject);

    Todolistproject deleteAll();

    Todolistproject findById(int taskId);


    void deleteById(int taskId);

    void markAsComplete(int taskId);





}
