package com.cumulativeDo.Controller;

import com.cumulativeDo.model.*;
import com.cumulativeDo.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/")

public class UserController {
    // @Autowired
    // private UserRepository userRepository;
    private Set<User> users;
    private User loggedUser;
    private final ToDoService toDoService;

    public UserController(ToDoService toDoService) {
        users = new HashSet<>();
        loggedUser = null;
        this.toDoService = toDoService;
    }

    // Validate and register user, return appropriate response

    /**
     * Registers a new user with the provided user details.
     *
     * @param user A User object containing the user's details (username, password, email, etc.).
     * @return A ResponseEntity with the HTTP status indicating the success or failure of the user registration.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if the user already exists
        boolean userExists = users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));

        if (userExists) {
            return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
        }

        // Register the new user
        users.add(user);
        toDoService.addUser(user.getUsername());

        loggedUser = user;

        // Return the ResponseEntity with the registered user and HTTP status code 201 (Created)
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Logs in a user.
     *
     * @param user     The user object containing the username and password.
     * @param session  The HttpSession object to store the logged-in user information.
     * @return ResponseEntity containing either the user's to-do list with
     *         HTTP status code 200 (OK) or an error message with HTTP status
     *         code 401 (Unauthorized) if login failed.
     */
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpSession session) {
        boolean userExists = users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));

        // Check if the user exists and the password is correct
        // Replace this with your actual authentication logic
        if (userExists && isPasswordCorrect(user)) {
            // Store the logged-in user in the session
            session.setAttribute("loggedInUser", user);
            loggedUser = user;
            // Return the ResponseEntity with the user and HTTP status code 200 (OK)
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // Return the ResponseEntity with an error message and HTTP status code 401 (Unauthorized)
            return new ResponseEntity<>("Invalid username or password. Please register if you don't have an account.", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("getLoggedInUser")
    public ResponseEntity<?> getLoggedInUser() {
        if (loggedUser == null) {
            return new ResponseEntity<>("No user is logged in", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    public User loggedInUser() {
        return loggedUser;
    }

    // Other UserController methods

    /**
     * Updates user information, if the user exists.
     *
     * @param user     The user object containing the username and password.
     * @param session  The HttpSession object to store the logged-in user information.
     * @return ResponseEntity containing either the user's to-do list with
     *         HTTP status code 200 (OK) or an error message with HTTP status
     *         code 401 (Unauthorized) if login failed.
     */
    @PutMapping("{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user, HttpSession session) {
        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Return the ResponseEntity with an error message and HTTP status code 401 (Unauthorized)
            return new ResponseEntity<>("You must be logged in to update your user information.", HttpStatus.UNAUTHORIZED);
        }

        // Update the user information
        // Replace this with your actual logic for updating the user
        if (updateUserInfo(loggedInUser)) {
            // Return the ResponseEntity with the updated user and HTTP status code 200 (OK)
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } else {
            // Return the ResponseEntity with an error message and HTTP status code 400 (Bad Request)
            return new ResponseEntity<>("Failed to update user information.", HttpStatus.BAD_REQUEST);
        }
    }

    public boolean loggedIn(String username) {
        return loggedUser.getUsername().equals(username);
    }



    private boolean isPasswordCorrect(User user) {
        // TODO: implement password check
        return false;
    }

    private boolean updateUserInfo(User loggedInUser) {
        // TODO: this function does not need to be implemented, but frontend logic needs to be connected
        return false;
    }


    // Getters
    @GetMapping("users")
    public Set<User> getUsers() {
        return this.users;
    }

}