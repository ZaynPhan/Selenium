package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebCommands {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Driver/chromedriver.exe");
        driver = new ChromeDriver(); //mở browser
        driver.get("http://demo.guru99.com/v4/"); //truy cập link
        driver.manage().window().maximize(); //phóng to
    }

    @Test
    public void testcase01(){
        //check URL guru
        String guruURL = driver.getCurrentUrl();
        Assert.assertEquals(guruURL,"http://demo.guru99.com/v4/");

        //Find element Insurance
        WebElement lbInsuranceAtHomePage = driver.findElement(By.xpath("//a[@href='http://demo.guru99.com/insurance/v1/index.php']"));
        String insurranceLink = lbInsuranceAtHomePage.getAttribute("href");
        Assert.assertEquals(insurranceLink,"http://demo.guru99.com/insurance/v1/index.php");

        //Click on element
        lbInsuranceAtHomePage.click();

        //Check URL insurance
        String insurranceProjectURL = driver.getCurrentUrl();
        Assert.assertEquals(insurranceProjectURL,"http://demo.guru99.com/insurance/v1/index.php");

        //Check title Insurance
        String insurranceProjectTitle = driver.getTitle();
        Assert.assertEquals(insurranceProjectTitle,"Insurance Broker System - Login");

        //Go Back
        driver.navigate().back();

        //Check title Home page
        String guruTitle = driver.getTitle();
        Assert.assertEquals(guruTitle,"Guru99 Bank Home Page");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();

    }
}
