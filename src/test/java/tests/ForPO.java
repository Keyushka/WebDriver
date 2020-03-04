package test.java.tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.tests.PO.HomePage;
import test.java.tests.PO.VacancyPage;
import test.java.utils.RetryAnalyzer;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Epic("Cart")
public class ForPO extends BaseTest{
    HomePage homePage;
    VacancyPage vacancyPage;

    @BeforeMethod
    public void PageLoad() {
        homePage = new HomePage(driver);
        vacancyPage = new VacancyPage(driver);
    }

    @Link("https://www.google.com")
    @TmsLink("B-1")
    @Issues({
            @Issue("AAA-1"),
            @Issue("AAA-134")
    })
    @Story("Add to cart")
    @Feature("Add t-short")
    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void anyTest() {
        homePage
                .open()
                .selectLanguage("uk")
                .openAbout()
                .openVacancies();
        vacancyPage
                .selectVacancy("Викладач UX")
                .setName("Vova")
                .setEmail("email@email.com")
                .submit();
        String actualMsg = vacancyPage.getPhoneErrorMsg();
        String expectedMsg = "Поле не має бути пустим";
        //assertTrue(false); // для проверки повторного запуска теста
        assertEquals(actualMsg, expectedMsg);
    }

    @Story("Add to cart")
    @Feature("Delete t-short")
    @Test
    public void checkLang(){
        /*int arr[] = {2,4,6,8,1,1};
        List<String> a = new ArrayList<String>();
        List<Integer> a = new ArrayList<Integer>();*/
        String[] langExpected = {"RU", "UA", "EN"};

        homePage.open();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//a[text() = 'UA'])[1]"))));
        List<WebElement> langElements = driver.findElements(By.xpath("(//ul[@class='lang'])[1]//a"));
        List<String> langActual = new ArrayList<String>(); // обьявили список стринговых жлементов для выдержки текста

        /*list.get(0).getText();
        System.out.println(list.get(0).getText());
        System.out.println(list.get(1).getText());
        System.out.println(list.get(2).getText());
        list.get(1).getText();
        list.get(2).getText();
        list.size(); // итерироваться вместо .langth()*/
        /*for(WebElement el: list) {
            System.out.print("\"" + el.getText() + "\"" + ", ");
        }*/
        for(WebElement el: langElements) {
            langActual.add(el.getText()); // в каждый langActual записываем только текст элемента в списке
        }
        for(String lang: langExpected) {
            boolean isContains = langActual.contains(lang);
            //если элемента из списка langActual содержит langExpected ({"RU", "UA", "EN"}), тобудет значение True
            assertTrue(isContains, String.format("Expected language '%s' to be present on the page", lang));
            // если True, то провека проходит
        }

    }



}