package com.alexnova.nidhishah.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By noResultsMessage = By.xpath("//*[@id=\"isp_search_results_container\"]/li[1]");
    public String getNoResultsMessage(){
        return driver.findElement(noResultsMessage).getText();
    }

    By getResultMessage = By.xpath("//*[@id=\"isp_header_subtitle\"]");
    public String getSearchResultsMessage(){
        return driver.findElement(getResultMessage).getText();
    }

    By babyStripedShoesLink = By.xpath("//*[@id=\"isp_search_results_container\"]/li[1]/div[2]/a/div");
    public ProductsPage clickOnStripedShoes(){
        driver.findElement(babyStripedShoesLink).click();
        return new ProductsPage(driver);
    }

}
