package test.java.tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import test.java.utils.Screenshot;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        // гарантировано инициализируем драйвер перед каждым методом
        context.setAttribute("WebDriver", driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //  неявное ожидание у этого драйвера будет 15 секунд
        wait = new WebDriverWait(driver, 30); // создали вейтер - явное ожидание
    }

    @AfterMethod
    public void tearDown() {
        saveScreenshot();
        driver.quit();
    }

    @Attachment(value = "screen", type = "image/png")
    private  byte[] saveScreenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
