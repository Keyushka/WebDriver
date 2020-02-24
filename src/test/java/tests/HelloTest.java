package test.java.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class HelloTest extends BaseTest{


    @Test
    public void checkStyle() {
        driver.get("http://iteaua-develop.demo.gns-it.com/");
        /*WebElement uaLan = driver.findElement(By.xpath("(//a[text() = 'UA'])[1]"));
        WebElement ruLan = driver.findElement(By.xpath("(//a[text() = 'RU'])[1]"));
        WebElement enLan = driver.findElement(By.xpath("(//a[text() = 'EN'])[1]"));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(uaLan),
                ExpectedConditions.visibilityOf(ruLan),
                ExpectedConditions.visibilityOf(enLan)
        ));*/ //  проверяем визибилити одного из этих эелементов - точнее выбор языка страницы
        //Thread.sleep(7000); //задержка пеед закрытием

        // By callBack2 = By.cssSelector(".callback-btn");
        // By callBack3 = By.xpath("//a[@class='callback-btn']");
        By callBack = By.className("callback-btn"); //деклаирует описание элемента
        WebElement callBackEl = driver.findElement(callBack); //ищет элемент по декларации

        //waitForPresence.until(ExpectedConditions.presenceOfElementLocated(callBack)); // ждем до того как появится элемент callBack
        wait.until(ExpectedConditions.elementToBeClickable(callBack)); // ждем до того как появится кликабельный элемент callBack

        callBackEl.click();

        //Thread.sleep(5000);
        By callBackMsg = By.xpath("//*[@class='b-header-contacte__detail']");
        // By callBackMsg = By.xpath("//*[@class='b-header-contacte__detail' and contains(text(), 'менеджеры')]");
        // пытаемся найти по слову "менеджеры" через xpath
        WebElement callBackMsgEl = driver.findElement(callBackMsg);
        //waitForPresence.until(ExpectedConditions.presenceOfElementLocated(callBackMsg));

        /*System.out.println(callBackMsgEl.getAttribute("outerHTML"));
        Thread.sleep(2000); // возвращаем сам тег с внутренней частью и после задержки проверяем что в нем находится
        System.out.println(callBackMsgEl.getAttribute("outerHTML"));*/
        //waitForPresence.until(ExpectedConditions.visibilityOf(callBackMsgEl)); // элемент виден на странице
        String msg =  callBackMsgEl.getText(); //берем текст элемента, метод возвращает стрингу
        // поэтому присваиваем  метод переменной
        System.out.println(msg);

        WebElement submitBtn = driver.findElement(By.xpath("//div[@class='b-header-contacte-phone-block']//input[@type='submit']"));
        //waitForPresence.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
        //Thread.sleep(500);

        WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        wait.until(ExpectedConditions.attributeContains(nameInput, "style", "border-color: red;"));
        String actualStyle = nameInput.getAttribute("style");
        // находим элемент, вытягиваем любой атрибут, а именно -  style, он должен быть RED
        /*System.out.println(style); // выводим в консоль border-color: red

        By exitBtn = By.xpath("(//*[@class='b-header-contacte__close-btn'])[1]");
        WebElement exitBtnEl = driver.findElement(exitBtn);
        exitBtnEl.click(); // закрываем элемент формы - крестик
        Thread.sleep(2000); // добавляет - throws InterruptedException*/

        String expectedValue = "border-color: red;";
        assertEquals(expectedValue, actualStyle);

    }

}
