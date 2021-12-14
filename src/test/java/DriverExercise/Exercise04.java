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

 */
public class Exercise04 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver(); //mở browser
        driver.get("https://demoqa.com/checkbox"); //truy cập link
        driver.manage().window().maximize(); //phóng to
    }

    @Test
    public void testcase01(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
