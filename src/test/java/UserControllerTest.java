import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.Mockito;

import java.util.HashSet;

import model.*;
import controller.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {
    private UserController userController;
    private User testUser1;
    private HttpSession testSession;

    @BeforeEach
    public void setUp() {
        userController = new UserController();
        testUser1 = new User(1L, "john.doe@example.com", "12341234");
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Call the getSession() method on the HttpServletRequest object to create a new HttpSession
        testSession = request.getSession();

        // Assert that the HttpSession object is not null
        assertNotNull(testSession);
    }

    @Test
    void registerNonExistingUserTest() {
        ResponseEntity<?> response = userController.registerUser(testUser1);

        assertTrue(userController.getUsers().contains(testUser1));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser1, response.getBody());
    }

    public void registerConflictingUserTest() {
        ResponseEntity<?> response = userController.registerUser(testUser1);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already exists.", response.getBody());
    }

    @Test
    void loginSuccessTest() {
        ResponseEntity<?> response = userController.loginUser(testUser1, testSession);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser1, response.getBody());
        assertEquals(testUser1, testSession.getAttribute("loggedInUser"));
    }

//    @Test
//    public void loginUnauthorizedTest() {
//        User wrongUser = new User(2L, "wrong.user@example.com", "wrongPassword");
//        ResponseEntity<?> response = userController.loginUser(wrongUser, testSession);
//
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//        assertEquals("Invalid username or password. Please register if you don't have an account.", response.getBody());
//    }
//
//    // Add more tests as needed
//    private class TestHttpSession implements HttpSession {
//        private final Map<String, Object> attributes = new HashMap<>();
//
//        @Override
//        public Object getAttribute(String name) {
//            return attributes.get(name);
//        }
//
//        @Override
//        public void setAttribute(String name, Object value) {
//            attributes.put(name, value);
//        }
//
//        // Implement other required methods with empty bodies or default values.
//        // ...
//    }
}