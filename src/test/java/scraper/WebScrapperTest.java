package scraper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


public class WebScrapperTest {


    @Test
    public void addItemsExistingUserTest() throws IOException {
        WebScraper ws = new WebScraper("cse444");

        System.out.println(ws.findAllURL());
    }



}