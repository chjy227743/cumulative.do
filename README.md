# cumulative.do

## Introduction and Goals:

This product will be a to do list web application that aims to collect assignment data from Canvas and course websites to automatically create and update a personalized list of to-dos for the user. The application will also allow users to be able to add in other to do list items. To-do list items will be displayed in chronological order of due date and will be crossed off from the list as the user checks off the tasks. The target audience of this product would be students at UW, particularly students in the CSE department, but can eventually be expanded to students in general.

## Repository Layout:

- All frontend code are contained in src/frontend.
- All backend code contained in src/main/java.
- All configuration/set up content contained in .idea folder.
- All tests will be contained in the tests folder.
- All documentations are in the top level.


## Prerequisites:
We recommend to use IntelliJ IDEA to build and run this project, here are some prerequisites:
1. JDK 18- To compile and run the Java code.
### Installing JDK 18
#### For Windows:
- Go to Oracle's JDK download page: https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html
- Download the "Windows x64 Installer" if you have a 64-bit version of Windows.
- After the download is complete, run the installer and follow the instructions. By default, the JDK will be installed in a directory such as C:\Program Files\Java\jdk-18.
- After installation, you need to set up the environment variable. Go to Control Panel -> System and Security -> System -> Advanced System Settings -> Environment Variables.
- Under System Variables, click New. For Variable Name, enter JAVA_HOME. For Variable Value, enter the path where the JDK was installed (e.g., C:\Program Files\Java\jdk-18).
- Click OK to close all windows.
- Open a new Command Prompt (cmd) and type java -version to verify that JDK 18 is correctly installed.

#### For MacOS:
- Go to Oracle's JDK download page: https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html
- Download the "macOS Installer".
- After the download is complete, run the installer and follow the instructions.
- After installation, open Terminal and type java -version to verify that JDK 18 is correctly installed.

#### For Linux:
- Go to Oracle's JDK download page: https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html
- Download the "Linux Compressed Archive" for your specific version of Linux.
- Open Terminal and navigate to the directory where the .tar.gz file was downloaded.
- Extract the archive with the command tar -xvf [file name].
- Move the extracted folder to /usr/lib/jvm with the command sudo mv ./[extracted folder] /usr/lib/jvm/.

Now, you need to set up the environment variable. Open /etc/environment in a text editor with root privileges (sudo nano /etc/environment).
-  In this file, add the following line (replacing [path to JDK] with the path where the JDK was installed): JAVA_HOME="[path to JDK]"
-  Save the file and exit. Reload the file with the command source /etc/environment.
-  Verify the installation by typing java -version in the Terminal.
Please replace "[file name]" and "[extracted folder]" with your actual downloaded file name and extracted folder name. Note that these instructions assume you have the necessary permissions to install software on your machine.
Also, replace "[path to JDK]" with the actual path to your JDK installation. This path will typically look something like /usr/lib/jvm/jdk-18. You can find it by navigating to /usr/lib/jvm/ in your file system and looking for a directory that starts with jdk-18.

## Build and Test the System:

### Build
1. Clone the repository: git clone https://github.com/chjy227743/cumulative.do.git
2. Navigate to the project directory: cd cumulative.do
3. Use correct SDK and JDK to build the project:
- open the project
- File -> Project Structure -> choose jdk and sdk of version 18
5. Build the project using Gradle: ./gradlew build -x test (test might fail at this point so we are skipping)
- This command will compile the source code and package the application into a JAR file. If the build is successful, you will see a "BUILD SUCCESS" message at the end of the output.

### Test

To run the tests using Maven: ./gradlew test
This command will run all the test cases in the project. If the tests pass, you will see a "BUILD SUCCESS" message at the end of the output, along with a summary of the test results.
Remember that the tests require a working internet connection to function correctly since they interact with the web scraper component. If you encounter issues while running the tests, please ensure that you have a stable internet connection and that the target websites are accessible.

### Run application

1. With the complete project repository cloned, run the application from `java/com/cumulativeDo/CumulativeDo.java`
2. Then, run `npm start` to retrieve the frontend link.
3. Click on the link, which should be http://localhost:3000 to open
4. To view the root of our Springboot/backend server, open a new tab on your browser and visit http://localhost:8080/api.
5. To view the list of users registered in the application, use the link http://localhost:8080/api/users.



## Operational Use Cases:

- Use Case #2: Manually input TODO item.
