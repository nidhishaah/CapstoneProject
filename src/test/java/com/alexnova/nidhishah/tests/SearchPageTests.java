package com.alexnova.nidhishah.tests;

import com.alexnova.nidhishah.library.SelectBrowser;
import com.alexnova.nidhishah.pages.AccountPage;
import com.alexnova.nidhishah.pages.HomePage;
import com.alexnova.nidhishah.pages.LoginPage;
import com.alexnova.nidhishah.pages.SearchPage;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
/*
This Class contains all test in Search Page.
This test contains TC0008 to TC0009
 */
public class SearchPageTests extends BaseTest{

    WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;

    @BeforeMethod
    @Parameters("browserName")
    public void SetUp(String browserName){
        driver = SelectBrowser.StartBrowser(browserName);
        driver.manage().deleteAllCookies();
        if(browserName.equals("Chrome"))
        {
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        }
        driver.get("http://www.alexandnova.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /*
    This test case verifies if a valid search page is shown for a valid search query
    Assertion is on number of search results shown
     */
    @Test(priority = 8)
    public void tc0008_SearchBoxPositiveTest(Method method){

        test = extent.createTest(method.getName(), "Running test ...");
        homePage = new HomePage(driver);

        searchPage = homePage.enterSearch("baby shoes");
        test.log(Status.INFO,"Enter valid search field");

        String resultMessage = searchPage.getSearchResultsMessage();
        test.log(Status.INFO,"Search result is:"+resultMessage);
        System.out.println(resultMessage);

        String[] arr1 = resultMessage.split(" ");
        System.out.println(arr1[0]);
        int num1 = Integer.parseInt(arr1[0]);
        System.out.println("num="+num1);
        Assert.assertTrue(num1>0);
        test.log(Status.PASS,"Assert more than 0 search result received");
    }

    /*
    This test case verifies an empty search query
    Assertion is on error message received
     */
    @Test(priority = 9)
    public void tc0009_SearchBoxEmptyTest(Method method){

        test = extent.createTest(method.getName(), "Running test ...");

        homePage = new HomePage(driver);
        searchPage = homePage.enterSearch(" ");
        test.log(Status.INFO,"Entered an empty search");

        String resultMessage = searchPage.getNoResultsMessage();
        System.out.println(resultMessage);
        test.log(Status.INFO,"Get message for no results found");

        Assert.assertTrue(resultMessage.contains("No results found."));
        test.log(Status.PASS,"Assert on "+resultMessage);
    }


    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot,new File(System.getProperty("user.dir")+"/test-output/screenshots/"+result.getName()+"failure.png"));
            test.addScreenCaptureFromPath(result.getName()+"failure.png");
            test.fail("test failed");
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
