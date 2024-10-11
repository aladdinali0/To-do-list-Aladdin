package com.luv2code.springboot.todolist.mycoolapp.service;

import com.luv2code.springboot.todolist.mycoolapp.dao.TodolistDao;
import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodolistServiceimpl implements  TodolistService{
    private TodolistDao todolistDao;
    @Autowired
    public TodolistServiceimpl(TodolistDao thetodolistDao) {
        todolistDao = thetodolistDao;
    }

    @Override
    public List<Todolistproject> findAll() {
        return todolistDao.findAll();
    }

    @Override
    public Todolistproject save(Todolistproject theTodolistproject) {
        return todolistDao.save(theTodolistproject);
    }

    @Override
    public Todolistproject deleteAll() {
         todolistDao.deleteAll();
         return null;
    }

    @Override
    public Todolistproject findById(int taskId) {
        return todolistDao.findById(taskId);
    }

    @Override
    public void deleteById(int taskId) {
         todolistDao.deleteById(taskId);

    }

    @Override
    public void markAsComplete(int taskId) {
        Todolistproject task = todolistDao.findById(taskId); // Fetch the task by ID
        if (task != null) {
            task.setCompleted(true); // Assuming there's a `complete` field in the entity
            todolistDao.save(task); // Save the updated task
        } else {
            throw new RuntimeException("Task not found with id - " + taskId); // Handle task not found
        }

    }

}
