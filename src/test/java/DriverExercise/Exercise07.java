package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class Exercise07 extends Common {
//    WebDriver driver;

    @BeforeClass
    public void beforeTestcaseAlert() {
        loadBrowser();
    }

    //Upload ảnh bằng Sendkeys
/*
Exercise 1:
Step 1: Vào trang: http://demo.guru99.com/test/upload/
Step 2: Upload file
Step 3: Check checkbox: I accept terms of service
Step 4: Click button Submit File
Step 5: Verify message “1 file has been successfully uploaded.” được hiển thị
 */
    @Test
    public void testcaseUploadFileBySendKeys01() throws InterruptedException {
        //Vào trang: http://demo.guru99.com/test/upload/
        inputURL("http://demo.guru99.com/test/upload/");

        //Upload file
        String rootPath = System.getProperty("user.dir");// khai báo root path
        String pathDataImage = "/Files/";
        String fileName = "File (177).jpg";
        String pathImage = rootPath.concat(pathDataImage).concat(fileName);

        WebElement browseBtn = driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
        waitForElement(10, "//input[@id='uploadfile_0']");
        browseBtn.sendKeys(pathImage);

        //Check checkbox: I accept terms of service
        WebElement termsCheckbox = driver.findElement(By.xpath("//input[@id='terms']"));
        waitForElement(10, "//input[@id='terms']");
        termsCheckbox.click();

        //Click button Submit File
        WebElement submitFileBtn = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        waitForElement(10, "//button[@id='submitbutton']");
        submitFileBtn.click();

        //Verify message “1 file has been successfully uploaded.” được hiển thị
        WebElement successMessage = driver.findElement(By.xpath("//h3[@id='res']"));
        waitForElement(10, "//h3[@id='res']");
        String actualSuccessMessage = successMessage.getText();
        Assert.assertEquals(actualSuccessMessage.trim(), "1 file\nhas been successfully uploaded.");
    }

    /*
    Exercise 2:
    Step 1: Vào trang: http://the-internet.herokuapp.com/upload
    Step 2: Upload file
    Step 3: Click button: Upload
    Step 4: Verify message “File Uploaded!” và file bên dưới vừa upload được hiển thị
     */
    @Test
    public void testcaseUploadFileBySendKeys02() {
        //Vào trang: http://the-internet.herokuapp.com/upload
        inputURL("http://the-internet.herokuapp.com/upload");

        //Upload file
        String rootPath = System.getProperty("user.dir");
        String pathDataImage = "/Files/";
        String fileName = "File (177).jpg";
        String pathImage = rootPath.concat(pathDataImage).concat(fileName);

        WebElement browseBtn = driver.findElement(By.xpath("//input[@id='file-upload']"));
        browseBtn.sendKeys(pathImage);

        //Click button: Upload
        WebElement uploadBtn = driver.findElement(By.xpath("//input[@id='file-submit']"));
        uploadBtn.click();

        //Verify message “File Uploaded!”
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='example']/h3"));
        String actualSuccessMessage = successMessage.getText();
        Assert.assertEquals(actualSuccessMessage.trim(), "File Uploaded!");

        //Verify file bên dưới vừa upload được hiển thị
        WebElement fileNameUploadded = driver.findElement(By.xpath("//div[@id='uploaded-files']"));
        String actualFileNameUploadded = fileNameUploadded.getText();
        Assert.assertEquals(actualFileNameUploadded.trim(), fileName);
    }

    //Upload ảnh bằng Robot
/*
Exercise 1:
Step 1: Vào trang: http://demo.guru99.com/test/upload/
Step 2: Upload file
Step 3: Check checkbox: I accept terms of service
Step 4: Click button Submit File
Step 5: Verify message “1 file has been successfully uploaded.” được hiển thị
 */
    @Test
    public void testcaseRobot01() throws InterruptedException, AWTException {
        inputURL("http://demo.guru99.com/test/upload/");

        //Click Browse button
        WebElement browseBtn = driver.findElement(By.xpath("//input[@name='uploadfile_0']"));
        Actions action = new Actions(driver);
        action.click(browseBtn).perform();

        //Set Clipboard Data
//        String rootPath = System.getProperty("user.dir");
//        String pathDataImage = "/Files/";
//        String fileName = "File (177).jpg";
//        String pathImage = rootPath.concat("").concat(fileName);
        StringSelection absoluteFilePath = new StringSelection("C:\\Users\\minhphn\\Desktop\\File(177).jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(absoluteFilePath, null);

        Robot robot = new Robot();

        //Paste absolute File path to pop-up
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //Check checkbox: I accept terms of service
        WebElement termsCheckbox = driver.findElement(By.xpath("//input[@id='terms']"));
        termsCheckbox.click();

        //Click button Submit File
        WebElement submitFileBtn = driver.findElement(By.xpath("//button[@id='submitbutton']"));
        waitForElement(10, "//button[@id='submitbutton']");
        submitFileBtn.click();

        //Verify message “1 file has been successfully uploaded.” được hiển thị
        WebElement successMessage = driver.findElement(By.xpath("//h3[@id='res']"));
        waitForElement(10, "//h3[@id='res']");
        String actualSuccessMessage = successMessage.getText();
        Assert.assertEquals(actualSuccessMessage.trim(), "1 file\nhas been successfully uploaded.");
    }

    public void waitForElement(int seconds, String waitConditionLocator) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitConditionLocator)));
    }

    @AfterClass
    public void afterTestcase() {
        driver.quit();
    }
}
