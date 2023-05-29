package com.cumulativeDo.scraper;

import com.cumulativeDo.model.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import scraper.util.ScraperUtils;
import java.text.SimpleDateFormat;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


public class WebScraper {
    private final int id;
    private String rootUrl;
    private Set<String> keywords;
    private String curQuarterUrl;


    public WebScraper(int id) {
        this.id = id;

        this.rootUrl = "https://courses.cs.washington.edu/courses/cse" + id + "/";
        this.curQuarterUrl = getCurQuarterUrl();

        // possible keywords to find page of assignments
        keywords = new HashSet<String>(List.of("assignment", "lab", "homework", "exercise",
                "quiz", "exam", "schedule", "calendar"));
    }


    /**
     * Finds the course title for courseId.
     * @return The course title for the course id
     */
    public String getCourseTitle() {
        // finds the web page for the course id
        try {
            Document doc = Jsoup.connect(curQuarterUrl).get();
            return doc.title();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse todos from assignment page.
     * @return The set of to do items from the course website.
     */
    public Set<ToDoItem> parseToDo() {
//        // TODO: implement function #4
//
//        Set<ToDoItem> todoItems = new HashSet<>();
//        // adjust the format to match the website's date format
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//
//        try {
//            // URLs of assignment related pages
//            Set<String> assignmentUrls = findKeywordURL();
//
//            for(String url: assignmentUrls) {
//                Document doc = Jsoup.connect(url).get();
//                // select rows in table with id 'hwlist'
//                Elements rows = doc.select("table#hwlist tr");
//                for (Element row : rows) {
//                    // select cells in each row
//                    Elements cols = row.select("td");
//
//                    // Ensure there are at least 3 columns (for title and due date)
//                    if(cols.size() >= 3) {
//                        String title = cols.get(0).text(); // title is in the first column
//                        Date dueDate = formatter.parse(cols.get(2).text()); // due date is in the third column
//
//                        ToDoItem item = new ToDoItem(null, title, this.id, dueDate);
//                        todoItems.add(item);
//                    }
//                }
//            }
//        }catch (IOException e) {
//            System.out.println("IOException in parseToDo" + e.getStackTrace());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        if (this.id == 331) {
            try {
                System.out.println(curQuarterUrl);
                return ScraperUtils.parse331(curQuarterUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (this.id == 421) {
            try {
                System.out.println(curQuarterUrl);
                return ScraperUtils.parse421(curQuarterUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private String getCurQuarterUrl() {
        // find the root url
        this.rootUrl = "https://courses.cs.washington.edu/courses/cse" + this.id + "/";

        Document doc = null;
        try {
            doc = Jsoup.connect(rootUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the element where the current quarter course is located
        Element content = doc.getElementsByClass("first leaf").first();

        // get the current quarter link
        Element link = content.getElementsByTag("a").first();
        return link.absUrl("href");
    }

}