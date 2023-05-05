package controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Set;

import model.*;
import controller.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ToDoItemControllerTest {
    private ToDoItemController toDoItemController;
    private ToDoItem testTodoItem;
    private long testUserId;
    private String testTodo;
    private String course;
    private Date due;

    @BeforeEach
    public void setUp() {
        toDoItemController = new ToDoItemController();
        testUserId = 1L;
        testTodo = "Test Task";
        course = "Test course";
        due = new Date();
        testTodoItem = new ToDoItem(1L, testTodo, course, due);
    }

    @Test
    public void addItemsExistingUserTest() {
        // Add an item to the user
        ResponseEntity<ToDoItem> response = toDoItemController.addTodoItem(testUserId, testTodo, course, due);
        assertEquals(testTodoItem, response.getBody());
        Set<ToDoItem> todoItems = toDoItemController.getTodoItems(testUserId).getBody();

        // test if the todoItem is in user and the status is correct
        assert todoItems != null;
        assertTrue(todoItems.contains(testTodoItem));
        assertNotEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(testTodoItem, response.getBody());
    }

    @Test
    public void getItemsExistingUserTest() {
        // Add a todo item for the test user
        ResponseEntity<ToDoItem> response = toDoItemController.addTodoItem(testUserId, testTodo, course, due);
        assertEquals(testTodoItem, response.getBody());

        ResponseEntity<Set<ToDoItem>> getResponse = toDoItemController.getTodoItems(testUserId);
        Set<ToDoItem> todoItems = getResponse.getBody();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todoItems, response.getBody());
    }


    @Test
    public void getItemNotExistingUserTest() {
        ResponseEntity<?> response = toDoItemController.getTodoItems(testUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}