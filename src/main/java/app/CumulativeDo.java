package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CumulativeDo {

    public static void main(String[] args) {
        SpringApplication.run(CumulativeDo.class, args);
    }

    @RestController
    public class HomeContoller {
        @RequestMapping("/")
        public String index() {
            return "index";
        }
    }
}