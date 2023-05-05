package model;
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
    private Long id;
    private String username;
    private String password;

    /**
     * Constructs a new User instance with the specified parameters.
     *
     * @param id       the unique identifier for the user
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}