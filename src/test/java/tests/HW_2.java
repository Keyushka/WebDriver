package test.java.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HW_2 {
    WebDriver driver;
    WebDriverWait waitForPresence;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        waitForPresence = new WebDriverWait(driver, 15);
        driver.get("http://iteaua-develop.demo.gns-it.com/");

        WebElement callBack = driver.findElement(By.className("callback-btn"));
        waitForPresence.until(ExpectedConditions.elementToBeClickable(callBack));
        callBack.click();
    }

    @Test
    public void TestPositive() {
        WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        waitForPresence.until(ExpectedConditions.elementToBeClickable(nameInput));
        nameInput.sendKeys("Irina Kononenko");

        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='phone']"));
        waitForPresence.until(ExpectedConditions.elementToBeClickable(phoneInput));
        phoneInput.sendKeys("1234567890");

        WebElement submitBtn = driver.findElement(By.xpath("//div[@class='b-header-contacte-phone-block']//input[@type='submit']"));
        waitForPresence.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();

        WebElement callBackThankMsg = driver.findElement(By.cssSelector(".b-header-contacte-phone-thank > p"));
        waitForPresence.until(ExpectedConditions.visibilityOf(callBackThankMsg));
        String actualThankMsg =  callBackThankMsg.getText();
        System.out.println(actualThankMsg);

        String expectedMsg = "Спасибо!\nНаш менеджер свяжется с Вами.";
        assertEquals(expectedMsg, actualThankMsg);
    }

    @Test
    public void TestNegative() {
        WebElement submitBtn = driver.findElement(By.xpath("//div[@class='b-header-contacte-phone-block']//input[@type='submit']"));
        waitForPresence.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();

        WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        waitForPresence.until(ExpectedConditions.attributeContains(nameInput, "style", "border-color: red;"));
        String actualNameStyle = nameInput.getAttribute("style");

        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='phone']"));
        waitForPresence.until(ExpectedConditions.attributeContains(phoneInput, "style", "border-color: red;"));
        String actualPhoneStyle = phoneInput.getAttribute("style");

        String expectedValue = "border-color: red;";
        assertEquals(expectedValue, actualNameStyle, String.format("Expected %s to be equal %s", actualNameStyle, expectedValue));
        assertEquals(expectedValue, actualPhoneStyle, String.format("Expected %s to be equal %s", actualPhoneStyle, expectedValue));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
