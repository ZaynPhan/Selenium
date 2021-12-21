package DriverExercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
}
