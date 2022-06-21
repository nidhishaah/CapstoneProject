package com.alexnova.nidhishah.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.Set;

public class BaseTest {

    private WebDriver driver;
    private static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;



    @BeforeSuite
    public void setUpReport(){

        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/screenshots/CapstoneReport_NidhiShah.html");
//        htmlReporter =
//                new ExtentHtmlReporter("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\Shah_Nidhi_Capstone_AlexnNova\\test-output\\screenshots\\CapstoneReport_NidhiShah.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "DEKTOP-34GJ352");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Nidhi Shah");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Capstone Report");
        htmlReporter.config().setReportName("Capstone Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }

    @AfterSuite
    public void tearDown(){
        extent.flush();
        //driver.quit();
    }




}
