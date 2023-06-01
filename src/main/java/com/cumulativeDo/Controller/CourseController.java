package com.cumulativeDo.Controller;

import com.cumulativeDo.model.Course;
import com.cumulativeDo.model.ToDoItem;
import com.cumulativeDo.model.User;
import com.cumulativeDo.service.ToDoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/")
public class CourseController {
    private Set<Course> courses;

    public CourseController() {
        courses = new HashSet<>();
    }

    /**
     * Registers a new user with the provided user details.
     *
     * @param courseId A course to be added.
     * @return A ResponseEntity with the HTTP status indicating the success or failure of the user registration.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("addCourse")
    public ResponseEntity<?> addCourse(@RequestBody int courseId) {
        // Check if the course already exists
        boolean hasCourse = courses.stream().anyMatch(u -> u.getCourseId() == courseId);

        if (hasCourse) {
            return new ResponseEntity<>("Course already added.", HttpStatus.CONFLICT);
        }

        Course newCourse = new Course(courseId);
        courses.add(newCourse);

        Set<ToDoItem> todos = newCourse.getAssignment();

        if (todos == null) return new ResponseEntity<>("Course not supported", HttpStatus.BAD_REQUEST);

        Gson gson = new Gson();
        String jsonTodoList = gson.toJson(todos);


        // Return the ResponseEntity with the registered user and HTTP status code 201 (Created)
        return new ResponseEntity<>(jsonTodoList, HttpStatus.CREATED);
    }



    // Getters
    @GetMapping("courses")
    public Set<Course> getCourses() {
        return this.courses;
    }
}
