package autotest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Otus_Test {

    private Logger logger = LogManager.getLogger(Otus_Test.class);
    private WebDriver driver;

    @BeforeAll
    public static void dowload() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Driver installed");
    }

    @AfterEach
    public void setDown() {
        if (driver != null)
            driver.quit();
    }

    private void auth() throws InterruptedException {
        String login = "mxmrbkv95@gmail.com";
        String password = "AutoTestOtus2022!";
        driver.findElement(By.cssSelector("button[data-modal-id=new-log-reg]")).click();
        driver.findElement(By.xpath("//input[@type='text' and @placeholder='Электронная почта']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@placeholder='Введите пароль']")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
        logger.info("Authorization was successful");
    }

    @Test
    public void otus() throws InterruptedException {

       // Перейти на Otus
        driver.get("https://otus.ru");
        logger.info("Otus open");

        //Авторизоваться на сайте
        auth();

        // Вывести в лог все Cookies
        for (Cookie cookie : driver.manage().getCookies()) {
            logger.info(cookie.getName() + " " + cookie.getValue());
        }
    }
}
