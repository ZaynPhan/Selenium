package DriverExercise;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise06 {
    WebDriver driver;

    @BeforeClass
    public void beforeTestcaseAlert() {
        loadBrowser();
    }
    /*
    Exercise 01: Tương tác với Alert thông thường
    Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/javascript_alerts
    Step 02 - Click vào button: Click for JS Alert
    Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
    Step 04 - Accept alert và verify message hiển thị tại Result là:  You clicked an alert successfully
        */
    @Test
    public void testcaseAlert01(){
        //Input URL
        inputURL("http://the-internet.herokuapp.com/javascript_alerts");

        //Click vào button: Click for JS Alert
        WebElement jsAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsAlertBtn.click();

        //Verify message hiển thị trong alert là: I am a JS Alert
        Alert jsAlert = driver.switchTo().alert();
        String actualAlert = jsAlert.getText();
        Assert.assertEquals(actualAlert, "I am a JS Alert");

        //Accept alert và verify message hiển thị tại Result là:  You clicked an alert successfully
        jsAlert.accept();
        WebElement successMessage = driver.findElement(By.xpath("//p[text()='You successfully clicked an alert']"));
        String actualSuccessMessage = successMessage.getText();
        Assert.assertEquals(actualSuccessMessage.trim(), "You successfully clicked an alert");
    }
/*
Exercise 02: Tương tác với Alert Confirm
Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/javascript_alerts
Step 02 - Click vào button: Click for JS Confirm
Step 03 - Verify message hiển thị trong alert là: I am a JS Confirm
Step 04 - Cancel alert và verify message hiển thị tại Result là:  You clicked: Cancel
 */
    @Test
    public void testcaseAlert02(){
        inputURL("http://the-internet.herokuapp.com/javascript_alerts");

        //Click vào button: Click for JS Confirm
        WebElement jsAlertConfirmBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        jsAlertConfirmBtn.click();

        //Verify message hiển thị trong alert là: I am a JS Confirm
        Alert jsAlertConfirm = driver.switchTo().alert();
        String actualAlertConfirm = jsAlertConfirm.getText();
        Assert.assertEquals(actualAlertConfirm.trim(), "I am a JS Confirm");

        //Cancel alert và verify message hiển thị tại Result là:  You clicked: Cancel
        jsAlertConfirm.dismiss();
        WebElement cancelMessage = driver.findElement(By.xpath("//p[text()='You clicked: Cancel']"));
        String actualCancelMessage = cancelMessage.getText();
        Assert.assertEquals(actualCancelMessage.trim(), "You clicked: Cancel");
    }

/*
Exercise 03: Tương tác với Alert Prompts
Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/javascript_alerts
Step 02 - Click vào button: Click for JS Prompt
Step 03 - Verify message hiển thị trong alert là: I am a JS prompt
Step 04 - Nhập vào text “VuNguyen”và verify message hiển thị tại Result là:  You entered: VuNguyen
 */
    @Test
    public void testcaseAlert03(){
        inputURL("http://the-internet.herokuapp.com/javascript_alerts");

        //Click vào button: Click for JS Prompt
        WebElement jsPromptAlertBtn = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        jsPromptAlertBtn.click();

        //Verify message hiển thị trong alert là: I am a JS prompt
        Alert jsPromptAlert = driver.switchTo().alert();
        String actualjsPromptAlertText = jsPromptAlert.getText();
        Assert.assertEquals(actualjsPromptAlertText.trim(), "I am a JS prompt");

        //Nhập vào text “VuNguyen”và verify message hiển thị tại Result là:  You entered: VuNguyen
        Actions inputTextForAlert = new Actions(driver);
//        inputTextForAlert.sendKeys("");
        jsPromptAlert.accept();

        WebElement inputMessage = driver.findElement(By.xpath("//p[text()='You entered: ']"));
        String actualInputMessage = inputMessage.getText();
        Assert.assertEquals(actualInputMessage.trim(), "You entered:");
    }

    /*
    Exercise 04: Tương tác với javascript( sử dụng javascript: Thay thế các hàm click của selenium bằng Javascript click)
Step 01 - Truy cập vào trang: http://live.guru99.com/
Step 02 - Vào trang đăng nhập: http://live.guru99.com/index.php/customer/account/
Click MY ACCOUNT link
Step 03 - Click CREATE AN ACCOUNT button
Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
(Lưu ý: Tạo random cho dữ liệu tại field Email Address)
Step 05 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
Step 06 – Click account -> Log out
Step 07: Login hệ thống với tài khoản vừa tạo:
-	Click my account
-	Nhập username, password vừa tạo -> Click Log In
Step 08: Kiểm tra login thành công với url hiện tại là: http://live.guru99.com/index.php/customer/account

     */
    @Test
    public void testcaseAlert04(){

    }

    @AfterClass
    public void afterTestcaseAlert(){
        driver.quit();
    }

    public void loadBrowser (){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void inputURL (String URL){
        driver.get(URL);
        driver.manage().window().maximize();
    }
}
