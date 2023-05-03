import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpSession;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions.*;

import model.*;
import controller.*;

@SpringBootTest
public class UserControllerTest {
    private ToDoItemController toDoItemController;
    private ToDoItem testTodoItem;
    private long testUserId;

    @BeforeEach
    public void setUp() {
        toDoItemController = new ToDoItemController();
        toDoItemController.items = new HashMap<>();
        testUserId = 1L;
        testTodoItem = new ToDoItem(1L, "Test Task", "Test Description", false, new Date());
    }

    @Test
    public void addItemsExistingUserTest() {
        // Add an empty set for the test user
        toDoItemController.items.put(testUserId, new HashSet<>());

        ResponseEntity<ToDoItem> response = toDoItemController.addTodoItem(testUserId, testTodoItem);
        Set<ToDoItem> todoItems = toDoItemController.items.get(testUserId);

        assertTrue(todoItems.contains(testTodoItem));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(testTodoItem, response.getBody());
    }

    @Test
    public void getItemsExistingUserTest() {
        // Add a todo item for the test user
        Set<ToDoItem> todoItems = new HashSet<>();
        todoItems.add(testTodoItem);
        toDoItemController.items.put(testUserId, todoItems);

        ResponseEntity<?> response = toDoItemController.getTodoItems(testUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todoItems, response.getBody());
    }

    @Test
    public void getItemNotExistingUserTest() {
        ResponseEntity<?> response = toDoItemController.getTodoItems(testUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to find user.", response.getBody());
    }
}