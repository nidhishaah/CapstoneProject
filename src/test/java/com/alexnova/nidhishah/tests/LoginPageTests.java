package com.alexnova.nidhishah.tests;

import com.alexnova.nidhishah.library.SelectBrowser;
import com.alexnova.nidhishah.pages.AccountPage;
import com.alexnova.nidhishah.pages.LoginPage;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class LoginPageTests extends BaseTest{
    WebDriver driver;
    LoginPage loginPage;
    AccountPage accountPage;

    @BeforeMethod
    @Parameters("browserName")
    public void SetUp(String browserName){
        driver = SelectBrowser.StartBrowser(browserName);
        driver.manage().deleteAllCookies();
        if(browserName.equals("Chrome")) {
            driver.get("chrome://settings/clearBrowserData");
            driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        }
        driver.get("http://www.alexandnova.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test(priority = 6)
    public void tc0006_LoginPositiveTesting(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");
        driver.get("https://www.alexandnova.com/account/login");
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("john.fink1981@gmail.com");
        Thread.sleep(1000);
        loginPage.enterPassword("P@ssword");
        Thread.sleep(1000);
        test.log(Status.INFO,"Entered Login info on login screen");

        accountPage = loginPage.clickLogin();
        test.log(Status.INFO,"Clicked on Login Button");
        Thread.sleep(20000);

        String welcomeMsg = accountPage.getHeading();
        Assert.assertEquals(welcomeMsg,"Welcome, John");
        test.log(Status.PASS,"Asserted on login message");
    }

    @Test(priority = 7)
    public void tc0007_LoginNegativeTesting(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");
        driver.get("https://www.alexandnova.com/account/login");
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("john.finkkk@gmail.com");
        Thread.sleep(1000);
        loginPage.enterPassword("P@ssword!!");
        Thread.sleep(1000);
        test.log(Status.INFO,"Entered Login info on login screen");

        accountPage = loginPage.clickLogin();
        test.log(Status.INFO,"Clicked on Login Button");
        Thread.sleep(20000);

        String errorMsg = loginPage.getErrorMsg();
        Assert.assertEquals(errorMsg,"Sorry! Please try that again.");
        test.log(Status.PASS,"Asserted on the error message");
    }




    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot,new File(System.getProperty("user.dir")+"/test-output/screenshots/"+result.getName()+"failure.png"));
            test.addScreenCaptureFromPath(result.getName()+"failure.png");
        }
        else if (result.getStatus() == ITestResult.SKIP) {
            test.skip(result.getThrowable());
        }
        else {
            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot,new File(System.getProperty("user.dir")+"/test-output/screenshots/"+result.getName()+"_pass.png"));
            test.addScreenCaptureFromPath(result.getName()+"_pass.png");
            test.pass("Test passed");
        }
        driver.quit();
    }



}
