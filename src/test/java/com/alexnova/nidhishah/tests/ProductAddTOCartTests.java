package com.alexnova.nidhishah.tests;

import com.alexnova.nidhishah.library.SelectBrowser;
import com.alexnova.nidhishah.pages.*;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
/*
This Class has all test cases when product is added to the Cart.
This class has test cases TC0010 to TC0015
 */
public class ProductAddTOCartTests extends BaseTest{
    WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;
    ProductsPage productsPage;
    CartPage cartPage;
    private double priceInt;

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
This test case verifies if the product page shows product price.
Assertion is on verifying a valid price.
 */
    @Test(priority = 10)
    public void tc0010_ProductPagePricesPositiveTest(Method method) throws InterruptedException {

        test = extent.createTest(method.getName(), "Running test ...");
        homePage = new HomePage(driver);

        searchPage = homePage.enterSearch("baby shoes");
        test.log(Status.INFO,"Entered 'baby shoes' in search field");

        productsPage = searchPage.clickOnStripedShoes();
        test.log(Status.INFO,"Select the first pair of shoes showed");

        String price = productsPage.getStripedShoesPrice();
        test.log(Status.INFO,"Product shows on product page");

        Assert.assertTrue(price.contains("$"));
        Assert.assertTrue(price.contains("USD"));
        priceInt = Double.parseDouble(price.substring(1,6));
        System.out.println(priceInt);
        test.log(Status.INFO,"Price shown for shoes="+price);

        Assert.assertTrue(priceInt>0.0);
        test.log(Status.PASS,"Assert on price of product shown");
   }

   /*
   This test case verifies if a selected product can be successfully added to cart .
   Assertion is on product description in cart
    */
    @Test(priority = 11)
    public void tc0011_ProductAddedToCartTest(Method method) throws InterruptedException {

        test = extent.createTest(method.getName(), "Running test ...");

        productsPage.selectSize();
        test.log(Status.INFO,"Select size of shoes");

        productsPage.selectColor();
        test.log(Status.INFO,"Select color of shoes");

        productsPage.clickAddToCart();
        //Thread.sleep(5000);
        test.log(Status.INFO,"Add product to cart");

        cartPage = productsPage.clickCart();
        //Thread.sleep(2000);

        String productIncart = cartPage.getProductDescription();
        test.log(Status.INFO,"Product description in cart="+productIncart);

        System.out.println(productIncart);
        Assert.assertTrue(productIncart.contains("Baby Sandals"));
        test.log(Status.PASS,"Assert product available in cart");
    }

    /*
    This test case verifies if we refresh page and product remains in cart
    Assertion is on product description in cart
     */
    @Test(priority = 12)
    public void tc0012_RefreshCartTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        driver.navigate().refresh();
        Thread.sleep(3000);
        test.log(Status.INFO,"Refresh cart");

        String productIncart = cartPage.getProductDescription();
        System.out.println(productIncart);
        test.log(Status.INFO,"product in cart="+productIncart);

        Assert.assertTrue(productIncart.contains("Baby Sandals"));
        test.log(Status.PASS,"Assert product still available on cart");
    }

    /*
    This test case verifies if we can successfully increase quantity of product in cart.
    Assertion is on quantity of product
     */

    @Test(priority = 13)
    public void tc0013_IncreaseQuantityTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");

        cartPage.increaseQuantity("3");
        test.log(Status.INFO,"Increase quantity to 3");

        String qty = cartPage.getQuantity();
        Assert.assertEquals(qty,"3");
        test.log(Status.PASS,"Assert quantity in cart is 3");
        cartPage.clickUpdateCart();
        System.out.println(cartPage.getQuantity());

        test.log(Status.INFO,"Updated cart");

    }

    /*
    This test case verifies if final product updates when we increase the quantity
    Assertion is on the final product
     */
    @Test(priority = 14,dependsOnMethods = {"tc0010_ProductPagePricesPositiveTest"})
    public void tc0014_FinalAmountUpdatesTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");
        String finalPrice = cartPage.getFinalPrice();
        System.out.println(finalPrice);
        System.out.println(priceInt);
        Double expectedPrice = Math.round(3.0 * priceInt*100.0)/100.0;

        System.out.println(expectedPrice);
        test.log(Status.INFO,"Final price updated");

       // System.out.println(expectedPrice);
        Assert.assertTrue(finalPrice.contains(expectedPrice+""));
        test.log(Status.PASS,"Asserted on final price match expected price");
    }

    /*
    This test case test if we can successfully remove a product from the cart.
    Assertion is on message saying no items in cart.
     */
    @Test(priority = 15)
    public void tc0015_RemoveProductFromCartTest(Method method) throws InterruptedException {
        test = extent.createTest(method.getName(), "Running test ...");
        cartPage.increaseQuantity("0");

        test.log(Status.INFO,"Quantity changed to 0");
        Thread.sleep(2000);
        String noItemMessage= cartPage.getNoItemMessage();
        test.log(Status.INFO,"Message given = "+noItemMessage);

        Assert.assertTrue(noItemMessage.contains("You don't have any items in your cart yet"));
        test.log(Status.PASS,"Asserted on no items in cart message");
    }




    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot,new File(System.getProperty("user.dir")+"/test-output/screenshots/"+result.getName()+"failure.png"));
            test.addScreenCaptureFromPath(result.getName()+"failure.png");
            test.fail("Test failed");
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
    }
}
