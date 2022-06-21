package com.alexnova.nidhishah.pages;

import com.alexnova.nidhishah.pages.RegisterPage;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By registerButton = By.xpath("*//div[@class='new-customer']/a");

    public RegisterPage clickRegister(){

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0,500)");

        System.out.println("Im here");
        driver.findElement(registerButton).click();
        return new RegisterPage(driver);
    }

    By pageTitle = By.xpath("*//h1[@class='page-title']"); // Doesnt work in firefox
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
        return driver.findElement(errorMsg).getText();
    }


}
