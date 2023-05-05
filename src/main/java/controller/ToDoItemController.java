package controller;

import model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/todoItem")
public class ToDoItemController {
    // key for Map is user id which is mapped to the set of todo items
    private Map<Long, Set<ToDoItem>> items;

    public ToDoItemController() {
        this.items = new HashMap<>();
    }
    /**
     * Adds a new ToDo item for the user.
     *
     * @param userId A user id of the User that the ToDo item belongs to.
     * @param todoItem The ToDo item to be added.
     * @return A ResponseEntity containing the created ToDo item, with an HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ToDoItem> addTodoItem(@RequestBody long userId, ToDoItem todoItem) {
        Set<ToDoItem> existingItems = items.get(userId);
        existingItems.add(todoItem);
        items.put(userId, existingItems);

        // Return the ResponseEntity with the added todoItem and HTTP status code 201 (Created)
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }

    /**
     * Retrieves all ToDo items for a specific user.
     *
     * @param userId The ID of the user whose ToDo items are to be fetched.
     * @return A ResponseEntity containing a list of the user's ToDo items, with an HTTP status code.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<ToDoItem>> getTodoItems(@PathVariable long userId) {

        if (!items.containsKey(userId)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Set<ToDoItem> todoItems = items.get(userId);

        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDoItem> deleteTodoItem(@RequestBody long userId, ToDoItem todoItem) {
        UserController user = new UserController();
        // if the user is logged in
        if (user.loggedIn(userId , null)) {
            Set<ToDoItem> existingItems = items.get(userId);
            existingItems.remove(todoItem);
            return new ResponseEntity<>(todoItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }

    // mark complete


}
