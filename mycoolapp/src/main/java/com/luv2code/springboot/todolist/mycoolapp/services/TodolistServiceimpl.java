package com.luv2code.springboot.todolist.mycoolapp.services;

import com.luv2code.springboot.todolist.mycoolapp.dao.TodolistDao;
import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import com.luv2code.springboot.todolist.mycoolapp.models.DeletionResponse;
import com.luv2code.springboot.todolist.mycoolapp.models.User;
import com.luv2code.springboot.todolist.mycoolapp.repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodolistServiceimpl implements  TodolistService {
    private TodolistDao todolistDao;

    private final TodolistRepository todolistRepository;

    @Autowired
    public TodolistServiceimpl(TodolistDao thetodolistDao, TodolistRepository todolistRepository) {
        todolistDao = thetodolistDao;
        this.todolistRepository = todolistRepository;
    }

    @Override
    public List<Todolistproject> findTasksByUser(User user) {
        return todolistRepository.findByUser(user);
    }

    @Override
    public Todolistproject save(Todolistproject theTodolistproject) {
        return todolistRepository.save(theTodolistproject);
    }

    @Override
    public DeletionResponse deleteAllForUser(User user) {
        List<Todolistproject> tasks = todolistRepository.findByUser(user);
        if (tasks.isEmpty()) {
            return new DeletionResponse("No tasks found for user to delete.", false);
        }

        todolistRepository.deleteAll(tasks);
        return new DeletionResponse("All tasks for the user have been deleted.", true);
    }



    @Override
    public Todolistproject deleteAll() {
         todolistRepository.deleteAll();
         return null; // could possibly change this
    }

    // New method to return a custom response object with status message
    public DeletionResponse deleteAllAndReturnStatus() {
        todolistRepository.deleteAll();
        return new DeletionResponse("All tasks have been deleted!", true);
    }

    @Override
    public Todolistproject findById(int taskId) {
        // Find task by ID (currently does not check for user-specific tasks)
        return todolistRepository.findById(Long.valueOf(taskId))
                .orElseThrow(() -> new RuntimeException("Task id not found: " + taskId));
    }

    @Override
    public String deleteById(int taskId) {
        Todolistproject task = todolistRepository.findById(Long.valueOf(taskId))
                .orElseThrow(() -> new RuntimeException("Task id not found: " + taskId));

        todolistRepository.delete(task);
        return "Task deleted successfully with id - " + taskId;
    }

    @Override
    public void markAsComplete(int taskId) {
        // fetch the task by ID, convert taskId to long if necessary
        Todolistproject task = todolistRepository.findById(Long.valueOf(taskId))
                .orElseThrow(() -> new RuntimeException("Task not found with id - " + taskId));
        // Mark the task as complete
        task.setCompleted(true); // Assuming there's a `completed` field in the entity

        // Save the updated task
        todolistRepository.save(task); // Save the task back to the repository

    }


}