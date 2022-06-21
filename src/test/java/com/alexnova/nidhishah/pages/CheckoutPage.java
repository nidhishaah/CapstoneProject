package com.alexnova.nidhishah.pages;

import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

  By discountField = By.xpath("//*[@id=\"checkout_reduction_code\"]");
    public void enterDiscount(){
        driver.findElement(discountField).sendKeys("WELCOME10"+ Keys.ENTER);
    }

    By applyButton = By.xpath("*//button[@id='checkout_submit']");
    public void clickApply(){
        driver.findElement(applyButton).click();
    }

    By discountValue = By.xpath("//*[@id=\"order-summary\"]/div/div[3]/table/tbody/tr[2]/td/span[1]");
    public String getDiscountValue(){
        return driver.findElement(discountValue).getText();

    }

    By addressField = By.id("checkout_shipping_address_address1");
    By cityField= By.id("checkout_shipping_address_city");
    By zipField=By.id("checkout_shipping_address_zip");
    By emailID = By.id("checkout_email");
    By firstName = By.id("checkout_shipping_address_first_name");
    By lastName = By.id("checkout_shipping_address_last_name");

    public void fillAddress() throws InterruptedException {
        driver.findElement(emailID).sendKeys("john.fink1981@gmail.com");
        Thread.sleep(2000);
        driver.findElement(firstName).sendKeys("John");
        Thread.sleep(2000);
        driver.findElement(lastName).sendKeys("Fink");
        Thread.sleep(2000);
        driver.findElement(addressField).sendKeys("Main st 123");
        Thread.sleep(2000);
        driver.findElement(cityField).sendKeys("Superior");
        Thread.sleep(2000);
        driver.findElement(zipField).sendKeys("80027");
    }

    By continueButton = By.id("continue_button");
    public void clickContinue(){
        driver.findElement(continueButton).click();
    }

    By ccPayment = By.cssSelector("body > div > div > div > main > div.step > div > form > div.section.section--payment-method > div.section__content > div:nth-child(2) > fieldset > div:nth-child(2) > div.radio__label.payment-method-wrapper > label");
    public String getCCPaymentType(){
        return driver.findElement(ccPayment).getText();
    }

    By shopPayPayment = By.cssSelector("body > div > div > div > main > div.step > div > form > div.section.section--payment-method > div.section__content > div:nth-child(2) > fieldset > div:nth-child(5) > div.radio__label > label > span");
    public String shopPayPaymentType(){
        return driver.findElement(shopPayPayment).getText();
    }

    By paypalPayment = By.cssSelector("body > div > div > div > main > div.step > div > form > div.section.section--payment-method > div.section__content > div:nth-child(2) > fieldset > div:nth-child(7) > div.radio__label > label > span");
    public String paypalPaymentType(){
        return driver.findElement(paypalPayment).getText();
    }

    By errorNumber = By.id("error-for-number");
    By errorName = By.id("error-for-name");
    By errorExpiry = By.id("error-for-expiry");
    By errorSecurityCode = By.id("error-for-verification_value");

    public String getErrorNumber(){
        return driver.findElement(errorNumber).getText();
    }
    public String geterrorName(){
        return driver.findElement(errorName).getText();
    }
    public String geterrorExpiry(){
        return driver.findElement(errorExpiry).getText();
    }
    public String geterrorSecurityCode(){
        return driver.findElement(errorSecurityCode).getText();
    }


  //  By ccNumber = By.name("number");
    By ccNumber=By.xpath("//*[@id='number']");
    By ccName = By.name("name");
    By ccExpiry = By.name("expiry");
    By ccCode = By.name("verification_value");


    public void fillNumber(){
        driver.findElement(ccNumber).sendKeys("36985214769874");
    }
    public void fillName(){
        driver.findElement(ccName).sendKeys("nidhi shah");
    }
    public void fillExpiry(){
        driver.findElement(ccExpiry).sendKeys("09");
        driver.findElement(ccExpiry).sendKeys("2022");
    }
    public void fillCode(){
        driver.findElement(ccCode).sendKeys("222");
    }

  //  By noticeText = By.cssSelector("body > div > div > div > main > div.step > div > form > div.section.section--payment-method > div.section__content > div:nth-child(2) > div > p")
    By noticeText = By.xpath("*//div[@class='notice__content']/p[text()='Your payment details couldn’t be verified. Check your card details and try again.']");
    public String getNoticeText(){
      //  driver.switchTo().frame(driver.findElement(By.id("google-analytics-sandbox")));
      return  driver.findElement(noticeText).getText();
    }


    public void switchWindow() throws InterruptedException {

        By numberFrame = By.xpath("*//iframe[contains(@id,'card-fields-number')]");

        driver.switchTo().frame(driver.findElement(numberFrame));
        fillNumber();
        driver.switchTo().parentFrame();
        Thread.sleep(3000);
        By nameFrame = By.xpath("*//iframe[contains(@id,'card-fields-name')]");
        driver.switchTo().frame(driver.findElement(nameFrame));
        fillName();
        driver.switchTo().parentFrame();

        Thread.sleep(3000);
        By expiryFrame = By.xpath("*//iframe[contains(@id,'card-fields-expiry')]");
        driver.switchTo().frame(driver.findElement(expiryFrame));
        fillExpiry();
        driver.switchTo().parentFrame();
        Thread.sleep(3000);
        By codeFrame = By.xpath("*//iframe[contains(@id,'card-fields-verification_value')]");
        driver.switchTo().frame(driver.findElement(codeFrame));
        fillCode();
        driver.switchTo().parentFrame();
        Thread.sleep(3000);
    }
}
