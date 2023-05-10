# cumulative.do

## Introduction and Goals:

This product will be a to do list web application that aims to collect assignment data from Canvas and course websites to automatically create and update a personalized list of to-dos for the user. The application will also allow users to be able to add in other to do list items. To-do list items will be displayed in chronological order of due date and will be crossed off from the list as the user checks off the tasks. The target audience of this product would be students at UW, particularly students in the CSE department, but can eventually be expanded to students in general.

## Repository Layout:

- All backend code contained in src/main/java.
- All configuration/set up content contained in .idea folder.
- All tests will be contained in the tests folder.

## Prerequisites
1. JDK 18- To compile and run the Java code.
2. Maven - To manage dependencies and build the project.

## Build and Test the System
### Build
1. Clone the repository: git clone https://github.com/chjy227743/cumulative.do.git
2. Navigate to the project directory: cd cumulative.do
3. Build the project using Maven: mvn clean install
- This command will compile the source code, run the tests, and package the application into a JAR file. If the build is successful, you will see a "BUILD SUCCESS" message at the end of the output.

### Test

To run the tests using Maven: mvn test
This command will run all the test cases in the project. If the tests pass, you will see a "BUILD SUCCESS" message at the end of the output, along with a summary of the test results.
Remember that the tests require a working internet connection to function correctly since they interact with the web scraper component. If you encounter issues while running the tests, please ensure that you have a stable internet connection and that the target websites are accessible.
