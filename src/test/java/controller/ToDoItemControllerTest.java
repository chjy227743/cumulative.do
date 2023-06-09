package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import com.cumulativeDo.model.*;
import  com.cumulativeDo.Controller.*;
import com.cumulativeDo.service.ToDoService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ToDoItemControllerTest {
    private ToDoItemController toDoItemController;
    private ToDoService toDoService;
    private UserController userController;
    private ToDoItem testTodoItem;
    private String testUserName;
    private String testTodo;
    private int courseId;
    private LocalDate due;

    @BeforeEach
    public void setUp() {
        toDoService = new ToDoService();
        userController = new UserController(toDoService);
        toDoItemController = new ToDoItemController(toDoService, userController);
        testUserName = "usr1";
        testTodo = "Test Task";
        courseId = 344;
        due = LocalDate.of(2020,1,1);
        testTodoItem = new ToDoItem(testTodo, courseId, due);
    }

    @Test
    public void addItemsExistingUserTest() {
        // Add an item to the user
        ResponseEntity<?> response = toDoItemController.addTodoItem(testTodo, courseId, due);
        assertEquals(testTodoItem, response.getBody());
        Set<ToDoItem> todoItems = toDoItemController.getTodoItems(testUserName).getBody();

        // test if the todoItem is in user and the status is correct
        assert todoItems != null;
        assertTrue(todoItems.contains(testTodoItem));
        assertNotEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(testTodoItem, response.getBody());
    }

    @Test
    public void getItemsExistingUserTest() {
        // Add a todo item for the test user
        ResponseEntity<?> response = toDoItemController.addTodoItem(testTodo, courseId, due);
        assertEquals(testTodoItem, response.getBody());

        ResponseEntity<Set<ToDoItem>> getResponse = toDoItemController.getTodoItems(testUserName);
        Set<ToDoItem> todoItems = getResponse.getBody();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todoItems, response.getBody());
    }


    @Test
    public void getItemNotExistingUserTest() {
        ResponseEntity<?> response = toDoItemController.getTodoItems(testUserName);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}