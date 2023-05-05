package server;

import controller.ToDoItemController;
import controller.UserController;
import model.User;
import server.utils.CORSFilter;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();

        // TODO: Create all the Spark Java routes you need here.
        UserController userController = new UserController();
        ToDoItemController toDoItemController = new ToDoItemController();

        /**
         * Route for registering a user
         */
        Spark.post("/registerUser", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                // Extract user registration data from the request
                String username = request.queryParams("username");
                String password = request.queryParams("password");

                User user = new User(2L, username, password);
                userController.registerUser(new User(2L, username, password));
                // Return a response indicating success or failure
                Gson gson = new Gson();
                return gson.toJson(user);
            }
        });

        // Add more routes for other functionality as needed
    }
}
