package scraper.util;

import model.ToDoItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class ScraperUtils {
    private static String navBar = "site/nav-bar.html";
    private static List<String> keywords = List.of("assignment", "lab", "homework", "exercise",
            "quiz", "exam", "schedule", "calendar");

    public static Set<ToDoItem> parse311(String url) throws IOException {

        return null;
    }

    public static Set<ToDoItem> parse351() {
        return null;
    }

    public static Set<ToDoItem> parse331(String url) throws IOException {
        Set<ToDoItem> todos = new HashSet<>();

        String navBarUrl = url + navBar;
        Document doc = Jsoup.connect(navBarUrl).get();

        Element content = doc.getElementsByClass("nav navbar-nav").first();
        Elements links = content.getElementsByTag("a");

        Map<String, String> menu = new HashMap<>();
        for (Element link : links) {
            // gets menu string
            String str = ((TextNode) link.childNode(0)).getWholeText();
            if (str.charAt(str.length()-1) == 's') str = str.substring(0, str.length()-1);

            menu.put(str.toLowerCase(), link.attr("href"));
        }

        String keywordUrl = null;
        for (String keyword : keywords) {
            if (menu.containsKey(keyword)) {
                keywordUrl = url + menu.get(keyword);
                break;
            }
        }




        return null;
    }

    public static Set<ToDoItem> parse333() {
        return null;
    }

    public static Set<ToDoItem> parse344() {
        return null;
    }

    public static Set<ToDoItem> parse444() {
        return null;
    }
}
