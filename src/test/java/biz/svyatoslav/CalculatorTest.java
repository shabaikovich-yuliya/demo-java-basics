package biz.svyatoslav;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.WatchEvent;

public class CalculatorTest {
    @Test
    public void test1(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://svyatoslav.biz/testlab/wt/index.php");
        String name = "/html/body/table/tbody/tr[2]/td[2]/form/table/tbody/tr[2]/td[2]/input";
        By nameA = By.xpath(name);
        WebElement filledName = webDriver.findElement(nameA);
        filledName.sendKeys("John");
        String xpath = "/html/body/table/tbody/tr[2]/td[2]/form/table/tbody/tr[6]/td/input";
        By by = By.xpath(xpath);
        WebElement webElement = webDriver.findElement(by);
        webElement.click();
    }
}
