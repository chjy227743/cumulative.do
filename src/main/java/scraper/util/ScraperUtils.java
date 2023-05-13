package scraper.util;

import controller.ToDoItemController;
import model.ToDoItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        doc = Jsoup.connect(keywordUrl).get();
        Element table = doc.getElementsByClass("listtable").first();
        Elements rows = table.getElementsByTag("tr");
        rows.remove(0);

        Set<ToDoItem> todos = new HashSet<>();
        for (Element row : rows) {
            System.out.println(1);
            Element dateCol = row.getElementsByTag("td").first();
            String dateString = ((TextNode) dateCol.childNode(0)).getWholeText();
            LocalDate date = parseDate(dateString, 2023);
            Element hwCol = row.getElementsByTag("td").get(1).getElementsByTag("a").first();
            String hwString;
            if (hwCol == null) {
                hwCol = row.getElementsByTag("td").get(1).getElementsByTag("span").first();
                hwString = ((TextNode) hwCol.childNode(0)).getWholeText().split(" ")[0];
            } else {
                hwString = ((TextNode) hwCol.childNode(0)).getWholeText();
            }
            System.out.println(hwString);
            todos.add(new ToDoItem(hwString, 331, date));
        }

        return todos;
    }

    private static LocalDate parseDate(String dateString, int year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy");
        String[] parts = dateString.split(" ");
        String cleanedDateString = parts[0] + " " + parts[parts.length - 1];
        return LocalDate.parse(cleanedDateString + " " + year, formatter);
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
