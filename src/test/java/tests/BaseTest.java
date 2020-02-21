package test.java.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        // гарантировано инициализируем драйвер перед каждым методом
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //  неявное ожидание у этого драйвера будет 15 секунд
        wait = new WebDriverWait(driver, 15); // создали вейтер - явное ожидание
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
