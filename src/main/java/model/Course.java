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
        this.name = ws.getCourseTitle();
        this.quarter = ws.getCurQuarter();
        this.assignment = ws.parseToDo();
    }

    public int getCourseId() {
        return this.id;
    }

    public String getCourseName() {
        return this.name;
    }

    public String getQuarter() {
        return this.quarter;
    }

    public Set<ToDoItem> getAssignment() {
        return this.assignment;
    }
}
