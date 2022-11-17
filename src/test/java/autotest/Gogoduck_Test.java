package autotest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Gogoduck_Test {

    private Logger logger = LogManager.getLogger(Gogoduck_Test.class);
    private WebDriver driver;

    @BeforeAll
    public static void dowload() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {

        //Hedless режим
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1200");
        driver = new ChromeDriver(options);
        logger.info("Driver installed");
    }

    @AfterEach
    public void setDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void openDuckDuckGo() throws InterruptedException {

        // Перейти на duckduckgo.com
        driver.get("https://duckduckgo.com/");
        logger.info("DuckDuckGo open");

        //  В поисковую строку ввести ОТУС

        WebElement search = driver.findElement(By.cssSelector("#search_form_input_homepage"));
        search.clear();
        search.sendKeys("Отус");
        logger.info("Otus in the search bar enter");

        // Поиск
        driver.findElement(By.cssSelector("#search_button_homepage")).click();

        // Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", driver
                .findElement(By.xpath(" //*[text() = 'Онлайн‑курсы для профессионалов, дистанционное обучение современным ...']"))
                .getText());
    }
}
