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
//@RequestMapping("/api/todoItem")
public class ToDoItemController {
    // key for Map is user id which is mapped to the set of todo items
    private final ToDoService service;

    @Autowired
    public ToDoItemController(ToDoService service) {
        this.service = service;
    }
    /**
     * Adds a new ToDo item for the user.
     *
     * @param userName A user id of the User that the ToDo item belongs to.
     * @param todo The description of the todo item to be added.
     * @param courseId The course of the todo item to be added.
     * @param due The due date of the todo item to be added.
     * @return A ResponseEntity containing the created ToDo item, with an HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ToDoItem> addTodoItem(@RequestBody String userName, String todo, int courseId, LocalDate due) {
        ToDoItem addItem = new ToDoItem(todo, courseId, due);
        ToDoItem todoItem = service.addTodoItem(userName, addItem);

        // Return the ResponseEntity with the added todoItem and HTTP status code 201 (Created)
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }

    /**
     * Retrieves all ToDo items for a specific user.
     *
     * @param userName The ID of the user whose ToDo items are to be fetched.
     * @return A ResponseEntity containing a list of the user's ToDo items, with an HTTP status code.
     */
//    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<ToDoItem>> getTodoItems(@PathVariable String userName) {
        if (!service.hasUsername(userName)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Set<ToDoItem> todoItems = service.getTodoItems(userName);

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
        UserController user = new UserController();
        // if the user is logged in
        if (user.loggedIn(userName , null)) {
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
//    @PostMapping
    public ResponseEntity<ToDoItem> completeToDoItem(@RequestBody String userName, ToDoItem todoItem) {
        UserController user = new UserController();
        // if the user is logged in
        if (user.loggedIn(userName , null)) {
            ToDoItem item = service.completeTodoItem(userName, todoItem);

            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }
}
