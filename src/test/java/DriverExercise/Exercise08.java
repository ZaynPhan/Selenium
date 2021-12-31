package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise08 extends Common {

    @BeforeClass
    public void beforeTestcaseWait() {
        loadBrowser();
    }

    /*
    Exercise 1: Chờ với invisible wait
Step 1: Vào trang: http://the-internet.herokuapp.com/dynamic_loading/1
Step 2: Click button Start
Step 3: Wait cho đến khi chữ Loading biến mất
Step 4: Verify label: “Hello World!” được hiển thị
     */
    @Test
    public void testcaseWait01() {
        inputURL("http://the-internet.herokuapp.com/dynamic_loading/1");

        //Click button Start
        WebElement startBtn = driver.findElement(By.xpath("//button[text()='Start']"));
        startBtn.click();

        //Wait until 'Loading' text disappears
        WebDriverWait explicitWait = new WebDriverWait(driver, 15);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

        //Verify label "Hello World!" is displayed
        WebElement labelHello = driver.findElement(By.xpath("//h4[text()='Hello World!']"));
        String actualLabelHello = labelHello.getText();
        Assert.assertEquals(actualLabelHello.trim(), "Hello World!");
    }

    /*
    Exercise 2: Chờ với visible wait
    Step 1: Vào trang: http://the-internet.herokuapp.com/dynamic_controls
    Step 2: Click button Remove
    Step 3: Wait cho đến khi label “It's gone!” và button Add hiển thị
    Step 4: Verify label “It's gone!” và button Add hiển thị
     */
    @Test
    public void testcaseWait02() {
        inputURL("http://the-internet.herokuapp.com/dynamic_controls");

        //Click button Remove
        WebElement removeBtn = driver.findElement(By.cssSelector("button[onclick='swapCheckbox()']"));
        removeBtn.click();

        //Wait cho đến khi label “It's gone!” và button Add hiển thị
        waitForElement(10, "//p[@id='message']");
        waitForElement(10, "//button[text()='Add']");

        //Verify label “It's gone!” và button Add hiển thị
        WebElement labelGone = driver.findElement(By.id("message"));
        Assert.assertTrue(labelGone.isDisplayed());

        WebElement addBtn = driver.findElement(By.xpath("//button[text()='Add']"));
        Assert.assertTrue(addBtn.isDisplayed());
    }

    /*
    Exercise 3: Chờ với fluent wait
    Step 1: Vào trang: https://www.w3schools.com/howto/howto_js_countdown.asp
    Step 2: Chờ fluentWait cho đến khi “25s” được hiển thị
     */
    @Test
    public void testcaseWait03() {
        inputURL("https://www.w3schools.com/howto/howto_js_countdown.asp");

        //Chờ FluentWait cho đến khi 25s được hiển thị
//        Wait wait = new FluentWait(driver)
//                .withTimeout(30, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//        WebElement scs25 = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='countdown1']")));
//        Assert.assertTrue(scs25.isDisplayed());

//        WebElement scs25 = driver.findElement(By.xpath("//p[@id='countdown1']"));

//        new FluentWait<WebElement>(scs25).withTimeout(60, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
//                .until(new Function<WebElement, Boolean>() {
//                    public Boolean apply(WebElement element) {
//                        return element.getText().endsWith("25s");
//                    }
//                }

        waitForElement(120, "//p[contains(text(), '25s')]");
        String second25Text = driver.findElement(By.xpath("//p[contains(text(), '25s')]")).getText();
        Assert.assertEquals(second25Text.trim(), "6d 5h 5m 25s");
    }

    @AfterClass
    public void afterTestcaseWait() {
        driver.quit();
    }
}
