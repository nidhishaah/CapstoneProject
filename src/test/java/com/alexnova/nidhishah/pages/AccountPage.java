package com.alexnova.nidhishah.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
This Page is shown on Login. This class shows all interactions with the Account Page
 */
public class AccountPage {

    WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    By heading = By.xpath("*//div[@id='keyboard-nav-3']/h1");
    public String getHeading(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(heading));
        return driver.findElement(heading).getText();
    }

    By homePage = By.xpath("//*[@id='navigation-home']");
    public HomePage clickHomeButton(){
        driver.findElement(homePage).click();
        return new HomePage(driver);
    }

//    By logoutButton = By.linkText("Log out");
//    //By accountButton = By.xpath("//*[@id=\"customer_login_link\"]");
//    public void logout(){
//        WebElement ele = driver.findElement(By.linkText("Account"));
//
////Creating object of an Actions class
//        Actions action = new Actions(driver);
//
////Performing the mouse hover action on the target element.
//        action.moveToElement(ele).perform();
//        //driver.findElement(accountButton).sendKeys(Keys.);
//        driver.findElement(logoutButton).click();
//    }
}
