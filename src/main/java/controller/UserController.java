package controller;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import model.ToDoItem;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")

@Data
public class UserController {
    // @Autowired
    // private UserRepository userRepository;
    private Set<User> users;
    private Set<Long> loggedUsers;

    // Validate and register user, return appropriate response

    /**
     * Registers a new user with the provided user details.
     *
     * @param user A User object containing the user's details (username, password, email, etc.).
     * @return A ResponseEntity with the HTTP status indicating the success or failure of the user registration.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Check if the user already exists
        boolean userExists = users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));

        if (userExists) {
            return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
        }

        // Register the new user
        users.add(user);

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
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user, HttpSession session) {
        boolean userExists = users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));

        // Check if the user exists and the password is correct
        // Replace this with your actual authentication logic
        if (userExists && isPasswordCorrect(user)) {
            // Store the logged-in user in the session
            session.setAttribute("loggedInUser", user);
            loggedUsers.add(user.getId());
            // Return the ResponseEntity with the user and HTTP status code 200 (OK)
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // Return the ResponseEntity with an error message and HTTP status code 401 (Unauthorized)
            return new ResponseEntity<>("Invalid username or password. Please register if you don't have an account.", HttpStatus.UNAUTHORIZED);
        }
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
    @PutMapping("/{userId}")
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

    public boolean loggedIn(long userId, HttpSession session) {
        return loggedUsers.contains(userId);
    }

    private boolean isPasswordCorrect(User user) {
        // TODO: implement password check
        return false;
    }

    private boolean updateUserInfo(User loggedInUser) {
        // TODO: this function does not need to be implemented, but frontend logic needs to be connected
        return false;
    }

}