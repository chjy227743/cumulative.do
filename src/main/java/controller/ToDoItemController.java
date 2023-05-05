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
    private long id;

    public ToDoItemController() {
        this.items = new HashMap<>();
        this.id = 0;
    }
    /**
     * Adds a new ToDo item for the user.
     *
     * @param userId A user id of the User that the ToDo item belongs to.
     * @param todo The description of the todo item to be added.
     * @param course The course of the todo item to be added.
     * @param due The due date of the todo item to be added.
     * @return A ResponseEntity containing the created ToDo item, with an HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ToDoItem> addTodoItem(@RequestBody long userId, String todo, String course, Date due) {
        Set<ToDoItem> existingItems = items.get(userId);
        ToDoItem todoItem = new ToDoItem(id, todo, course, due);
        id++;
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

    /**
     * Deletes a todo item for the user with userId.
     *
     * @param userId The ID of the user whose ToDo item is to be removed from.
     * @param todoItem The todoItem to remove.
     * @return A ResponseEntity containing the user's removed todoItem, with an HTTP status code.
     */
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

    /**
     * Marks a todo item for the user with userId as complete.
     *
     * @param userId The ID of the user whose ToDo item is marked as complete.
     * @param todoItem The todoItem to mark as complete.
     * @return A ResponseEntity containing the user's completed todoItem, with an HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ToDoItem> completeToDoItem(@RequestBody long userId, ToDoItem todoItem) {
        UserController user = new UserController();
        // if the user is logged in
        if (user.loggedIn(userId , null)) {
            Set<ToDoItem> existingItems = items.get(userId);
            if (existingItems.contains(todoItem))
                todoItem.markComplete();

            return new ResponseEntity<>(todoItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }
}
