package scraper;

import model.ToDoItem;

import java.util.*;

public class WebScraper {
    private final String courseId;
    private Set<String> keywords;

    public WebScraper(String courseId) {
        this.courseId = courseId;
        // possible keywords to find page of assignments
        keywords = new HashSet<>(List.of("assignment", "lab", "homework", "exercise",
                "guiz", "exam", "schedule", "calendar"));
    }

    /**
     * Finds the course website for courseId in the current quarter.
     * @return String representation of the URL of the page
     */
    public String fetchCurrentURL() {
        // TODO: implement function #1
        return null;
    }

    /**
     * Parse todos from assignment page.
     * @return The set of to do items from the course website.
     */
    public Set<ToDoItem> parseToDo() {
        // TODO: implement function #4
        // inspect table? tag is <tr>
        return null;
    }


    /**
     * Finds the list of keyword related HTML page from all HTML.
     * @return a set of String representation of the URLs
     */
    public Set<String> findKeywordHTML() {
        // TODO: implement function #3
        return null;
    }


    private Set<String> findALLHTML() {
        // Finds the list of HTML page accessible from the course website.
        // TODO: implement function #2
        return null;
    }
}