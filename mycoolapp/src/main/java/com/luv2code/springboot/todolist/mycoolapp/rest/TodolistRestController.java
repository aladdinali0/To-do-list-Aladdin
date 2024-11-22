package com.luv2code.springboot.todolist.mycoolapp.rest;
import com.luv2code.springboot.todolist.mycoolapp.models.User;
import com.luv2code.springboot.todolist.mycoolapp.repository.UserRepository;
import com.luv2code.springboot.todolist.mycoolapp.services.TodolistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.luv2code.springboot.todolist.mycoolapp.entity.Todolistproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("api/")
public class TodolistRestController {

    @Autowired
    // inject todolistService (constructor injection
    private final TodolistService todolistService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public TodolistRestController(TodolistService todolistService, UserRepository userRepository) {
        this.todolistService = todolistService;
        this.userRepository = userRepository;

    }

    // expose "/todolists" and return a full to-do list
    @CrossOrigin(origins = "*")
    @GetMapping("/todolists")
    public List<Todolistproject> findTasksByUser(Authentication authentication) {
        // Get the logged-in user's username
        String username = authentication.getName();

        // Fetch the user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        return todolistService.findTasksByUser(user);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/todolists/{taskId}")
    public Todolistproject findById(@PathVariable int taskId){
        Todolistproject theTodolistproject = todolistService.findById(taskId);
        if (theTodolistproject == null){
            throw new RuntimeException("Task id is not found: Error " + taskId);
        }
        return theTodolistproject;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/todolists") // change the endpoint later to avoid confusion
    public ResponseEntity<?> addTask(@RequestBody Todolistproject theTodolistproject, Authentication authentication) {
        // Retrieve the logged-in user's username from the authentication object
        String username = authentication.getName();

        // Find the user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        // Set the user for the task
        theTodolistproject.setUser(user);

        // Save the task
        Todolistproject savedTask = todolistService.save(theTodolistproject);

        return ResponseEntity.ok(savedTask);
//        theTodolistproject.setID(0);
//        return todolistService.save(theTodolistproject);
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






