package com.alexnova.nidhishah.pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
This Page displays the product and all product information.
 This Class has all actions and interactions with the Product page.
 */
public class ProductsPage {

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    By productPrice= By.cssSelector("#shopify-section-product > section > div > div.product-details-wrapper > div > p > span.product-price-minimum.money.notranslate");

    public String getStripedShoesPrice(){
        return driver.findElement(productPrice).getText();
    }


    By sizeRadioButton = By.xpath("//*[@id=\"bcpo-select-option-0\"]/div[5]/label");
    public void selectSize(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(sizeRadioButton));
        driver.findElement(sizeRadioButton).click();
    }

    By colorRadioButton = By.xpath("//*[@id=\"bcpo-select-option-1\"]/div[3]/label");
    public void selectColor(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(colorRadioButton));
        driver.findElement(colorRadioButton).click();
    }


    By addToCartButton=By.xpath("*//input[@value='Add to cart']");
    public void clickAddToCart(){
        driver.findElement(addToCartButton).click();
    }

    By cartButton = By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div[2]/div[4]/a/span[1]");
    public CartPage clickCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(cartButton)));
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }

}
