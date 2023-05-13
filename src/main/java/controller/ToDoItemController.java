package controller;

import model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/todoItem")
public class ToDoItemController {
    // key for Map is user id which is mapped to the set of todo items
    private Map<String, Set<ToDoItem>> items;
    private long id;

    public ToDoItemController() {
        this.items = new HashMap<>();
        this.id = 0;
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
        Set<ToDoItem> existingItems = items.get(userName);
        ToDoItem todoItem = new ToDoItem(todo, courseId, due);
        id++;
        existingItems.add(todoItem);
        items.put(userName, existingItems);

        // Return the ResponseEntity with the added todoItem and HTTP status code 201 (Created)
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }

    /**
     * Retrieves all ToDo items for a specific user.
     *
     * @param userName The ID of the user whose ToDo items are to be fetched.
     * @return A ResponseEntity containing a list of the user's ToDo items, with an HTTP status code.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<ToDoItem>> getTodoItems(@PathVariable String userName) {

        if (!items.containsKey(userName)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Set<ToDoItem> todoItems = items.get(userName);

        return new ResponseEntity<>(todoItems, HttpStatus.OK);
    }

    /**
     * Deletes a todo item for the user with userId.
     *
     * @param userName The ID of the user whose ToDo item is to be removed from.
     * @param todoItem The todoItem to remove.
     * @return A ResponseEntity containing the user's removed todoItem, with an HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ToDoItem> deleteTodoItem(@RequestBody String userName, ToDoItem todoItem) {
        UserController user = new UserController();
        // if the user is logged in
        if (user.loggedIn(userName , null)) {
            Set<ToDoItem> existingItems = items.get(userName);
            existingItems.remove(todoItem);
            return new ResponseEntity<>(todoItem, HttpStatus.OK);
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
    @PostMapping
    public ResponseEntity<ToDoItem> completeToDoItem(@RequestBody String userName, ToDoItem todoItem) {
        UserController user = new UserController();
        // if the user is logged in
        if (user.loggedIn(userName , null)) {
            Set<ToDoItem> existingItems = items.get(userName);
            if (existingItems.contains(todoItem))
                todoItem.markComplete();

            return new ResponseEntity<>(todoItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.BAD_REQUEST);
    }

    public long getId() {
        id++;
        return id;
    }
}
