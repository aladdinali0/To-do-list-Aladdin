package com.luv2code.springboot.todolist.mycoolapp.rest;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin(origins = "*")
    @GetMapping("/todolists")
    public List<Todolistproject> findAll() {
        return todolistDao.findAll();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/todolists/{taskId}")
    public Todolistproject findById(@PathVariable int taskId){
        Todolistproject theTodolistproject = todolistDao.findById(taskId);
        if (theTodolistproject == null){
            throw new RuntimeException("Task id is not found: Error " + taskId);
        }
        return theTodolistproject;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/todolists") // change the endpoint later to avoid confusion
    public Todolistproject addTask(@RequestBody Todolistproject theTodolistproject) {
        theTodolistproject.setID(0);
        return todolistDao.save(theTodolistproject);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/todolists") // change the endpoint later to avoid confusion
    public Todolistproject updateTask(@RequestBody Todolistproject theTodolistproject) {
        return todolistDao.save(theTodolistproject);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/todolists")
    public Todolistproject deleteAll() {
       return todolistDao.deleteAll();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/todolists/{taskId}")
    public String deleteById(@PathVariable int taskId){
        Todolistproject theTodolistproject = todolistDao.findById(taskId);

        if (theTodolistproject == null){
            throw new RuntimeException("Task id is not found: Error " + taskId);
        }
        todolistDao.deleteById(taskId);
        return " Deleted task id - " + taskId;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/todolists/{taskId}/markComplete")
    public void markTaskAsComplete(@PathVariable int taskId) {
        todolistDao.markAsComplete(taskId);
    }




    @CrossOrigin(origins = "*")
    @PostMapping("/addTask")
    public Todolistproject addTask(@RequestParam String taskname, @RequestParam String description) {
        Todolistproject newTask = new Todolistproject();
        newTask.setTaskname(taskname);
        newTask.setDescription(description);
        // You might want to set other properties as needed before saving
        newTask.setID(0);

        return todolistDao.save(newTask);
    }

}






