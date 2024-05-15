package com.luv2code.springboot.todolist.mycoolapp.rest;

import com.luv2code.springboot.todolist.mycoolapp.dao.TodolistDao;
import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class TodolistRestController {

    @Autowired
    // inject todolistdao (constructor injection
    private TodolistDao todolistDao;

    @Autowired
    public TodolistRestController(TodolistDao thetodoListDao) {
        todolistDao = thetodoListDao;
    }

    // expose "/todolists" and return a full to-do list
    @GetMapping("/todolists")
    public List<Todolistproject> findAll() {
        return todolistDao.findAll();
    }

    @PostMapping("/todolists") // change the endpoint later to avoid confusion
    public Todolistproject addTask(@RequestBody Todolistproject theTodolistproject) {
        theTodolistproject.setID(0);
        return todolistDao.save(theTodolistproject);
    }

    @PutMapping("/todolists") // change the endpoint later to avoid confusion
    public Todolistproject updateTask(@RequestBody Todolistproject theTodolistproject) {
        return todolistDao.save(theTodolistproject);
    }

    @DeleteMapping("/todolists") // change the endpoint later to avoid confusion
    public Todolistproject deleteAll() {
       return todolistDao.deleteAll();
    }

    @PostMapping("/addTask")
    public Todolistproject addTask(@RequestParam String taskname, @RequestParam String description) {
        Todolistproject newTask = new Todolistproject();
        newTask.setTaskname(taskname);
        newTask.setDescription(description);
        // You might want to set other properties as needed before saving

        return todolistDao.save(newTask);
    }


}












