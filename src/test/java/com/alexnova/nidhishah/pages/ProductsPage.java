package com.alexnova.nidhishah.pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    By productPrice= By.cssSelector("#shopify-section-product > section > div > div.product-details-wrapper > div > p > span.product-price-minimum.money.notranslate");
//    By productPrice = By.xpath("//span[@data-vitals-cc-usd='$34.95 USD']");
    public String getStripedShoesPrice(){
        return driver.findElement(productPrice).getText();
    }


    By sizeRadioButton = By.xpath("//*[@id=\"bcpo-select-option-0\"]/div[5]/label");
    public void selectSize(){
        driver.findElement(sizeRadioButton).click();
    }

    By colorRadioButton = By.xpath("//*[@id=\"bcpo-select-option-1\"]/div[3]/label");
    public void selectColor(){
        driver.findElement(colorRadioButton).click();
    }

   // By addToCartButton =  By.xpath("*//form[contains(@id,'product_form')]/div[4]/input");
    By addToCartButton=By.xpath("*//input[@value='Add to cart']");
    public void clickAddToCart(){
        driver.findElement(addToCartButton).click();
    }

    By cartButton = By.xpath("//*[@id=\"shopify-section-header\"]/section/header/div[2]/div[4]/a/span[1]");
    public CartPage clickCart(){
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }

}
