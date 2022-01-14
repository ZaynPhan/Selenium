package testng;

import DriverExercise.Common;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
/*
Exercise 2: TestNG Group:
Sửa lại bài tập Exercise 01_ Button Radio Checkbox Combobox thành các tên group như sau:
Exercise 1, Exercise 2, Exercise 3: tên group là “LoginWithInvalidAccount”
Exercise 4: tên group là “LoginWithInvalidAccount”
Chỉ chạy testcase với group có tên là: “LoginWithInvalidAccount”
 */

public class Exercise02 extends Common {

    @BeforeClass(groups = {"loginSuccess","loginFail"})
    public void beforeClass(){
        loadBrowser();
        inputURL("http://demo.guru99.com/v4/");
    }

    @DataProvider(name = "account")
    public static Object[][] userAndPass(Method method) {
        Object[][] result;
        if (method.getName().equals("TC01_LoginSuccessfully")) {
            result = new Object[][] { { "mngr380444", "bYpUryg" }, { "mngr380446", "sygUhub" } };
        } else {
            result = new Object[][] { { "mngr109860", "taqYsyd" }, { "mngr109861", "UzahajY" } };
        }
        return result;
    }

    @Test(dataProvider = "account", groups = "loginSuccess")
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

    @Test(dataProvider = "account", groups = "loginFail")
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


    @AfterClass(groups = {"loginSuccess","loginFail"})
    public void afterClass(){
        driver.quit();
    }
}
