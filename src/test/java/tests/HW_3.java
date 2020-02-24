package test.java.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.tests.PO.CallBack;
import test.java.tests.PO.HomePage;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HW_3 extends BaseTest{
    HomePage homePage;
    CallBack callBack;

    @BeforeMethod
    public void PageLoad() {
        homePage = new HomePage(driver);
        callBack = new CallBack(driver);
    }

    @Test
    public void TestPositive() {
        homePage.open();
        callBack.open()
                .setName("Irina Kononenko")
                .setPhone("1234567890")
                .submitBtn();
        String actualThankMsg = callBack.getThankMsg();
        System.out.println(actualThankMsg);
        String expectedMsg = "Спасибо!\nНаш менеджер свяжется с Вами.";
        assertEquals(expectedMsg, actualThankMsg);
    }

    @Test
    public void TestNegative() {
        homePage.open();
        callBack.open()
                .submitBtn();
        String actualNameStyle = callBack.getStyleName();
        String actualPhoneStyle = callBack.getStylePhone();
        String expectedValue = "border-color: red;";
        assertEquals(expectedValue, actualNameStyle, String.format("Expected %s to be equal %s", actualNameStyle, expectedValue));
        assertEquals(expectedValue, actualPhoneStyle, String.format("Expected %s to be equal %s", actualPhoneStyle, expectedValue));
    }

    @Test
    public void EveningCourses() {
        homePage.open()
                .openMenuEveningCourses()
                .openCoursesFromEvening();

        String[] courseExpected = {"Тестирование", "Frontend development", "JS development",
                "Веб-дизайн", "PHP", "Программирование под IOS", "Программирование под Android",
                "Java programming", "Python", "Data Science/Machine Learning", "C# /.NET development",
                "C++", "Game Development", "DEVOPS", "Digital Marketing", "Управление персоналом",
                "Управление проектами", "Менеджмент", "Кибербезопасность", "Mobile development",
                "Видеомонтаж", "Cisco", "Go development"};
        List<WebElement> courseElements = driver.findElements(By.xpath("//*[@id='course']/div/div/h2"));
        wait.until(ExpectedConditions.visibilityOfAllElements(courseElements));
        List<String> courseActual = new ArrayList<String>();

        /*for(WebElement el: courseElements) {
            System.out.print("\"" + el.getText() + "\"" + ", ");
        }*/

        for(WebElement el: courseElements) {
            courseActual.add(el.getText());
        }
        for(String course: courseExpected) {
            boolean isContains = courseActual.contains(course);
            assertTrue(isContains, String.format("Expected language '%s' to be present on the page", course));
        }
    }

    @Test
    public void DaytimeCourses() throws InterruptedException {
        homePage.open()
                .openMenuDaytimeCourses()
                .openCoursesFromDaytime();

        String[] courseExpected = {"Microsoft", "Cisco", "UNIX / Linux",
                "Oracle", "ITIL", "Программирование", "Управление проектами",
                "Пользовательские курсы", "Vmware", "Teradata", "EC-Council"};
        List<WebElement> courseElements = driver.findElements(By.xpath("//*[@id='course']/div/div/h2"));
        wait.until(ExpectedConditions.visibilityOfAllElements(courseElements));
        List<String> courseActual = new ArrayList<String>();

        for(WebElement el: courseElements) {
            courseActual.add(el.getText());
        }
        for(String course: courseExpected) {
            boolean isContains = courseActual.contains(course);
            assertTrue(isContains, String.format("Expected language '%s' to be present on the page", course));
        }

    }

}
