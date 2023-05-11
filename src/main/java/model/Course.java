package model;


import scraper.WebScraper;

import java.util.*;

public class Course {
    private int id;
    private String name;
    private String quarter;
    private Set<ToDoItem> assignment;

    public Course(int id) {
        WebScraper ws = new WebScraper(id);
        this.id = id;
    }
}
