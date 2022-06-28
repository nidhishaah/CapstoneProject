package com.alexnova.nidhishah.library;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SelectBrowser {

    static private WebDriver driver;


    public static WebDriver StartBrowser(String browsername)
    {

        String fileUrl = System.getProperty("user.dir");

        // ---If the browser is Firefox----
        if(browsername.equalsIgnoreCase("Firefox"))
        {
            // Set the path for geckodriver.exe
            System.setProperty("webdriver.gecko.driver",fileUrl+"\\src\\test\\resources\\drivers\\geckodriver1.exe ");
           //Instantiate a Gecko driver
            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if(browsername.equalsIgnoreCase("Chrome"))
        {
            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver" , fileUrl+"\\src\\test\\resources\\drivers\\chromedriver1.exe");
            // Instantiate a Chrome Driverclass.
            driver = new ChromeDriver();
        }
        // ----If the browser is  EdgeIE----
        else if(browsername.equalsIgnoreCase("EdgeExplore"))
        {
            // Set the path for IEdriver
            System.setProperty("webdriver.edge.driver", fileUrl+"\\src\\test\\resources\\drivers\\msedgedriver3.exe");
            // Instantiate a EdgeDriverclass.
            driver = new EdgeDriver();
        }

        return driver;
    }

}
