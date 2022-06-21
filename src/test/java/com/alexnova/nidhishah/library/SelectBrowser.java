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

//    public  String getBrowserInfo(){
//        return
//    }

    public static WebDriver StartBrowser(String browsername)
    {

        String fileUrl = System.getProperty("user.dir");

        // ---If the browser is Firefox----
        if(browsername.equalsIgnoreCase("Firefox"))
        {
            // Set the path for geckodriver.exe
            System.setProperty("webdriver.gecko.driver",fileUrl+"\\src\\test\\resources\\drivers\\geckodriver1.exe ");
            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if(browsername.equalsIgnoreCase("Chrome"))
        {
            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver" , fileUrl+"\\src\\test\\resources\\drivers\\chromedriver1.exe");
//              Code for incognito mode
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--incognito");
//            //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//           DesiredCapabilities.htmlUnit().setCapability(ChromeOptions.CAPABILITY,options);
            driver = new ChromeDriver();
        }
        // ----If the browser is  EdgeIE----
        else if(browsername.equalsIgnoreCase("EdgeExplore"))
        {
            // Set the path for IEdriver
            System.setProperty("webdriver.edge.driver", fileUrl+"\\src\\test\\resources\\drivers\\msedgedriver2.exe");
            // Instantiate a EdgeDriverclass.
           // EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver();
        }
        //driver.manage().window().maximize();
        return driver;
    }

}
