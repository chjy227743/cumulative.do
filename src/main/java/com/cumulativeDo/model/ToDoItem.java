package com.cumulativeDo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

//@Entity
@Getter
/**
 * ToDoItem class represents a single task item in a to-do list.
 * Each item contains a description, a completion status, an associated course, and a due date.
 */
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String todo;
    private boolean completed;
    private final Integer courseId;
    private final LocalDate dueDate;

    /**
     * Constructs a ToDoItem with the specified id, task description, course, and due date.
     * The completion status is set to false by default.
     *
     * @param todo    the task description
     * @param courseId  the associated course code for the task
     * @param dueDate the due date for the task
     */
    public ToDoItem(String todo, Integer courseId, LocalDate dueDate) {
        this.todo = todo;
        this.completed = false;
        this.courseId = courseId;
        this.dueDate = dueDate;
    }

    /**
     * Marks the todo item as complete.
     */
    public void markComplete() {
        this.completed = true;
    }

    // Getters
    public String getTodo() {
        return this.todo;
    }

    public int getCourseId() {
        if (this.courseId == null) return -1;
        return this.courseId;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

}