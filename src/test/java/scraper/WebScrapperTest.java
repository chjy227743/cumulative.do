package scraper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.*;


public class WebScrapperTest {
    @Test
    public void fetchUrlTest() throws IOException {
        WebScraper ws = new WebScraper("cse521");
        assertEquals("https://courses.cs.washington.edu/courses/cse521/22au/", ws.fetchCurrentURL());
    }

    @Test
    public void findAllURLTest() throws IOException {
        WebScraper ws = new WebScraper("cse421");
        Set<String> urls = ws.findAllURL();
        assertEquals(3, urls.size());
        assertTrue(urls.containsAll(Arrays.asList(
                "https://courses.cs.washington.edu/courses/cse421/23sp/schedule/",
                "https://homes.cs.washington.edu/~anuprao/",
                "https://calendar.google.com/calendar/embed?height=600&wkst=1&bgcolor=%23ffffff&ctz=America%2FLos_Angeles&showTitle=0&showNav=1&showDate=0&showPrint=0&showTabs=0&showCalendars=0&showTz=0&mode=WEEK&src=MzE2ZmRhOTU3NTE3ZDBjODIyYjlmZWJhMTI5MzQ4MDNhNWY1NWNjYTE2NDEyYWE1YzY2ZTI5ZGRmZmYxOTRiZEBncm91cC5jYWxlbmRhci5nb29nbGUuY29t&color=%23F4511E")));
    }

    @Test
    public void findAllURLTest2() throws IOException {
        WebScraper ws = new WebScraper("cse444");
        Set<String> urls = ws.findAllURL();
        System.out.println(urls);
    }

    @Test
    public void findKeywordURLTest() {
        WebScraper ws = new WebScraper("cse331");
        var urls = ws.findKeywordURL();
        assertEquals(3, urls.size());
        assertTrue(urls.containsAll(Arrays.asList(
                "https://www.washington.edu/students/timeschd/SPR2023/cse.html",
                "exams.html",
                "https://www.washington.edu/students/timeschd/SPR2023/cse.html")));
    }

    @Test
    public void parseToDoTest() {
        // TODO: add test for parseToDo
    }

}