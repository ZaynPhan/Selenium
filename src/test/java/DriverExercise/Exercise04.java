package DriverExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Exercise04 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");
        driver = new ChromeDriver(); //mở browser
//        driver.get("https://www.24h.com.vn/"); //truy cập link
//        driver.manage().window().maximize(); //phóng to
    }

    /*Exercise 1: Hover action
Step 1: Vào trang: https://www.24h.com.vn/
Step 2: Hover vào menu: Danh mục -> Tiếp tục hover menu bóng đá -> Tiếp tục hover -> Lịch thi đấu bóng đá
Step 3: Click vào menu Lịch thi đấu bóng đá
Step 4: Kiểm tra title của web hiện tại là: Lịch thi đấu Bóng Đá Anh, Ý TBN C1, Kết quả Tỉ lệ cược 24h
*/
    @Test
    public void testcaseHoverAction() throws InterruptedException {
        //Hover vào menu: Danh mục
        WebElement categoryMenu = driver.findElement(By.xpath("//a[text()='Danh mục']"));
        Actions categoryHover = new Actions(driver);
        categoryHover.moveToElement(categoryMenu).perform();
        Thread.sleep(1000);

        //Tiếp tục hover menu bóng đá
        WebElement categoryFootball = driver.findElement(By.xpath("//ul[@class='fly']/preceding-sibling::a[text()='Bóng đá']"));
        Actions cateFootballHover = new Actions(driver);
        cateFootballHover.moveToElement(categoryFootball).perform();
        Thread.sleep(1000);

        //Tiếp tục hover Lịch thi đấu bóng đá
        WebElement footballMatchShedule = driver.findElement(By.xpath("//a[@title='Lịch thi đấu bóng đá']"));
        Actions footballSheduleHover = new Actions(driver);
        footballSheduleHover.moveToElement(footballMatchShedule).perform();
        Thread.sleep(1000);
    }

    /*Exercise 2: Click and Hold action
    Step 1: Vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
    Step 2: Click and hold: từ số 1 đến 8
    Step 3: Kiểm tra rằng đã chọn 8 element thành công( Getsize list element =8)
    */
    @Test
    public void testcaseClickAndHover() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html"); //truy cập link
        driver.manage().window().maximize(); //phóng to

        //Click and hold từ 1 đến 8
//        List<WebElement> listHold8Elements = driver.findElements(By.xpath("//ol[@id='selectable']"));
//        Actions clickHold = new Actions(driver);
//        clickHold.clickAndHold(listHold8Elements.get(0)).clickAndHold(listHold8Elements.get(11)).click().perform();
//        clickHold.release();

        WebElement number1 = driver.findElement(By.xpath("//li[text()='1']"));
        Actions clickHold1 = new Actions(driver);
        clickHold1.clickAndHold(number1).perform();
        Thread.sleep(1500);
        WebElement number8 = driver.findElement(By.xpath("//li[text()='8']"));
        Actions moveTo8 = new Actions(driver);
        moveTo8.moveToElement(number8).perform();
        Thread.sleep(1500);

        //Check số lượng element được chọn bằng 8?
        List<WebElement> listSelectedElements = driver.findElements(By.xpath("//li[@class='ui-state" +
                "-default ui-selectee ui-selected']"));
        Assert.assertEquals(listSelectedElements.size(), 8);
    }

    /*
    Exercise 3: Right click action
    Step 1 - Truy cập vào trang: http://demo.guru99.com/test/simple_context_menu.html
    Step 2 - Right click vào button: right click me
    Step 3 - Hover chuột và click vào label: Quit
    Step 4: Verify text của alert là: “clicked: quit”
    Step 5: Accept alert
     */
    @Test
    public void testcaseRightClick() throws InterruptedException {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html"); //truy cập link
        driver.manage().window().maximize(); //phóng to

        //Right click vào button: right click me
        WebElement rightClickMeBtn = driver.findElement(By.xpath("//span[text()='right click me']"));
        Actions rightClick = new Actions(driver).contextClick(rightClickMeBtn);
        rightClick.build().perform();

        //Hover chuột và click vào label: Quit
        WebElement quitBtn = driver.findElement(By.xpath("//span[text()='Quit']"));
        Actions quitHover = new Actions(driver);
        quitHover.moveToElement(quitBtn).perform();
        Thread.sleep(1500);

        quitBtn.click();
        Thread.sleep(1500);

        //Verify text của alert là: “clicked: quit”
        String actualAlertText = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText, "clicked: quit");
        Thread.sleep(1500);

        //Accept alert
        driver.switchTo().alert().accept();

        //Check đã hide alert
        rightClick.build().perform();
        Thread.sleep(2000);
    }

    /*
    Exercise 4: Double click action
    Step 1 - Truy cập vào trang: http://demo.guru99.com/test/simple_context_menu.html
    Step 2 - Double click vào button: “Double-Click Me To See Alert”
    Step 3: Verify text của alert là: “You double clicked me.. Thank You..”
    Step 4: Accept alert
     */
    @Test
    public void testcaseDoubleClick() throws InterruptedException {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html"); //truy cập link
        driver.manage().window().maximize(); //phóng to

        //Double click vào button: “Double-Click Me To See Alert”
        WebElement doubleClickBtn = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
        Actions doubleClick = new Actions(driver);
        doubleClick.doubleClick(doubleClickBtn).perform();
        Thread.sleep(1500);

        //Verify của alert là: “You double clicked me.. Thank You..”
        String actualAlertText = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertText, "You double clicked me.. Thank You..");
        Thread.sleep(1500);

        //Accept alert
        driver.switchTo().alert().accept();

        //Check đã hide alert
        doubleClick.doubleClick(doubleClickBtn).perform();
        Thread.sleep(2000);
    }

    /*
    Exercise 5: Drag and Drop action
Step 1: Assert chữ: “Drag the small circle here.” được hiển thị
Step 2: Kéo hình tròn màu xanh lên trên vòng tròn lớn
Step 3: Assert chữ “You did great!” được hiển thị khi drag and drop thành công
     */
    @Test
    public void testcaseDragAndDrop() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
        driver.manage().window().maximize();

        //Check text 'Drag the small circle here.' is displayed
        WebElement dragHereText = driver.findElement(By.xpath("//div[text()='Drag the small circle here.']"));
        Assert.assertTrue(dragHereText.isDisplayed());

        //Kéo hình tròn màu xanh lên trên vòng tròn lớn
        WebElement dragCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement dropCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
        Actions builder = new Actions(driver);
//        Action dragAndDrop = builder.clickAndHold(dragCircle).moveToElement(dropCircle).release(dropCircle).build();
//        dragAndDrop.perform();
        for (int i = 0; i < 8; i++) {
            builder.sendKeys(Keys.ARROW_DOWN);
        }
        builder.dragAndDrop(dragCircle,dropCircle).build().perform();
        Thread.sleep(1500);

        //Check Drop success?
//        WebElement highlightElement = driver.findElement(By.xpath("//div[@class='ui-widget-header ui-droppable ui-state-highlight']"));
//        Assert.assertTrue(highlightElement.isDisplayed());

        //Verify text của hình chữ nhật “Drop here” ban đầu đã thay đổi thành “Dropped!”
        WebElement successText = driver.findElement(By.xpath("//div[text()='You did great!']"));
        Assert.assertTrue(successText.isDisplayed());
    }

//    @Test
//    public void Testscript05_TC01_dragAndDrop() throws Exception {
//
//        driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
//        driver.manage().window().maximize();
//
//        WebElement dragFrom = driver.findElement(By.xpath("//div[@id='draggable']"));
//        WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
//        Actions builder = new Actions(driver);
//        Action dragAndDrop = builder.clickAndHold(dragFrom).moveToElement(target).release(target).build();
//        dragAndDrop.perform();
//        Assert.assertEquals("You did great!", target.getText());
//    }
//
//    @Test
//    public void Testscript05_TC02_dragAndDrop() throws Exception {
//
//        driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
//        driver.manage().window().maximize();
//
//        WebElement dragFrom = driver
//                .findElement(By.xpath("//div[@class='ui-widget-content ui-draggable ui-draggable-handle']"));
//        WebElement target = driver.findElement(By.xpath("//div[@class='ui-widget-header ui-droppable']"));
//        WebElement text = driver.findElement(By.xpath("//div[@class='ui-widget-header ui-droppable']/p"));
//
//        Actions builder = new Actions(driver);
//        Action dragAndDrop = builder.clickAndHold(dragFrom).moveToElement(target).release(target).build();
//        dragAndDrop.perform();
//        Assert.assertEquals("Dropped!", text.getText());
//    }
    /*
    Exercise 6: Keyboard action
    Step 1: Vào trang: http://demo.guru99.com/v4/
    Step 2: Input username và password: nguyenvana/12345678
    Step 3: Click phím tab 2 lần -> Trỏ đến button Reset
    Step 4: Click phím Enter để thực hiện reset
    Step 5: Input username và password: mngr264250/umutAmA
    Step 6: Click phím Enter để thực hiện đăng nhập
    Step 7: Verify text: Manger Id : mngr264250 được hiển thị
    Step 8: Ấn tổ hợp phím Stril + N ở textbox:  UserID để mở window mới
    Step 9: Vào trang https://www.google.com/
    Step 10: Verify title hiện tại là: Google
     */
    @Test
    public void testcaseKeyboardAction() throws InterruptedException {
        driver.get("http://demo.guru99.com/v4/index.php");
        driver.manage().window().maximize();

        //Input username và password: mngr373193/sutEjYq
        WebElement userNameTextField = driver.findElement(By.xpath("//input[@name='uid']"));
        userNameTextField.sendKeys("mngr373193");

        WebElement passwordTextField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordTextField.sendKeys("sutEjYq");

        //Click phím tab 2 lần -> Trỏ đến button Reset
        passwordTextField.sendKeys(Keys.TAB);
        passwordTextField.sendKeys(Keys.TAB);

        //Click phím Enter để thực hiện reset
        WebElement resetBtn = driver.findElement(By.xpath("//input[@name='btnReset']"));
        resetBtn.sendKeys(Keys.ENTER);

        //Check 2 warning message is displayed
        userNameTextField.click();
        passwordTextField.click();
        Thread.sleep(1500);
        WebElement userNameWarningMessage = driver.findElement(By.xpath("//label[@id='message23']"));
        Assert.assertTrue(userNameWarningMessage.isDisplayed());

        userNameTextField.click();
        Thread.sleep(1500);
        WebElement passwordWarningMessage = driver.findElement(By.xpath("//label[@id='message18']"));
        Assert.assertTrue(passwordWarningMessage.isDisplayed());

        //Input username và password: mngr373193/sutEjYq
        userNameTextField.sendKeys("mngr373193");
        passwordTextField.sendKeys("sutEjYq");

        //Click phím Enter để thực hiện đăng nhập
        passwordTextField.sendKeys(Keys.ENTER);

        //Check dòng chữ 'Manger Id : mngr373193' is displayed
        WebElement managerIdText = driver.findElement(By.xpath("//td[text()='Manger Id : mngr373193']"));
        Assert.assertTrue(managerIdText.isDisplayed());

        //Bấm tổ hợp phím Ctrl + N
        Actions newWindow = new Actions(driver);
        newWindow.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "N"));
        Thread.sleep(2000);

        //Vào trang https://www.google.com/
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();

        //Verify title hiện tại là: Google
        String actualTitle= driver.getTitle();
        Assert.assertEquals(actualTitle, "Google");

        //Bấm tổ hợp phím clear cache browser: Ctrl + Shift + R
        Actions clearCacheBrowser = new Actions(driver);
        clearCacheBrowser.sendKeys(Keys.chord(Keys.LEFT_CONTROL, Keys.chord(Keys.LEFT_SHIFT, "R")));
        Thread.sleep(2000);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
