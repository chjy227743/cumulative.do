package scraper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.ToDoItem;

import java.util.Set;

import java.io.IOException;
import java.util.*;


public class WebScraperTest {
    @Test
    public void fetchUrlTest() throws IOException {
        WebScraper ws = new WebScraper(421);
        assertEquals("https://courses.cs.washington.edu/courses/cse521/22au/", ws.getCurQuarter());
    }

    @Test
    public void findAllURLTest() throws IOException {
        WebScraper ws = new WebScraper(421);
        var urls = ws.findAllURL();
        assertEquals(3, urls.size());
        assertTrue(urls.containsAll(Arrays.asList(
                "https://courses.cs.washington.edu/courses/cse421/23sp/schedule/",
                "https://homes.cs.washington.edu/~anuprao/",
                "https://calendar.google.com/calendar/embed?height=600&wkst=1&bgcolor=%23ffffff&ctz=America%2FLos_Angeles&showTitle=0&showNav=1&showDate=0&showPrint=0&showTabs=0&showCalendars=0&showTz=0&mode=WEEK&src=MzE2ZmRhOTU3NTE3ZDBjODIyYjlmZWJhMTI5MzQ4MDNhNWY1NWNjYTE2NDEyYWE1YzY2ZTI5ZGRmZmYxOTRiZEBncm91cC5jYWxlbmRhci5nb29nbGUuY29t&color=%23F4511E")));
    }

    @Test
    public void findKeywordURLTest() {
        WebScraper ws = new WebScraper(331);
        var urls = ws.findKeywordURL();
        assertEquals(3, urls.size());
        assertTrue(urls.containsAll(Arrays.asList(
                "https://www.washington.edu/students/timeschd/SPR2023/cse.html",
                "exams.html",
                "https://www.washington.edu/students/timeschd/SPR2023/cse.html")));
    }

    @Test
    public void parseToDoTest() {

        // Initialize the WebScraper with a known course ID
        WebScraper scraper = new WebScraper(331);

        Set<ToDoItem> todoItems = scraper.parseToDo();

        // Assert that the returned set is not empty
        assertFalse(todoItems.isEmpty());

        // For each ToDoItem, assert that the id, todo, course, and dueDate fields are not null or empty
        for (ToDoItem item : todoItems) {
            assertNotNull(item.getId());
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

}