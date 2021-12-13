package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExerciseWebElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver(); //mở browser
        driver.get("http://demo.guru99.com/v4/"); //truy cập link
        driver.manage().window().maximize(); //phóng to
    }

    @Test
    public void testcase01() {
        //check URL guru
        String guruURL = driver.getCurrentUrl();
        Assert.assertEquals(guruURL, "http://demo.guru99.com/v4/");

        //Find element Insurance
        WebElement lbInsuranceAtHomePage = driver.findElement(By.xpath("//a[@href='http://demo.guru99.com/insurance/v1/index.php']"));

//        //Input data into element
//        lbInsuranceAtHomePage.sendKeys("zaynphan" + randomNumber());

        String insurranceLink = lbInsuranceAtHomePage.getAttribute("href");
        Assert.assertEquals(insurranceLink, "http://demo.guru99.com/insurance/v1/index.php");

        //Click on element
        lbInsuranceAtHomePage.click();

        //Check URL insurance
        String insurranceProjectURL = driver.getCurrentUrl();
        Assert.assertEquals(insurranceProjectURL, "http://demo.guru99.com/insurance/v1/index.php");

        //Check title Insurance
        String insurranceProjectTitle = driver.getTitle();
        Assert.assertEquals(insurranceProjectTitle, "Insurance Broker System - Login");

        //Go Back
        driver.navigate().back();

        //Check title Home page
        String guruTitle = driver.getTitle();
        Assert.assertEquals(guruTitle, "Guru99 Bank Home Page");
    }

    /*
    1. Vào trang chủ guru99
    2. Click vào here
    3. Input random valid email ID
    4. Click nút Submit
    5. Lấy value của User ID và Password
    6. vào lại trang chủ
    7. Input User ID và Password vừa đăng ký
    8. Assert chữ "Welcome To Manager's Page of Guru99 Bank" xuất hiện là thành công
     */
    @Test
    public void testcase02() {
        //2. Click vào here
        //Find element Here
        WebElement hereCTA = driver.findElement(By.xpath("//a[text()='here']"));

        //Click on element
        hereCTA.click();

        //Check URL generate access web
        String generateAccessURL = driver.getCurrentUrl();
        Assert.assertEquals(generateAccessURL, "http://demo.guru99.com/");

        //Check title Generate Access
        String generateAccessTitle = driver.getTitle();
        Assert.assertEquals(generateAccessTitle, "Guru99 Bank Home Page");

        //Find element emailInputField
        WebElement emailInput = driver.findElement(By.xpath("//label[@id=\"message9\"]/" +
                "preceding-sibling::input[@name=\"emailid\"]"));

        //3.Input random valid email ID
        String emailRegister = "zaynphan" + randomNumber() + "@gmail.com";
        emailInput.sendKeys(emailRegister);

        //Find element Submit button
        WebElement submitBtn = driver.findElement(By.xpath("//input[@name='btnLogin']"));

        //4. Click on Submit button
        submitBtn.click();

        //5. Lấy value của User ID và Password
        //Find element and get userIDValue
        WebElement userIDValue = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td"));
        String userName = userIDValue.getText().trim();

        //Find element and get passwordValue
        WebElement passwordValue = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td"));
        String password = passwordValue.getText().trim();

        //Check generate access URL success
        String generateAccessSuccessURL = driver.getCurrentUrl();
        Assert.assertEquals(generateAccessSuccessURL, "http://demo.guru99.com/access.php?uid="
                + userName + "%20&%20pass=" + password + "%20&%20email=" + emailRegister);

        //Find element Success Message and check displayed or not?
        WebElement successMessage = driver.findElement(By.xpath("//h3[text()=\"This access is valid only for 20 days.\"]"));
        successMessage.isDisplayed();

        //6. Vào lại trang chủ
        driver.get("http://demo.guru99.com/v4/");

        //Check title Home page
        String guruHomePageTitle = driver.getTitle();
        Assert.assertEquals(guruHomePageTitle, "Guru99 Bank Home Page");

        //Find and input user ID has just been created
        WebElement inputUserID = driver.findElement(By.xpath(
                "//td[text()='UserID']/following-sibling::td/input[@name='uid']"));
        inputUserID.sendKeys(userName);

        //Find and input password has just been created
        WebElement inputPassword = driver.findElement(By.xpath(
                "//td[text()='Password']/following-sibling::td/input[@name='password']"));
        inputPassword.sendKeys(password);

        //Find element button Submit
        WebElement submitbtn = driver.findElement(By.xpath("//input[@name='btnLogin']"));

        //Click on Submit button
        submitbtn.click();

        //Check URL Manager Home Page
        String managerHomePageURL = driver.getCurrentUrl();
        Assert.assertEquals(managerHomePageURL, "http://demo.guru99.com/v4/manager/Managerhomepage.php");

        //Check title Home page
        String managerHomePagetitle = driver.getTitle();
        Assert.assertEquals(managerHomePagetitle, "Guru99 Bank Manager HomePage");

        //Find and check value Welcome message
        WebElement welcomeMessage = driver.findElement(By.xpath("//marquee[text()=\"Welcome " +
                "To Manager's Page of Guru99 Bank\"]"));

        //Check welcome message displayed
        Assert.assertTrue(welcomeMessage.isDisplayed());
    }

    @Test
    public void testcase03() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int randomNumber() {
        return (int) (Math.random() * 1000000 + 1);
    }
}
