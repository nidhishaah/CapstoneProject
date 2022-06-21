package com.alexnova.nidhishah.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver=driver;
    }


    By firstNameField = By.name("customer[first_name]");

    public void fillFirstNameField(String email){
        driver.findElement(firstNameField).sendKeys(email);
    }

    By lastNameField = By.name("customer[last_name]");

    public void fillLastNameField(String email){
        driver.findElement(lastNameField).sendKeys(email);
    }

    By emailField = By.name("customer[email]");

    public void fillEMailField(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    By passwordField = By.name("customer[password]");

    public void fillPasswordField(String email){
        driver.findElement(passwordField).sendKeys(email);
    }

    By registerButton = By.xpath("//*[@id=\"create_customer\"]/div[5]/input");
    public LoginPage clickRegisterButton(){
        driver.findElement(registerButton).click();
        return new LoginPage(driver);

    }

}
