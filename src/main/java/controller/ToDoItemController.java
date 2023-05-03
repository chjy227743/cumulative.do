package controller;

import model.ToDoItem;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/todoItem")
public class ToDoItemController {
    // key is user id which is mapped to the set of todo items
    private Map<Long, Set<ToDoItem>> items;

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
    public ResponseEntity<?> getTodoItems(@PathVariable long userId) {

        if (!items.containsKey(userId)) {
            return new ResponseEntity<>("Failed to find user.", HttpStatus.NOT_FOUND);
        }

        Set<ToDoItem> todoItems = items.get(userId);

        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }
}
