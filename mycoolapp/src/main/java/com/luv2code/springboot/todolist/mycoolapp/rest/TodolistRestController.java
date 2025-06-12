package com.luv2code.springboot.todolist.mycoolapp.rest;
import com.luv2code.springboot.todolist.mycoolapp.models.DeletionResponse;
import com.luv2code.springboot.todolist.mycoolapp.models.User;
import com.luv2code.springboot.todolist.mycoolapp.repository.UserRepository;
import com.luv2code.springboot.todolist.mycoolapp.services.TodolistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    //  expose "//todolists/{taskId}" and return a to-do list task by id
    @CrossOrigin(origins = "*")
    @GetMapping("/todolists/{taskId}")
    public ResponseEntity<?> findById(@PathVariable int taskId, Authentication authentication){

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in.");
        }
        // Get the logged-in user's username
        String username = authentication.getName();

        //find the task
        Todolistproject task = todolistService.findById(taskId);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }

        // Check if the logged-in user is the owner of the task
        if (!task.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to view this task.");
        }

        return ResponseEntity.ok(task);


    }

    // expose "/todolists" and post a todolist task by user
    @CrossOrigin(origins = "*")
    @PostMapping("/todolists/addTask")
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
    }

//    @CrossOrigin(origins = "*")
//    @PutMapping("/todolists") // change the endpoint later to avoid confusion
//    public Todolistproject updateTask(@RequestBody Todolistproject theTodolistproject) {
//        return todolistService.save(theTodolistproject);
//    }

    // expose "/todolists" and deletes all todolist tasks by user * only admin can delete it
    @CrossOrigin(origins = "*")
    @DeleteMapping("/todolists/{username}")
    public ResponseEntity<String> deleteAllForUser(@PathVariable String username, Authentication authentication) {
        String loggedInUser = authentication.getName();

        if (!loggedInUser.equals(username) && !authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot delete another user's tasks.");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        DeletionResponse response = todolistService.deleteAllForUser(user);

        // Return the response from the service, either success or failure message
        return ResponseEntity.ok(response.getMessage());


    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/todolists")
    public ResponseEntity<String> deleteAll(Authentication authentication) {
        todolistService.deleteAll();
        return ResponseEntity.ok("All tasks deleted.");
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/todolists/{taskId}")
    public ResponseEntity<String> deleteById(@PathVariable int taskId, Authentication authentication){
        String username = authentication.getName();

        // Find the task by ID
        Todolistproject task = todolistService.findById(taskId);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }

        // Check if the logged-in user is the owner of the task
        if (!task.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this task.");
        }

        // If the task belongs to the logged-in user, delete the task
        String resultMessage = todolistService.deleteById(taskId);

        return ResponseEntity.ok(resultMessage); // Return the success message
    }

//    @CrossOrigin(origins = "*")
//    @PutMapping("/todolists/{taskId}/markComplete")
//    public void markTaskAsComplete(@PathVariable int taskId) {
//        todolistService.markAsComplete(taskId);
//    }

    @CrossOrigin(origins = "*")
    @PutMapping("/todolists/{taskId}/markComplete")
    public ResponseEntity<?> markTaskAsComplete(@PathVariable int taskId, Authentication authentication) {
        String username = authentication.getName();

        // Find the task
        Todolistproject task = todolistService.findById(taskId);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }

        // Check ownership
        if (!task.getUser().getUsername().equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to modify this task.");
        }

        // Mark as complete
        todolistService.markAsComplete(taskId);
        return ResponseEntity.ok("Task marked as complete.");
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






