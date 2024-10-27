package biz.svyatoslav;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalculatorTest {
    @Test
    // Проверка заголовка
    public void test0() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        String textHeaderXpath = "/html/body/table/tbody/tr[1]/td";
        By textHeaderBy = By.xpath(textHeaderXpath);
        WebElement textHeaderWebElement = driver.findElement(textHeaderBy);
        String actual = textHeaderWebElement.getText();

        Assertions.assertTrue(actual.contains("Расчёт веса"), "Должно было быть: Расчёт веса");
    }

    @Test
    public void test1() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("John");
        loginPage.clickButtonCalculate();
    }

    @Test
    public void test2() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("John");
        loginPage.sendKeysInputHeight("185");
        loginPage.sendKeysInputWeight("70");
        loginPage.clickButtonCalculate();

    }

    @Test
    public void test3() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("John");
        loginPage.sendKeysInputHeight("185");
        loginPage.sendKeysInputWeight("70");
        loginPage.selectFemaleGender();

        loginPage.clickButtonCalculate();

    }

    @Test
    //Не указаны имя, вес, рост и пол
    public void test4() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.INVALID_NAME_AND_WEIGH_AND_HEIGHT_AND_GENDER, loginPage.getErrorMessageText());
    }

    @Test
    //Не указано имя, рост и пол
    public void test5() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputWeight("65,5");
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.INVALID_NAME_AND_HEIGHT_AND_GENDER, loginPage.getErrorMessageText());
    }

    @Test
    //Не указан вес и пол
    public void test6() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Ivan Ivanov");
        loginPage.sendKeysInputHeight("184");
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.INVALID_WEIGHT_AND_GENDER, loginPage.getErrorMessageText());
    }

    @Test
    // Не указан пол
    public void test7() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Yuliya");
        loginPage.sendKeysInputHeight("165");
        loginPage.sendKeysInputWeight("70");
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.INVALID_GENDER, loginPage.getErrorMessageText());
    }

    @Test
    // Рост за пределами диапазона
    public void test8() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Yuliya");
        loginPage.sendKeysInputHeight("2");
        loginPage.sendKeysInputWeight("70");
        loginPage.selectFemaleGender();
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.INVALID_HEIGHT, loginPage.getErrorMessageText());
    }

    @Test
    // Рост и вес за пределами диапазона
    public void test9() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Yuliya");
        loginPage.sendKeysInputHeight("1800");
        loginPage.sendKeysInputWeight("700");
        loginPage.selectFemaleGender();
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.INVALID_HEIGHT_AND_INVALID_WEIGHT, loginPage.getErrorMessageText());
    }

    @Test
    // Результат: Идеальная масса тела
    public void test10() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Yuliya");
        loginPage.sendKeysInputHeight("180");
        loginPage.sendKeysInputWeight("70");
        loginPage.selectFemaleGender();
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.RESULT_IDEAL, loginPage.getResultMessageText());
    }

    @Test
    //Результат: Умеренный избыток массы тела
    public void test11() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Piotr");
        loginPage.sendKeysInputHeight("180");
        loginPage.sendKeysInputWeight("100");
        loginPage.selectFemaleGender();
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.RESULT_MIDDLE_OVERWEIGHT, loginPage.getResultMessageText());
    }

    @Test
    //Результат: Значительный избыток массы тела, тучность
    public void test12() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Piotr");
        loginPage.sendKeysInputHeight("180");
        loginPage.sendKeysInputWeight("150");
        loginPage.selectFemaleGender();
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.RESULT_MAX_OVERWEIGHT, loginPage.getResultMessageText());
    }
    @Test
    //Результат: Значительный избыток массы тела, тучность
    public void test13() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.sendKeysInputName("Piotr");
        loginPage.sendKeysInputHeight("180");
        loginPage.sendKeysInputWeight("50");
        loginPage.selectFemaleGender();
        loginPage.clickButtonCalculate();

        Assertions.assertEquals(LoginPageMessages.RESULT_UNDERWEIGHT, loginPage.getResultMessageText());
    }

    @Test
    //Проверка наличия элемента menu
    public void test14() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        String textMenuXpath = "/html/body/table/tbody/tr[2]/td[1]";
        By textMenuBy = By.xpath(textMenuXpath);
        WebElement textMenuWebElement = driver.findElement(textMenuBy);
        String actual = textMenuWebElement.getText();

        Assertions.assertTrue(actual.contains("menu"), "Должно было быть: menu");
    }

    @Test
    //Проверка наличия элемента banners
    public void test15() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        String textBannersXpath = "/html/body/table/tbody/tr[2]/td[3]";
        By textBannersBy = By.xpath(textBannersXpath);
        WebElement textBannersWebElement = driver.findElement(textBannersBy);
        String actual = textBannersWebElement.getText();

        Assertions.assertTrue(actual.contains("banners"), "Должно было быть: banners");
    }
    @Test
    //Проверка наличия текста в футере
    public void test16() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://svyatoslav.biz/testlab/wt/index.php");
        String textFooterXpath = "/html/body/table/tbody/tr[3]/td";
        By textFooterBy = By.xpath(textFooterXpath);
        WebElement textFooterWebElement = driver.findElement(textFooterBy);
        String actual = textFooterWebElement.getText();

        Assertions.assertTrue(actual.contains("fhlrhwelrwerhwerh"), "Должно было быть: fhlrhwelrwerhwerh");
    }


}
