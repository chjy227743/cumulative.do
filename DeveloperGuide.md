# CumulativeDo User Guide

## Developer Guide

### Obtain Source Code
- To obtain our source code, please clone the repository: git clone https://github.com/chjy227743/cumulative.do.git
- Or go to our GitHub: https://github.com/chjy227743/cumulative.do

There are detailed instructions for how our project was constructed and how to set up, test, build, and run our web application.

### Repo Layout
#### The repository layout:
- All frontend code are contained in src/frontend: https://github.com/chjy227743/cumulative.do/tree/main/src/frontend.

- All backend code contained in src/main/java: https://github.com/chjy227743/cumulative.do/tree/main/src/main/java/com/cumulativeDo.

- All configuration/set up content contained in .idea folder: https://github.com/chjy227743/cumulative.do/tree/main/.idea.

- All tests will be contained in the tests folder (src/test): https://github.com/chjy227743/cumulative.do/tree/main/src/test/java.

- All documentations are in the top level


### How to build the software
There are clear instructions on how to build the project called “Build and Test the System:” in README.md which is also located on our homepage: https://github.com/chjy227743/cumulative.do#build.

### Test the Software
Here’s the instruction on how to test the software: https://github.com/chjy227743/cumulative.do#test.


### Add New Tests
Steps for how to add more tests are in our living document: https://docs.google.com/document/d/1_rcy4G4i_9bVyt5nvqX00Dl_XcPAy-npdpDbupYRwiI/edit#.

Please go to the “Testing and Continuous Integration” section for the instructions. While naming test files, we usually use “the name of classes or methods that need to be tested” + “Test”. For example, if we want to test ToDoItemController, we will name the test “ToDoItemControllerTest”.


### Build a Release
There is an instruction on how to install JDK 18 to run our project from Prerequisites in our GitHub page: https://github.com/chjy227743/cumulative.do#prerequisites.

After building a release, the developer can use cse331 in Spring 2023 as a sanity check to see if this application can generate the to-do list correctly.
