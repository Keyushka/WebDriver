package test.java.tests.PO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CoursesPage extends BasePage {

    public Logger logger = LogManager.getLogger(CoursesPage.class);

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public CoursesPage clickBtnPay() {
        WebElement buttonPay = driver.findElement(By.xpath("//button[@name='roadFull_payOnce']"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonPay));
        buttonPay.click();
        return this;
    }

    public boolean checkInLocation(String location) {
        By locationEl = By.xpath("//input[@id=//div[contains(text(), '" + location + "')]/../@for]");
        WebElement courseLocation = driver.findElement(locationEl);
        wait.until(ExpectedConditions.presenceOfElementLocated(locationEl));
        logger.info("Checked location '"+ location +"'");
        return courseLocation.isSelected();
    }

    public boolean selectedCheckbox(){
        By checkbox = By.xpath("//*[@id='privacy-policy']/label/span");
        WebElement privacyPolicy = driver.findElement(checkbox);
        wait.until(ExpectedConditions.presenceOfElementLocated(checkbox));
        logger.info("Checked checkbox from Privacy Policy");
        return privacyPolicy.isSelected();
    }

    public String fillInInfo(String name, String email, String phone){
        logger.info("Course entry form is opened");
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("phone")).sendKeys(phone);
        logger.debug("All form inputs are successfully filled");
        WebElement checkbox = driver.findElement(By.xpath("//div[@class='privacy-policy__wrapper']//span"));
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        checkbox.click();
        WebElement submitBtn = driver.findElement(By.xpath("//input[@class='submit ']"));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
        logger.debug("Sign up button has been clicked");
        WebElement thanksBlock = driver.findElement(By.xpath("//div[@class=\"container regDiv\"]//h1"));
        wait.until(ExpectedConditions.visibilityOf(thanksBlock));
        return thanksBlock.getText();
    }
}
