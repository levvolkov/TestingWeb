package ru.netology.TestingWeb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class OrderCardTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestPositive() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Павел Пестель");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79217931826");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestPositivResultDoubleSurname() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Муравьев-Апостол Сергей");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79217951843");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestPositivResultDoubleName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Лягушкин Иван-Царевичь");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79770001155");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected, actual);
    }
}



