package model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

//@Entity
/**
 * ToDoItem class represents a single task item in a to-do list.
 * Each item contains a description, a completion status, an associated course, and a due date.
 */
@Data
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String todo;
    private boolean completed;
    private String course;
    private Date dueDate;

    /**
     * Constructs a ToDoItem with the specified id, task description, course, and due date.
     * The completion status is set to false by default.
     *
     * @param id      the unique identifier for the to-do item
     * @param todo    the task description
     * @param course  the associated course for the task
     * @param dueDate the due date for the task
     */
    public ToDoItem(Long id, String todo, String course, Date dueDate) {
        this.id = id;
        this.todo = todo;
        this.completed = false;
        this.course = course;
        this.dueDate = dueDate;
    }
}