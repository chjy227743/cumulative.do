package com.cumulativeDo.scraper.util;

import com.cumulativeDo.Controller.ToDoItemController;
import com.cumulativeDo.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            todos.add(new ToDoItem(hwString, 331, date));
        }

        return todos;
    }

    private static LocalDate parseDate(String dateString, int year) {
        String[] formats = { "MMMM d yyyy", "MMM d yyyy" };
        String[] parts = dateString.strip().split(" ");
        String cleanedDateString = parts[0] + " " + parts[parts.length - 1] + " " + year;
        for (String format : formats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.US);
                return LocalDate.parse(cleanedDateString, formatter);
            } catch (DateTimeParseException e) {
                // Do nothing - just try the next format
            }
        }
        throw new IllegalArgumentException("Date '" + dateString + "' could not be parsed");
    }

    public static Set<ToDoItem> parse421(String url) throws IOException {
        String navBarUrl = url + navBar;
        Document doc = Jsoup.connect(navBarUrl).get();

        Element content = doc.getElementsByClass("navbar navbar-inverse navbar-fixed-top").first();
        Elements links = content.getElementsByTag("a");

        Map<String, String> menu = new HashMap<>();
        for (Element link : links) {
            // gets menu string
            String str = ((TextNode) link.childNode(0)).getWholeText();
            if (str.charAt(str.length()-1) == 's') str = str.substring(0, str.length()-1);

            menu.put(str.toLowerCase(), link.attr("href"));
        }

        String keywordUrl = null;
        for (String option : menu.keySet()) {
            String[] splited = option.split(" ");
            for (String str : splited) {
                if (keywords.contains(str)) {
                    keywordUrl = url + menu.get(option);
                    break;
                }
            }

        }

        doc = Jsoup.connect(keywordUrl).get();
        Element table = doc.getElementsByTag("table").first();

        Elements rows = table.getElementsByTag("tr");
        rows.remove(0);

        Set<ToDoItem> todos = new HashSet<>();
        for (Element row : rows) {
            Element dateCol = row.getElementsByTag("td").first();
            String dateString = ((TextNode) dateCol.childNode(0)).getWholeText();
            LocalDate date = parseDate(dateString, 2023);
            Element hwCol = row.getElementsByTag("td").get(1);
            String hwStr = ((TextNode) hwCol.childNode(0)).getWholeText();
            if (hwStr.toLowerCase().contains("homework") || hwStr.toLowerCase().contains("exam")) {
                todos.add(new ToDoItem(hwStr.substring(0, hwStr.length() - 1), 421, date));
            }
        }

        return todos;
    }

    public static Set<ToDoItem> parse332(String url) throws IOException {
        String navBarUrl = url + navBar;
        Document doc = Jsoup.connect(navBarUrl).get();

        Element content = doc.getElementsByClass("navbar navbar-inverse navbar-fixed-top").first();
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
        Elements tables = doc.getElementsByTag("table");

        Set<ToDoItem> todos = new HashSet<>();

        Element exTable = tables.first();
        Elements rows = exTable.getElementsByTag("tr");
        rows.remove(0);
        for (Element row : rows) {
            Element hwCol = row.getElementsByTag("td").first();
            Element dateCol = row.getElementsByTag("td").get(1);
            String dateString = ((TextNode) dateCol.childNode(0)).getWholeText();
            if (dateString.equals("CANCELLED")) continue;
            LocalDate date = parseDate(dateString.split(", ")[1], 2023);
            String hwStr = ((TextNode) hwCol.child(0).childNode(0)).getWholeText();

            todos.add(new ToDoItem(hwStr, 332, date));
        }

        Element projectTable = tables.get(1);
        rows = projectTable.getElementsByTag("tr");
        rows.remove(0);
        for (Element row : rows) {
            Element hwCol = row.getElementsByTag("td").first();
            Element dateCol = row.getElementsByTag("td").get(1);

            String project = ((TextNode) hwCol.child(0).child(0).childNode(0)).getWholeText();

            Elements dates = dateCol.getElementsByTag("hr");

            for (Element item : dates) {
                String textAfterHr = item.nextSibling().toString();
                String task = project + textAfterHr.split(": ")[0];
                String dateString = textAfterHr.split(": ")[1];
                LocalDate date = parseDate(dateString.split(", ")[1], 2023);
                todos.add(new ToDoItem(task, 332, date));
            }
        }

        return todos;
    }

    public static Set<ToDoItem> parse312(String url) throws IOException {
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
