package com.alexnova.nidhishah.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By quantityField = By.xpath("//input[contains(@id,'updates')]");
    //By quantity = By.cssSelector("#updates_40723160727605")
    public  void increaseQuantity(String arg){
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(arg+ Keys.ENTER);
    }

    By finalPrice = By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/table/tbody/tr/td[4]/span/span");
    public String getFinalPrice(){
        return driver.findElement(finalPrice).getText();
    }

    By productDescription = By.xpath("//*[@class='cart-title']/a");
    public String getProductDescription(){
        return driver.findElement(productDescription).getText();
    }

    By updateCart = By.xpath("*//input[@value='Update Cart']");
    public void clickUpdateCart(){
        driver.findElement(updateCart).click();

    }

    By noItemMessage = By.xpath("//*[@id=\"shopify-section-cart\"]/section/p");
    public String getNoItemMessage(){
        return driver.findElement(noItemMessage).getText();
    }


    public String getQuantity() {
    return driver.findElement(quantityField).getAttribute("value");
    }

    By checkoutButton = By.xpath("*//button[@name='checkout']");
    public CheckoutPage clickCheckout(){
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }

}
