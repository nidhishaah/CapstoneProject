package com.alexnova.nidhishah.tests;

import com.alexnova.nidhishah.library.SelectBrowser;
import com.alexnova.nidhishah.pages.AccountPage;
import com.alexnova.nidhishah.pages.HomePage;
import com.alexnova.nidhishah.pages.LoginPage;
import com.alexnova.nidhishah.pages.RegisterPage;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class RegistrationPageTests extends BaseTest{

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    AccountPage accountPage;


    @BeforeMethod
    @Parameters("browserName")
    public void SetUp(String browserName){
        //Initialize the driver with the given browser name in testng.xml file
        driver = SelectBrowser.StartBrowser(browserName);
        //Delete ALl Cookies
        driver.manage().deleteAllCookies();

        if(browserName.equals("Chrome")) {
            //Clear cache for Chrome
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        }

        //Navigate to AlexandNova Website
        driver.get("http://www.alexandnova.com");

        //Maximize the window
        driver.manage().window().maximize();

        //Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(priority = 1)
    public void tc0001_NewUserRegistrationPageTest(Method method)
    {
        test = extent.createTest(method.getName(), "Running test ...");
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();

        test.log(Status.INFO,"Tests started for Browser:"+browserName);

        homePage = new HomePage(driver);
      //  homePage.iframeShown();
        loginPage = homePage.clickAccount();
        test.log(Status.INFO,"Click on Account Button");

        registerPage = loginPage.clickRegister();
        test.log(Status.INFO,"Click on Register button");

        Assert.assertTrue(driver.getTitle().contains("Create Account"));
        test.log(Status.PASS,"Assert if page title contains Create Account for the Registration form");

    }


    @Test(priority=2,enabled = true)
    public void tc0002_RegisterNewUserTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        homePage = new HomePage(driver);
        //  homePage.iframeShown();
        loginPage = homePage.clickAccount();
        test.log(Status.INFO,"Click on Account Button");

        registerPage = loginPage.clickRegister();
        test.log(Status.INFO,"Click on Register button");
        registerPage.fillFirstNameField("John");
        Thread.sleep(1000);
        registerPage.fillLastNameField("Fink");
        Thread.sleep(1000);
        registerPage.fillEMailField("john.fink1981@gmail.com");
        Thread.sleep(1000);
        registerPage.fillPasswordField("P@ssword");
        Thread.sleep(1000);
        test.log(Status.INFO,"Entered all 4 input data required");

        loginPage = registerPage.clickRegisterButton();
        test.log(Status.INFO,"Clicked on Register button");

        driver.get("https://www.alexandnova.com/account/login");
        loginPage.enterEmail("john.fink1981@gmail.com");
        loginPage.enterPassword("P@ssword");
        test.log(Status.INFO,"Entered Login info on login screen");

        accountPage = loginPage.clickLogin();
        test.log(Status.INFO,"Clicked on Login Button");
        Thread.sleep(20000);

        String welcomeMsg = accountPage.getHeading();
        Assert.assertEquals(welcomeMsg,"Welcome, John");
        test.log(Status.PASS,"Asserted on Welcome message displayed.");

    }

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc(){
        return new Object[][]{
                {"testAtgmail.com"},{"test@gmailcom"},{"test@gmail"},{"@gmail"}
        };
    }


    @Test(priority = 3,dataProvider ="test-data",enabled = true)
    public void tc0003_InvalidEmailValidationTest(Method method,String email) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        driver.get("https://www.alexandnova.com/account/register");
        registerPage = new RegisterPage(driver);
        registerPage.fillFirstNameField("John");
        Thread.sleep(1000);
        registerPage.fillLastNameField("Fink");
        Thread.sleep(1000);
        registerPage.fillEMailField(email);
        Thread.sleep(1000);
        registerPage.fillPasswordField("P@ssword");
        Thread.sleep(1000);
        test.log(Status.INFO,"Entered all 4 field, entered invalid email during registration");

        loginPage = registerPage.clickRegisterButton();
        Thread.sleep(20000);
        test.log(Status.INFO,"Clicked on Register button");

        String error = loginPage.getErrorMsg();
        System.out.println(error);
        Assert.assertTrue(error.contains("Sorry! Please try that again."));
        test.log(Status.PASS,"Asserted on error message received");
     //   accountPage.logout();
    }

    @Test(priority = 4,enabled = true)
    public void tc0004_MandatoryFieldsTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        driver.get("https://www.alexandnova.com/account/register");
        test.log(Status.INFO,"Navigating to registration page");

        registerPage = new RegisterPage(driver);
        registerPage.fillFirstNameField("");
        Thread.sleep(1000);
        registerPage.fillLastNameField("");
        Thread.sleep(1000);
        registerPage.fillEMailField("");
        Thread.sleep(1000);
        registerPage.fillPasswordField("");
        Thread.sleep(1000);
        test.log(Status.INFO,"Entered all 4 fields as empty");

        loginPage = registerPage.clickRegisterButton();
        Thread.sleep(20000);
        test.log(Status.INFO,"Clicked on Register button");
        String error = loginPage.getErrorMsg();
        Assert.assertTrue(error.contains("Sorry! Please try that again"));
        test.log(Status.PASS,"Asserted on error message received");
    }

    @Test(priority = 5,enabled = true)
    public void tc0005_InvalidPasswordTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        driver.get("https://www.alexandnova.com/account/register");
        test.log(Status.INFO,"Navigating to registration page");

        registerPage = new RegisterPage(driver);
        registerPage.fillFirstNameField("John");
        registerPage.fillLastNameField("Fink");
        registerPage.fillEMailField("john.fink1982@gmail.com");
        registerPage.fillPasswordField("pass");
        test.log(Status.INFO,"Entered all 4 fields,password does not satisfy the password rules");

        loginPage = registerPage.clickRegisterButton();
        Thread.sleep(20000);
        test.log(Status.INFO,"Clicked on Register button");
        String error = loginPage.getErrorMsg();
        System.out.println(error);
        Assert.assertTrue(error.contains("Sorry! Please try that again"));
        test.log(Status.PASS,"Asserted on the error message received");
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

//    @AfterClass
//    public void closeBrowser(){
//        driver.quit();
//    }


}
