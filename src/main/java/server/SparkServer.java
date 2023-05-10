package server;

import controller.ToDoItemController;
import controller.UserController;
import model.ToDoItem;
import model.User;
import server.utils.CORSFilter;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
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

                User user = new User(username, password);
                userController.registerUser(user);
                // Return a response indicating success or failure
                Gson gson = new Gson();
                return gson.toJson(user);
            }
        });

        Spark.post("/addTodo", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String todo = request.queryParams("description");
                String course = request.queryParams("course");
                String dueDate = request.queryParams("date");
                Date date;
                // parse the date
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    date = formatter.parse(dueDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }

                ToDoItem toDoItem = new ToDoItem(toDoItemController.getId(), todo, course, date);
                Gson gson = new Gson();
                return gson.toJson(toDoItem);
            }
        });



    }
}
