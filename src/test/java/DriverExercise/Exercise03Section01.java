package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
/*
Exercise 3: Tương tác với Radio button
Step 01 – Truy cập vào trang: https://demoqa.com/checkbox
Step 02 – Click vào checkbox: Home
Step 03 - Verify message hiển thị sau khi click
Step 04 – Click vào Radio Button trên Navigation bar
Step 05 – Click vào radio box Yes
Step 06 – Verify message hiển thị sau khi click là:  “You have selected Yes”
Step 07 – Click vào radio box Impressive
Step 08 – Verify message hiển thị sau khi click là:  “You have selected Impressive”
Step 09 – Click vào radio box No
Step 10 – Check btn No is disabled
 */

public class Exercise03Section01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver(); //mở browser
        driver.get("https://demoqa.com/checkbox"); //truy cập link
        driver.manage().window().maximize(); //phóng to
    }

    @Test
    public void testcase01() throws InterruptedException {
        //Find and click on Check Box on Navigation
        WebElement checkBoxNavigation = driver.findElement(By.xpath("//span[text()='Check Box']"));
        checkBoxNavigation.click();

        //Check checkbox Home is displayed?
        WebElement homeCheckBox = driver.findElement(By.xpath("//span[text()='Home']"));
        Assert.assertTrue(homeCheckBox.isDisplayed());

        homeCheckBox.click();

        //Check message below
        String actualSuccessMessage = "";

        //Tạo list webElement để get tên từng element và cộng vào chuỗi actualSuccessMessage
        List<WebElement> listSuccessElements = driver.findElements(By.xpath("//div[@id='result']/span"));
        for (WebElement webElement : listSuccessElements) {
            actualSuccessMessage += webElement.getText() + " ";
        }

        String successMessage = "You have selected : home desktop notes commands documents workspace react " +
                "angular veu office public private classified general downloads wordFile excelFile";
        Assert.assertEquals(actualSuccessMessage.trim(), successMessage);
        Thread.sleep(1500);

        //Find element Radio Box trên Navigation bar
        WebElement radioButton = driver.findElement(By.xpath("//span[text()='Radio Button']"));
        Assert.assertTrue(radioButton.isDisplayed());
        radioButton.click();

        //Check content của trang Radio
        WebElement radioButtonText = driver.findElement(By.xpath("//span[text()='Radio Button']"));

        Assert.assertEquals(radioButtonText, "Do you like the site?");

        //Check checkbox Yes is displayed
        WebElement checkboxYes = driver.findElement(By.xpath("//label[@for='yesRadio']"));
        Assert.assertTrue(checkboxYes.isDisplayed());

        checkboxYes.click();

        //Find and check following message Yes
        WebElement successMessageYes = driver.findElement(By.xpath("//p[text()='You have selected ']"));
        String messageYes = successMessageYes.getText().trim();
        Assert.assertEquals(messageYes, "You have selected");
        Thread.sleep(1500);

        //Check checkbox Impressive is displayed
        WebElement checkboxImpressive = driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        Assert.assertTrue(checkboxImpressive.isDisplayed());

        checkboxImpressive.click();

        //Find and check following message Impressive
        WebElement successMessageImpressive = driver.findElement(By.xpath("//p[text()='You have selected ']"));
        String messageImpressive = successMessageImpressive.getText().trim();
        Assert.assertEquals(messageImpressive, "You have selected");
        Thread.sleep(1500);

        //Check checkbox No is NOT enabled
        WebElement checkboxNo = driver.findElement(By.xpath("//label[@for='noRadio']"));
        Assert.assertFalse(checkboxNo.isEnabled());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int randomNumber() {
        return (int) (Math.random() * 1000000 + 1);
    }
}
