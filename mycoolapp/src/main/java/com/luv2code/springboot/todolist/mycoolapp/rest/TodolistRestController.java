package com.luv2code.springboot.todolist.mycoolapp.rest;
import com.luv2code.springboot.todolist.mycoolapp.service.TodolistService;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("api/")
public class TodolistRestController {

    @Autowired
    // inject todolistdao (constructor injection
    private final TodolistService todolistService;

    @Autowired
    public TodolistRestController(TodolistService theToodolistService) {
        todolistService = theToodolistService;
    }

    // expose "/todolists" and return a full to-do list
    @CrossOrigin(origins = "*")
    @GetMapping("/todolists")
    public List<Todolistproject> findAll() {
        return todolistService.findAll();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/todolists/{taskId}")
    public Todolistproject findById(@PathVariable("taskId")int taskId){ 
        Todolistproject theTodolistproject = todolistService.findById(taskId);
        if (theTodolistproject == null){
            throw new RuntimeException("Task id is not found: Error " + taskId);
        }
        return theTodolistproject;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/todolists") // change the endpoint later to avoid confusion
    public Todolistproject addTask(@RequestBody Todolistproject theTodolistproject) {
        theTodolistproject.setID(0);
        return todolistService.save(theTodolistproject);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/todolists") // change the endpoint later to avoid confusion
    public Todolistproject updateTask(@RequestBody Todolistproject theTodolistproject) {
        return todolistService.save(theTodolistproject);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/todolists")
    public Todolistproject deleteAll() {
       return todolistService.deleteAll();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/todolists/{taskId}")
    public String deleteById(@PathVariable int taskId){
        Todolistproject theTodolistproject = todolistService.findById(taskId);

        if (theTodolistproject == null){
            throw new RuntimeException("Task id is not found: Error " + taskId);
        }
        todolistService.deleteById(taskId);
        return " Deleted task id - " + taskId;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/todolists/{taskId}/markComplete")
    public void markTaskAsComplete(@PathVariable int taskId) {
        Todolistproject theTodolistproject = todolistService.findById(taskId);
        if (theTodolistproject == null) {
            throw new RuntimeException("Task id not found: " + taskId);
        }
        todolistService.markAsComplete(taskId);
    }




    @CrossOrigin(origins = "*")
    @PostMapping("/addTask")
    public Todolistproject addTask(@RequestParam String taskname, @RequestParam String description) {
        Todolistproject newTask = new Todolistproject();
        newTask.setTaskname(taskname);
        newTask.setDescription(description);
        // You might want to set other properties as needed before saving
        newTask.setID(0);

        return todolistService.save(newTask);
    }

}






