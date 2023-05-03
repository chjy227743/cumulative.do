import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpSession;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions.*;

import model.*;
import controller.*;

@SpringBootTest
public class UserControllerTest {
    private UserController userController;
    private User user;
    private HttpSession testSession;

    @BeforeEach
    public void setUp() {
        userController = new UserController();
        userController.users = new HashSet<>();
        user = new User("John Doe", "john.doe@example.com", "12341234");
        testSession = new TestHttpSession();
    }

    @Test
    void registerNonExistingUserTest() {
        ResponseEntity<?> response = userController.registerUser(testUser);

        assertTrue(userController.users.contains(testUser));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    public void registerConflictingUserTest() {
        userController.users.add(testUser);
        ResponseEntity<?> response = userController.registerUser(testUser);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already exists.", response.getBody());
    }

    @Test
    void loginSuccessTest() {
        ResponseEntity<?> response = userController.loginUser(user, testSession);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        assertEquals(user, testSession.getAttribute("loggedInUser"));
    }

    @Test
    public void loginUnauthorizedTest() {
        User wrongUser = new User("WrongUser", "wrong.user@example.com", "wrongPassword");
        ResponseEntity<?> response = userController.loginUser(wrongUser, testSession);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password. Please register if you don't have an account.", response.getBody());
    }

    // Add more tests as needed
    private class TestHttpSession implements HttpSession {
        private final Map<String, Object> attributes = new HashMap<>();

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        @Override
        public void setAttribute(String name, Object value) {
            attributes.put(name, value);
        }

        // Implement other required methods with empty bodies or default values.
        // ...
    }
}