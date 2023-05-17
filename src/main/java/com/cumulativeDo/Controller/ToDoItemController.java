package com.cumulativeDo.Controller;

import com.cumulativeDo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cumulativeDo.service.ToDoService;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("api/")
public class ToDoItemController {
    // key for Map is user id which is mapped to the set of todo items
    private final ToDoService service;
    private final UserController userController;

    @Autowired
    public ToDoItemController(ToDoService service, UserController userController) {
        this.service = service;
        this.userController = userController;
    }
    /**
     * Adds a new ToDo item for the user.
     * @param todo The description of the todo item to be added.
     * @param courseId The course of the todo item to be added.
     * @param due The due date of the todo item to be added.
     * @return A ResponseEntity containing the created ToDo item, with an HTTP status code.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("addItem")
    public ResponseEntity<?> addTodoItem(@RequestBody String todo, Integer courseId, LocalDate due) {
        User user = userController.loggedInUser();
        if (user == null) return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);

        ToDoItem addItem = new ToDoItem(todo, courseId, due);
        ToDoItem todoItem = service.addTodoItem(user.getUsername(), addItem);
        // Return the ResponseEntity with the added todoItem and HTTP status code 201 (Created)
        return new ResponseEntity<>(todoItem, HttpStatus.CREATED);
    }

    /**
     * Retrieves all ToDo items for a specific user.
     *
     * @param userName The ID of the user whose ToDo items are to be fetched.
     * @return A ResponseEntity containing a list of the user's ToDo items, with an HTTP status code.
     */
    @GetMapping("/{userName}/todos")
    public ResponseEntity<Set<ToDoItem>> getTodoItems(@PathVariable String userName) {
        if (!service.hasUsername(userName)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Set<ToDoItem> todoItems = service.getTodoItems(userName);
        System.out.println(todoItems);
        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    /**
     * Deletes a todo item for the user with userId.
     *
     * @param userName The ID of the user whose ToDo item is to be removed from.
     * @param todoItem The todoItem to remove.
     * @return A ResponseEntity containing the user's removed todoItem, with an HTTP status code.
     */
//    @PostMapping
    public ResponseEntity<ToDoItem> deleteTodoItem(@RequestBody String userName, ToDoItem todoItem) {
        // if the user is logged in
        if (userController.loggedIn(userName)) {
            ToDoItem item = service.deleteTodoItem(userName, todoItem);
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }

    /**
     * Marks a todo item for the user with userId as complete.
     *
     * @param userName The ID of the user whose ToDo item is marked as complete.
     * @param todoItem The todoItem to mark as complete.
     * @return A ResponseEntity containing the user's completed todoItem, with an HTTP status code.
     */
    @PostMapping("deleteItem")
    public ResponseEntity<ToDoItem> completeToDoItem(@RequestBody String userName, ToDoItem todoItem) {
        // if the user is logged in
        if (userController.loggedIn(userName)) {
            ToDoItem item = service.completeTodoItem(userName, todoItem);

            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }
}
