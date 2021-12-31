package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Set;

public class Exercise05 extends Common{

    @BeforeClass
    public void beforeTestcaseIframe() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    /*
    Exercise 2: Tương tác với iframe
    Step 1: Vào trang: https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
    Step 2: Kiểm tra textbox Last name là disable
    Step 3: Input giá trị “nguyen” vào textbox: First name
    Step 4: Click button submit
    Step 5: Kiểm tra message hiển thị là “fname=nguyen”
     */
    @Test
    public void testcaseIframe01() {
        //1. Vào trang w3schools
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
        driver.manage().window().maximize();

        //Vào iframe
        WebElement iframeLoginForm = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
        driver.switchTo().frame(iframeLoginForm);

        //2. Check textbox Last name is disable
        WebElement lastNameInputField = driver.findElement(By.xpath("//input[@id='lname']"));
        Assert.assertFalse(lastNameInputField.isEnabled());

        //3. Input "nguyen" into First name textbox
        WebElement firstNameInputField = driver.findElement(By.xpath("//input[@id='fname']"));
        firstNameInputField.sendKeys("nguyen");

        //4. Click button submit
        WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        submitBtn.click();

        //5. Check message 'frame=nguyen' is displayed
        WebElement frameName = driver.findElement(By.xpath("//div[@class='w3-container w3-large w3-border']"));
        String actualFrameNameMessage = frameName.getText().trim();
        Assert.assertEquals(actualFrameNameMessage, "fname=nguyen");

        //5.5 Log out iframe
        driver.switchTo().defaultContent();

        //6. Get text Result Size
        WebElement resultSize = driver.findElement(By.xpath("//span[@id='framesize']"));
        String actualResultSize = resultSize.getText();
        Assert.assertEquals(actualResultSize, "Result Size: 668 x 457");
    }

    /*
    Step 1: Truy cập trang web: :"https://www.adidas.com.vn/vi/help.html"
    Step 2: Check dòng text: "HỖ TRỢ CỬA HÀNG TRỰC"
    Step 3: Scroll xuống cuối trang
    Step 4: Click vào logo Đã thông báo Bộ công thương
    Step 5: Check dòng text "Công ty TNHH adidas Việt Nam" được hiển thị
    Step 6: Đóng trang "Bộ công thương"
     */
    @Test
    public void testcaseWindows() {
        //1. Vào trang CSKH adidas
        driver.get("https://www.adidas.com.vn/vi/help.html");
        driver.manage().window().maximize();
        String adidasTitle = driver.getTitle();

        //2. Check dòng text: "HỖ TRỢ CỬA HÀNG TRỰC"
        WebElement headerText = driver.findElement(By.xpath("//h1[text()='Hỗ Trợ Cửa Hàng Trực']"));
        String actualHeaderText = headerText.getText();
        Assert.assertEquals(actualHeaderText, "HỖ TRỢ CỬA HÀNG TRỰC");

        //3. Scroll xuống cuối trang
        Actions scrollDown = new Actions(driver);
        scrollDown.sendKeys(Keys.PAGE_DOWN);
        scrollDown.sendKeys(Keys.PAGE_DOWN);

        //4. Click vào logo Đã thông báo Bộ công thương
        WebElement AnouncementLogo = driver.findElement(By.xpath("//a[@href=\"http://online.gov.vn/HomePage/CustomWebsiteDisplay.aspx?DocId=42438\"]/img"));
        AnouncementLogo.click();

        //4.5 Switch qua tab "Thông tin website thương mại điện tử"
//        switchToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
        switchToWindowByIndex(1);

        //5. Check dòng text "Công ty TNHH adidas Việt Nam" được hiển thị
        WebElement companyNameText = driver.findElement(By.xpath("//p[text()='Tên Doanh nghiệp:']/ancestor::div/following-sibling::div[@class='col-xs-6']"));
        String actualCompanyName = companyNameText.getText().trim();
        Assert.assertEquals(actualCompanyName, "Công ty TNHH adidas Việt Nam");

        //6. Đóng trang "Bộ công thương"
        driver.close();

        //Chuyển về trang CSKH adidas
        switchToWindowByTitle(adidasTitle);
    }

    public void switchToWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String childWindows : allWindows) {
            driver.switchTo().window(childWindows);
            String childTitle = driver.getTitle();
            if (childTitle.equals(title)) {
                break;
            }
        }
    }

    @AfterClass
    public void afterTestcaseIframe() {
        driver.quit();
    }
}
