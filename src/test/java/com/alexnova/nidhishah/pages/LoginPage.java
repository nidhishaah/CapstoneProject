package com.alexnova.nidhishah.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
Login Page CLass had all actions and interactions with the Login Page.
This Page helps us to Login by giving the email and password.
 */
public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By registerButton = By.xpath("*//div[@class='new-customer']/a");

    public RegisterPage clickRegister(){

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0,500)");

        driver.findElement(registerButton).click();
        return new RegisterPage(driver);
    }

    By pageTitle = By.xpath("*//h1[@class='page-title']");
    public String getTitle(){
        return driver.findElement(pageTitle).getText();
    }

    By emailField = By.name("customer[email]");
    public void enterEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }


    By passwordField = By.name("customer[password]");
    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    By loginButton = By.xpath("//*[@id=\"customer_login\"]/div[3]/input");
    public AccountPage clickLogin(){
        driver.findElement(loginButton).click();
        return new AccountPage(driver);
    }

    By errorMsg = By.xpath("//*[@id='customer_login']/p");
    public String getErrorMsg(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(errorMsg)));
        return driver.findElement(errorMsg).getText();
    }


}
