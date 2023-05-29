package scraper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.cumulativeDo.model.*;
import com.cumulativeDo.scraper.WebScraper;

import java.util.Set;

import java.io.IOException;
import java.util.*;


public class WebScraperTest {

    @Test
    public void parseToDoTest() {

        // Initialize the WebScraper with a known course ID
        WebScraper scraper = new WebScraper(331);

        Set<ToDoItem> todoItems = scraper.parseToDo();

        // Assert that the returned set is not empty
        assertFalse(todoItems.isEmpty());

        // For each ToDoItem, assert that the id, todo, course, and dueDate fields are not null or empty
        for (ToDoItem item : todoItems) {
            assertFalse(item.getTodo().isEmpty());
//            assertEquals(331, item.getCourse());
            assertNotNull(item.getDueDate());
        }
    }

    @Test
    public void parse331Test() {

        // Initialize the WebScraper with a known course ID
        WebScraper scraper = new WebScraper(331);

        Set<ToDoItem> todoItems = scraper.parseToDo();

        System.out.println(todoItems);
    }

    @Test
    public void parse421Test() {

        // Initialize the WebScraper with a known course ID
        WebScraper scraper = new WebScraper(421);

        Set<ToDoItem> todoItems = scraper.parseToDo();

        System.out.println(todoItems);
    }

    @Test
    public void parse332Test() {

        // Initialize the WebScraper with a known course ID
        WebScraper scraper = new WebScraper(332);

        Set<ToDoItem> todoItems = scraper.parseToDo();

        System.out.println(todoItems);
    }

    @Test
    public void parse312Test() {

        // Initialize the WebScraper with a known course ID
        WebScraper scraper = new WebScraper(312);

        Set<ToDoItem> todoItems = scraper.parseToDo();

        Set<String> todos = new HashSet<>();

        // the correct todos
        for (int i = 1; i <= 8; i++) {
            String todo = "Homework " + i;
            todos.add(todo);
        }

        // check if our parsed result contains the correct todos
        for (ToDoItem item : todoItems) {
            assertTrue(todos.contains(item.getTodo()));
        }
    }
}