package test.java.tests.PO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePage{
    Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        super(driver); // используется родительский драйвер
    }

    public HomePage open(){
        logger.info("Home page is opened");
        logger.debug("Home page is opened  jkfgjkfgdf gdhdgjn kn k nk gerg");
        driver.get("http://iteaua-develop.demo.gns-it.com/");
        WebElement spinner = driver.findElement(By.id("preload-it"));
        wait.until(ExpectedConditions.visibilityOf(spinner));
        wait.until(ExpectedConditions.invisibilityOf(spinner));
        return this; // return HomePage;
    }

    public HomePage selectLanguage(String lang){
        logger.info("Language is checked to " + lang);
        WebElement uaLang = driver.findElement(By.xpath("(//a[@hreflang='"+ lang +"'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(uaLang));
        uaLang.click();
        return this;
    }

    public  HomePage openAbout(){
        WebElement aboutBtn = driver.findElement(By.xpath("//a[(contains(@href, 'about_itea')) and @class='parent']"));
        wait.until(ExpectedConditions.visibilityOf(aboutBtn));
        aboutBtn.click();
        return this;
    }

    public HomePage openVacancies(){
        WebElement vacancies = driver.findElement(By.xpath("//li[@id='menu-item-15362']/a"));
        wait.until(ExpectedConditions.elementToBeClickable(vacancies));
        vacancies.click();
        return this;
    }

    public HomePage openMenuEveningCourses(){
        WebElement eveningCourses = driver.findElement(By.xpath("//*[@id='menu-item-411']/a"));
        wait.until(ExpectedConditions.visibilityOf(eveningCourses));
        eveningCourses.click();
        return this;
    }

    public HomePage openCoursesFromEvening(){
        WebElement CoursesFromEvening = driver.findElement(By.xpath("//*[@id='menu-item-7880']/a"));
        wait.until(ExpectedConditions.visibilityOf(CoursesFromEvening));
        CoursesFromEvening.click();
        return this;
    }

    public HomePage openMenuDaytimeCourses(){
        WebElement eveningCourses = driver.findElement(By.xpath("//*[@id='menu-item-7901']/a"));
        wait.until(ExpectedConditions.visibilityOf(eveningCourses));
        eveningCourses.click();
        return this;
    }

    public HomePage openCoursesFromDaytime(){
        WebElement CoursesFromEvening = driver.findElement(By.xpath("//*[@id='menu-item-412']/a"));
        wait.until(ExpectedConditions.visibilityOf(CoursesFromEvening));
        CoursesFromEvening.click();
        return this;
    }

    public List<String> getListCourses() {
        List<WebElement> courseElements = driver.findElements(By.xpath("//*[@id='course']/div/div/h2"));
        wait.until(ExpectedConditions.visibilityOfAllElements(courseElements));
        List<String> courseActual = new ArrayList<String>();
        // выводим в консоль список всех эелемнтов - названия курсов (текст)
        /*for(WebElement el: courseElements) {
            System.out.print("\"" + el.getText() + "\"" + ", ");
        }*/
        for(WebElement el: courseElements) {
            courseActual.add(el.getText());
        }
        return courseActual;
    }
}
