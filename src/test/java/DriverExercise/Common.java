package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
    WebDriver driver;
    public void loadBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void inputURL(String URL){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    public void waitForElement(int seconds, String waitConditionLocator) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitConditionLocator)));
    }
}
