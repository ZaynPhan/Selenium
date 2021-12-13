package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/*
Exercise 3: Tương tác với Radio button
Step 01 – Truy cập vào trang: https://www.seleniumeasy.com/test/basic-radiobutton-demo.html
Step 02 – Click vào button: “Get Checked value”
Step 03 – Verify message hiển thị sau khi click là:  “Radio button is Not checked”
Step 04 – Click vào radio button: “Male”
Step 05 – Verify message hiển thị sau khi click là:  “Radio button 'Male' is checked”
Step 06 – Click vào radio button: “Female”
Step 07 – Verify message hiển thị sau khi click là:  “Radio button 'Female' is checked”
 */

public class Exercise03 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver(); //mở browser
        driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html"); //truy cập link
        driver.manage().window().maximize(); //phóng to
    }

    @Test
    public void testcase01() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int randomNumber() {
        return (int) (Math.random() * 1000000 + 1);
    }
}
