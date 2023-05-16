package com.cumulativeDo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a user in the application.
 */
//@Entity
@Data
public class User {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    private String username;
    private String password;

    /**
     * Constructs a new User instance with the specified parameters.
     *
     * @param username the username of the user (unique identifier)
     * @param password the password of the user
     */
    public User(String username, String password) {
//        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }
}