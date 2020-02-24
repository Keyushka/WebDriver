package test.java.tests.PO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CallBackPage extends BasePage{
    Logger logger = LogManager.getLogger(CallBackPage.class);

    public CallBackPage(WebDriver driver){
        super(driver);
    }

    public CallBackPage open(){
        WebElement callBack = driver.findElement(By.className("callback-btn"));
        wait.until(ExpectedConditions.elementToBeClickable(callBack));
        callBack.click();
        return this;
    }

    public CallBackPage setName(String name){
        WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        nameInput.sendKeys(name);
        return this;
    }

    public CallBackPage setPhone(String phone){
        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='phone']"));
        wait.until(ExpectedConditions.elementToBeClickable(phoneInput));
        phoneInput.sendKeys(phone);
        return this;
    }

    public CallBackPage submitBtn(){
        WebElement submitBtn = driver.findElement(By.xpath("//div[@class='b-header-contacte-phone-block']//input[@type='submit']"));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
        return this;
    }

    public String getThankMsg(){
        WebElement callBackThankMsg = driver.findElement(By.cssSelector(".b-header-contacte-phone-thank > p"));
        wait.until(ExpectedConditions.visibilityOf(callBackThankMsg));
        return callBackThankMsg.getText();
    }

    public String getStyleName(){
        WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        wait.until(ExpectedConditions.attributeContains(nameInput, "style", "border-color: red;"));
        return nameInput.getAttribute("style");
    }

    public String getStylePhone(){
        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='phone']"));
        wait.until(ExpectedConditions.attributeContains(phoneInput, "style", "border-color: red;"));
        return phoneInput.getAttribute("style");
    }
}
