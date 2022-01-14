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
Exercise 1: TestNG DataProvider
Step 1: Vào trang: http://demo.guru99.com/v4/
Step 2: Nếu
1.	Method name là: TC_02_LoginSuccessfully: lấy object dataprovider = { "mngr181082", "enAdUha" }, { "mngr181083", "UpYpEzU" }, { "mngr181086", "yzegUga" }
2.	Nếu method name là các tên khác: lấy object dataprovider = { "ronaldo", "123456" }, { "messi", "123456" }
Step 3:
Trường hợp số 1(step 2):
•	Input username
•	Input password
•	Click button submit để đăng nhập
•	Verify message: Welcome To Manager's Page of Guru99 Bank được hiển thị
Trường hợp số 2(step 2)
•	Input username
•	Input password
•	Click button submit để đăng nhập
•	Verify text trong alert là: User or Password is not valid
•	Accept alert
 */

public class Exercise01 extends Common {

    @BeforeClass
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

    @Test(dataProvider = "account")
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

    @Test(dataProvider = "account")
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
