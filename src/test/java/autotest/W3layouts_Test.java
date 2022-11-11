package autotest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class W3layouts_Test {

    private Logger logger = LogManager.getLogger(W3layouts_Test.class);
    private WebDriver driver;

    @BeforeAll
    public static void dowload() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        logger.info("Driver installed");
    }

    @AfterEach
    public void setDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void w3layouts() throws InterruptedException {

        // Перейти на w3layouts
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        logger.info("w3layouts open");

        // Нажать на любую картинку
        driver.findElement(By.xpath("//li[@data-id='id-1']")).click();

        // Проверить что картинка открылась в модальном окне
        boolean valueModalPage = driver.findElement(By.cssSelector("div.pp_hoverContainer")).isDisplayed();
        logger.info("Image opened in model window");
    }
}
