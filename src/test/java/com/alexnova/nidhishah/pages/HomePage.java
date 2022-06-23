package com.alexnova.nidhishah.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/*
This Class is for the HomePage interaction and actions.
 */
public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By accountButton = By.linkText("Account");

    public LoginPage clickAccount(){
        driver.findElement(accountButton).click();
        return new LoginPage(driver);
    }

    By searchBox = By.xpath("//*[@id='ispbxii_0']");

    public SearchPage enterSearch(String arg){
        driver.findElement(searchBox).sendKeys(arg+ Keys.ENTER);
        return new SearchPage(driver);
    }
}
