package com.alexnova.nidhishah.tests;

import com.alexnova.nidhishah.library.SelectBrowser;
import com.alexnova.nidhishah.pages.*;
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

/*
This Class has all Test cases regarding Checkout Page.
Contains TC0016 to TC0019
 */

public class CheckoutTests extends BaseTest{

    WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeClass
    @Parameters("browserName")
    public void SetUp(String browserName){
        driver = SelectBrowser.StartBrowser(browserName);

        driver.manage().deleteAllCookies();

        if(browserName.equals("Chrome")){
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);}

        driver.get("http://www.alexandnova.com");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /*
    This test case applies Discount and checks if it deducts amount from final price
    Assertion is on Discount value received.
     */
    @Test(priority = 16)
    public void tc0016_ApplyDiscountCodeTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        homePage = new HomePage(driver);
        searchPage = homePage.enterSearch("baby shoes");
        test.log(Status.INFO,"Search product");
        productsPage = searchPage.clickOnStripedShoes();
        productsPage.selectSize();
        productsPage.selectColor();
        productsPage.clickAddToCart();
        test.log(Status.INFO,"Add product to cart");
        cartPage = productsPage.clickCart();
        test.log(Status.INFO,"Click Checkout");
        checkoutPage = cartPage.clickCheckout();
        checkoutPage.enterDiscount();
        test.log(Status.INFO,"Enter Discount");
        Thread.sleep(3000);

        double discount = Double.parseDouble(checkoutPage.getDiscountValue().substring(3));
        Assert.assertTrue(discount > 0);
        test.log(Status.PASS,"Discount is greater than 0");

    }


    /*
    This test case checks if three modes of payment available:Credit card , Shop pay and pay pal
     Assertion is on text got from 3 payment fields in checkout page after filling address details.
     */
    @Test(priority = 17 )
    public void tc0017_PaymentModeCheck(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        checkoutPage.fillAddress();
        test.log(Status.INFO,"Fill address and continue");
        checkoutPage.clickContinue();
        Thread.sleep(5000);
        test.log(Status.INFO,"Fill shipping and continue");
        checkoutPage.clickContinue();
        test.log(Status.INFO,"Verify payment options available");
        System.out.println(checkoutPage.getCCPaymentType());
        System.out.println(checkoutPage.shopPayPaymentType());
        System.out.println(checkoutPage.paypalPaymentType());
        Assert.assertEquals(checkoutPage.getCCPaymentType(),"Credit card");
        Assert.assertEquals(checkoutPage.shopPayPaymentType(),"Shop Pay");
        Assert.assertEquals(checkoutPage.paypalPaymentType(),"PayPal");
        test.log(Status.PASS,"Assert on Credit card , sho pay and pay pal");

    }

    /*
    This test case validates if any mandatory fields in the Credit card area.
    Assertion is on the error messages received after we leave the credit card field empty and try to check out.
    */
    @Test(priority = 18)
    public void tc0018_CCMandatoryFieldValidation(Method method){
        test = extent.createTest(method.getName(), "Running test ...");

        checkoutPage.clickContinue();
        test.log(Status.INFO,"Click continue without entering any CC info");
        System.out.println(checkoutPage.getErrorNumber());
       Assert.assertEquals(checkoutPage.getErrorNumber(),"Enter a valid card number");
        System.out.println(checkoutPage.geterrorName());
        Assert.assertEquals(checkoutPage.geterrorName(),"Enter your name exactly as it’s written on your card");
        System.out.println(checkoutPage.geterrorExpiry());
        Assert.assertEquals(checkoutPage.geterrorExpiry(),"Enter a valid card expiry date");
        System.out.println(checkoutPage.geterrorSecurityCode());
        Assert.assertEquals(checkoutPage.geterrorSecurityCode(),"Enter the CVV or security code on your card");
        test.log(Status.PASS,"Asserted on all validation errors");
    }

    /*
    This test case verifies if we get the correct error message when entered wrong credit card info
    Asserting is on the error message received when credit card info entered and pressed the pay now button.
     */
    @Test(priority = 19)
    public void tc0019_SuccessfulCheckoutTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");
        checkoutPage.switchWindow();
        test.log(Status.INFO,"Fill cc info");
        checkoutPage.clickContinue();
        test.log(Status.INFO,"Enter continue");
        Thread.sleep(2000);

        String noticeText = checkoutPage.getNoticeText();
        System.out.println(noticeText);
        Assert.assertTrue(noticeText.contains("Your payment details couldn’t be verified. Check your card details and try again."));
        test.log(Status.PASS,"Assert on 'payment details couldn’t be verified");

    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot,new File(System.getProperty("user.dir")+"/test-output/screenshots/"+result.getName()+"failure.png"));
            test.addScreenCaptureFromPath(result.getName()+"failure.png");
            test.fail("Test Failed");
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
    }

    @AfterClass
    public void tearDown(){
      driver.quit();
      extent.flush();
    }

}
