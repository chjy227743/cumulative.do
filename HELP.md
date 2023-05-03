# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.cumulative.do' is invalid and this project uses 'com.example.demo' instead.

# Getting Started

### Setting up Maven
To ensure Maven is properly setup on your IDE, please follow the steps below:
1. Download the newest version of Maven's zip archive from https://maven.apache.org/download.cgi
2. Decompress the zip file and place it in User/<user>/opt
3. Open terminal and open `.zshrc` through vim 
4. Add the following lines of code into the file <br>
   `export M2_HOME=/Users/<userName>/opt/apache-maven-3.9.1` <br>
   `export PATH=$PATH:$M2_HOME/bin` <br>
    Save and close the file.
5. Recompile `.zshrc` by running `source ~/.zshrc` in the command line terminal
6. Run the `mvn -v` command to ensure Maven has been properly set up.

Note: If your MacOS uses an older version, replace all `zshrc` in the instructions by `bash_profile`.


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

