package model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

//@Entity
@Data
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String todo;
    private boolean completed;
    private String course;
    private Date dueDate;

    public ToDoItem(Long id, String todo, String course, Date dueDate) {
        this.id = id;
        this.todo = todo;
        this.completed = false;
        this.course = course;
        this.dueDate = dueDate;
    }
}