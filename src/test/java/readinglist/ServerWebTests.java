package readinglist;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.*;

/**
 * Created by pivotal on 5/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=ReadingListApplication.class)
@WebIntegrationTest(randomPort = true)
public class ServerWebTests {
    private static SafariDriver chromeDriver;



    @Value("${local.server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser() {
        chromeDriver = new SafariDriver();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        chromeDriver.quit();
    }

    @Test
    public void addBookToEmptyList() {
        String baseUrl = "http://localhost:" + port + "/readinglist/someone";

        chromeDriver.get(baseUrl);

        assertEquals(chromeDriver.findElementByTagName("div").getText(), "You have no books in your book list");

        chromeDriver.findElementByName("title").sendKeys("BOOK TITLE");
        chromeDriver.findElementByName("author").sendKeys("BOOK AUTHOR");
        chromeDriver.findElementByName("isbn").sendKeys("1234567890");
        chromeDriver.findElementByName("description").sendKeys("DESCRIPTION");
        chromeDriver.findElementByTagName("form").submit();

        WebElement dl =
                chromeDriver.findElementByCssSelector("dt.bookHeadline");
        assertEquals(dl.getText(), "BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)");

        WebElement dd =
                chromeDriver.findElementByCssSelector("dd.bookDescription");
        assertEquals(dd.getText(), "DESCRIPTION");

    }
}
