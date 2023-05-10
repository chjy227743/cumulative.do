package scraper;

import model.ToDoItem;

import java.io.IOException;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


public class WebScraper {
    private final String courseId;
    private Set<String> keywords;

    public WebScraper(String courseId) {
        this.courseId = courseId;

        // possible keywords to find page of assignments
        keywords = new HashSet<String>(List.of("assignment", "lab", "homework", "exercise",
                "quiz", "exam", "schedule", "calendar"));
    }

    /**
     * Finds the course website for courseId in the current quarter.
     * @return String representation of the URL of the page
     */
    public String fetchCurrentURL() throws IOException {
        // TODO: implement function #1
        // TODO: assume quarter to be 23sp for demo. Will be replaced with a function
        // finds the web page for the course id
        String html = "https://courses.cs.washington.edu/courses/" + this.courseId + "/";
        Document doc = Jsoup.connect(html).get();

        // get the element where the current quarter course is located
        Element content = doc.getElementsByClass("first leaf").first();

        // get the current quarter link
        Element link = content.getElementsByTag("a").first();
        String curQuarterURL = link.absUrl("href");

        //return link.attr("href");
        return curQuarterURL;
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
    public Set<String> findKeywordURL() {
        // TODO: implement function #3
        Set<String> keywordUrls = new HashSet<>();
        try {
            Set<String> urls = findAllURL();
            for (String url : urls) {
                Document doc = Jsoup.connect(url).get();
                String title = doc.title().toLowerCase();
                boolean containsKeyword = false;

                for (String keyword : keywords) {
                    keyword = keyword.toLowerCase();
                    if (url.toLowerCase().contains(keyword) || title.contains(keyword)) {
                        containsKeyword = true;
                        break;
                    }
                }

                if (containsKeyword) {
                    keywordUrls.add(url);
                }
            }
        } catch (Exception e) {
            System.out.println("IOException in findKeywordURL" + e.getStackTrace());
        }
        return keywordUrls;



    }


    public Set<String> findAllURL() throws IOException {
        // Finds the list of HTML page accessible from the course website.
        // TODO: implement function #2
        String root = fetchCurrentURL();
        Set<String> urls = new HashSet<>();

        Document doc = Jsoup.connect(root).get();
        Elements linkElements = doc.select("a");


        Element content = doc.body();
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            urls.add(linkHref);
        }

        return urls;
    }
}