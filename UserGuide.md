# CumulativeDo User Guide

## Introduction

This product will be a to-do list web application that aims to collect assignment data from Canvas and course websites to automatically create and update a personalized list of to-dos for the user. The application will also allow users to be able to add in other to do list items. To-do list items will be displayed in chronological order of due date and will be crossed off from the list as the user checks off the tasks. The target audience of this product would be students at UW, particularly students in the CSE department, but can eventually be expanded to students in general.

## Software Guide

### Install:
- Install JDK 18: Follow the instructions provided in the README based on your operating system (Windows, MacOS, or Linux).

- Clone the Repository: Use the command git clone https://github.com/chjy227743/cumulative.do.git in your terminal to clone the repository to your local system.

### Startup:
1. Navigate to the project directory: cd cumulative.do
2. Use the correct SDK and JDK to build the project:
- Open the project
- Navigate to File -> Project Structure -> choose JDK and SDK of version 18
3. Build the project using Gradle: ./gradlew build -x test
4. Run the application from java/com/cumulativeDo/CumulativeDo.java
5. Run npm start to retrieve the frontend link.

### Run:
Click on the frontend link, which should be http://localhost:3000 to open the application.

### Bug Report
If a bug was found, you can add an issue.
Please follow the bug writing guidelines and tag the issue with “bug”. 

### Known Bugs
State inconsistency: Mark a todo item as completed in the app would not change the backend/database state of the item yet.
