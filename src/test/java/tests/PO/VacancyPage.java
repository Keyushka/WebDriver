package test.java.tests.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VacancyPage {
    WebDriver driver;
    WebDriverWait wait;

    public VacancyPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 30);
    }

    public VacancyPage open(){
        driver.get("http://iteaua-develop.demo.gns-it.com/uk/vacancies/");
        return this; // return VacancyPage;
    }

    public VacancyPage selectVacancy(String vacancy){
        WebElement uxVacancy = driver.findElement(By.xpath("//a//h2[text()='" +vacancy + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(uxVacancy));
        uxVacancy.click();
        return this;
    }

    public VacancyPage setName(String name){
        WebElement nameInput = driver.findElement(By.id("names"));
        wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        nameInput.sendKeys(name);
        return this;
    }

    public VacancyPage setEmail(String email){
        WebElement emailInput = driver.findElement(By.id("email"));
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailInput.sendKeys(email);
        return this;
    }

    public VacancyPage submit(){
        WebElement submitBtn = driver.findElement(By.xpath("//div[@class='submit-btn']/input[@name='ok']"));
        submitBtn.click();
        return this;
    }

    public String getPhoneErrorMsg(){
        WebElement phoneMsg = driver.findElement(By.xpath("(//label[@for='telephone']/span)[2]"));
        wait.until(ExpectedConditions.visibilityOf(phoneMsg));
        return phoneMsg.getText();
    }
}
