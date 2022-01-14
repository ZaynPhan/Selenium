package testng;

import DriverExercise.Common;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
/*
Làm lại exercise02 bằng @Paramater thay vì @DataProvider
 */

public class Exercise03 extends Common {

    @BeforeClass
    public void beforeClass(){
        loadBrowser();
        inputURL("http://demo.guru99.com/v4/");
    }

//    @DataProvider(name = "account")
//    public static Object[][] userAndPass(Method method) {
//        Object[][] result;
//        if (method.getName().equals("TC01_LoginSuccessfully")) {
//            result = new Object[][] { { "mngr380444", "bYpUryg" }, { "mngr380446", "sygUhub" } };
//        } else {
//            result = new Object[][] { { "mngr109860", "taqYsyd" }, { "mngr109861", "UzahajY" } };
//        }
//        return result;
//    }

    @Parameters({"validUsername","validPassword"})
    @Test
    public void TC01_LoginSuccessfully(String username, String password){
        inputURL("http://demo.guru99.com/v4/");

        WebElement idInput = driver.findElement(By.xpath("//input[@name='uid']"));
        waitForElement(5, "//input[@name='uid']");
        idInput.sendKeys(username);

        WebElement pwInput = driver.findElement(By.xpath("//input[@name='password']"));
        waitForElement(5, "//input[@name='password']");
        pwInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='btnLogin']"));
        waitForElement(5, "//input[@name='btnLogin']");
        loginBtn.click();

        WebElement successGreeting = driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
        waitForElement(5, "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]");
        String actualSuccessGreeting = successGreeting.getText().trim();

        Assert.assertEquals(actualSuccessGreeting,"Welcome To Manager's Page of Guru99 Bank");
    }

    @Parameters({"invalidUsername","invalidPassword"})
    @Test
    public void TC02_LoginFailed(String username, String password) {
        inputURL("http://demo.guru99.com/v4/");

        WebElement idInput = driver.findElement(By.xpath("//input[@name='uid']"));
        waitForElement(5, "//input[@name='uid']");
        idInput.sendKeys(username);

        WebElement pwInput = driver.findElement(By.xpath("//input[@name='password']"));
        waitForElement(5, "//input[@name='password']");
        pwInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='btnLogin']"));
        waitForElement(5, "//input[@name='btnLogin']");
        loginBtn.click();

        Alert loginFailAlert = driver.switchTo().alert();
        String actualAlert = loginFailAlert.getText();
        Assert.assertEquals(actualAlert, "User or Password is not valid");

        loginFailAlert.accept();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
