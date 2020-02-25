package test.java.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.tests.PO.CallBackPage;
import test.java.tests.PO.CoursesPage;
import test.java.tests.PO.HomePage;

import java.util.List;

import static org.testng.Assert.*;

public class HW_3 extends BaseTest{
    HomePage homePage;
    CallBackPage callBackPage;
    CoursesPage coursesPage;

    @BeforeMethod
    public void PageLoad() {
        homePage = new HomePage(driver);
        callBackPage = new CallBackPage(driver);
        coursesPage = new CoursesPage(driver);
    }

    @Test
    public void TestPositive() {
        homePage.open();
        callBackPage.open()
                .setName("Irina Kononenko")
                .setPhone("1234567890")
                .submitBtn();
        String actualThankMsg = callBackPage.getThankMsg();
        System.out.println(actualThankMsg);
        String expectedMsg = "Спасибо!\nНаш менеджер свяжется с Вами.";
        assertEquals(expectedMsg, actualThankMsg);
    }

    @Test
    public void TestNegative() {
        homePage.open();
        callBackPage.open()
                .submitBtn();
        String actualNameStyle = callBackPage.getStyleName();
        String actualPhoneStyle = callBackPage.getStylePhone();
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
                "Видеомонтаж", "Cisco", "Go development",};

        List<String> courseActual = homePage.getListCourses();
        for(String course: courseExpected) {
            boolean isContains = courseActual.contains(course);
            assertTrue(isContains, String.format("Expected language '%s' to be present on the page", course));
        }
    }

    @Test
    public void DaytimeCourses() {
        homePage.open()
                .openMenuDaytimeCourses()
                .openCoursesFromDaytime();
        String[] courseExpected = {"Microsoft", "Cisco", "UNIX / Linux", "Oracle", "ITIL",
                "Программирование", "Управление проектами", "Пользовательские курсы",
                "Vmware", "Teradata", "EC-Council"};

        List<String> courseActual = homePage.getListCourses();
        for(String course: courseExpected) {
            boolean isContains = courseActual.contains(course);
            assertTrue(isContains, String.format("Expected language '%s' to be present on the page", course));
        }

    }

    @Test (dataProvider = "providerEveningCourses")
    public void ListCoursesSetLocation(String courseName){
        homePage.open()
                .openMenuEveningCourses()
                .openCoursesFromEvening()
                .openCoursePage(courseName);
        coursesPage.clickBtnPay();
        assertTrue(coursesPage.checkInLocation("Берестейская"));
        assertFalse(coursesPage.checkInLocation("Позняки"));
        assertFalse(coursesPage.checkInLocation("ВДНХ"));
        assertFalse(coursesPage.selectedCheckbox());
    }


    @DataProvider
    public Object[][] providerEveningCourses() {
        return new Object[][]{
                {"Тестирование"},
                {"Frontend development"},
                {"JS development"},
                {"Веб-дизайн"},
                {"PHP"},
                {"Программирование под IOS"},
                {"Программирование под Android"},
                {"Java programming"},
                {"Python"},
                {"Data Science/Machine Learning"},
                {"C# /.NET development"},
                {"C++"},
                {"Game Development"},
                {"DEVOPS"},
                {"Digital Marketing"},
                {"Управление персоналом"},
                {"Управление проектами"},
                {"Менеджмент"},
                {"Кибербезопасность"},
                {"Mobile development"},
                {"Видеомонтаж"},
                {"Cisco"},
                {"Go development"}

        };
    }


}
