package test.java.tests.PO;

import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import test.java.utils.PropertyLoader;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends BasePage{
    Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        super(driver); // используется родительский драйвер
    }

    @Step("Open ome page")
    public HomePage open(){
        logger.info("Home page is opened");
        driver.get(PropertyLoader.getProperty("url"));
        WebElement spinner = driver.findElement(By.id("preload-it"));
        wait.until(ExpectedConditions.visibilityOf(spinner));
        wait.until(ExpectedConditions.invisibilityOf(spinner));
        logger.debug("Home page is opened and spinner is loaded");
        return this; // return HomePage;
    }

    @Step("Select language {lang}")
    public HomePage selectLanguage(String lang){
        logger.info("Language is checked to " + lang);
        WebElement uaLang = driver.findElement(By.xpath("(//a[@hreflang='"+ lang +"'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(uaLang));
        uaLang.click();
        return this;
    }

    @Step("Open page About")
    public  HomePage openAbout(){
        WebElement aboutBtn = driver.findElement(By.xpath("//a[(contains(@href, 'about_itea')) and @class='parent']"));
        wait.until(ExpectedConditions.visibilityOf(aboutBtn));
        aboutBtn.click();
        logger.info("About page is opened");
        return this;
    }

    @Step("Open page Vacancy")
    public HomePage openVacancies(){
        WebElement vacancies = driver.findElement(By.xpath("//li[@id='menu-item-15362']/a"));
        wait.until(ExpectedConditions.elementToBeClickable(vacancies));
        vacancies.click();
        logger.info("Vacancy page is opened");
        return this;
    }

    @Step("Click on menu from Evening Courses")
    public HomePage openMenuEveningCourses(){
        WebElement eveningCourses = driver.findElement(By.xpath("//*[@id='menu-item-411']/a"));
        wait.until(ExpectedConditions.visibilityOf(eveningCourses));
        eveningCourses.click();
        logger.debug("Clicking on the evening course menu");
        return this;
    }

    @Step("Open courses from Evening Courses")
    public HomePage openCoursesFromEvening(){
        WebElement CoursesFromEvening = driver.findElement(By.xpath("//*[@id='menu-item-7880']/a"));
        wait.until(ExpectedConditions.visibilityOf(CoursesFromEvening));
        CoursesFromEvening.click();
        logger.info("Evening courses is opened");
        return this;
    }

    public HomePage openMenuDaytimeCourses(){
        WebElement eveningCourses = driver.findElement(By.xpath("//*[@id='menu-item-7901']/a"));
        wait.until(ExpectedConditions.visibilityOf(eveningCourses));
        eveningCourses.click();
        logger.debug("Clicking on the daytime course menu");
        return this;
    }

    public HomePage openCoursesFromDaytime(){
        WebElement CoursesFromEvening = driver.findElement(By.xpath("//*[@id='menu-item-412']/a"));
        wait.until(ExpectedConditions.visibilityOf(CoursesFromEvening));
        CoursesFromEvening.click();
        logger.info("Daytime courses is opened");
        return this;
    }

    public List<String> getListCourses() {
        List<WebElement> courseElements = driver.findElements(By.xpath("//*[@id='course']/div/div/h2"));
        logger.debug("Expecting the visibility of the list of course items");
        wait.until(ExpectedConditions.visibilityOfAllElements(courseElements));
        List<String> courseActual = new ArrayList<String>();
        // выводим в консоль список всех эелемнтов - названия курсов (текст)
        /*for(WebElement el: courseElements) {
            System.out.print("\"" + el.getText() + "\"" + ", ");
        }*/
        for(WebElement el: courseElements) {
            courseActual.add(el.getText());
        }
        logger.info("Return the text of elements from the list of courses");
        return courseActual;
    }

    public HomePage openCoursePage(String courseName) {
            WebElement eveningCourses = driver.findElement(By.xpath("//h2[contains(text(), '" + courseName + "')]/.."));
            wait.until(ExpectedConditions.elementToBeClickable(eveningCourses));
            eveningCourses.click();
            logger.info("Page of course - '" + courseName + "' is opened");
            return this;
        }

}
