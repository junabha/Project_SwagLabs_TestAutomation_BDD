package com.runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static String configFile="/projectConfig.properties";
    public static WebDriverFactory webDriverFactory;
    public static WebDriver webDriver;
    public static Properties properties;
    private static  boolean isNewDriver=false;

    public WebDriverFactory()
    {
        properties=new Properties();
        try
        {
            properties.load(properties.getClass().getResourceAsStream(configFile));
        }catch(Exception ex)
        {
            System.out.println("could not load the file");
        }
        if(!isNewDriver)
        {
            System.out.println("load isNewDriver = "+isNewDriver);
            webDriver=getWebDriverInstance();
            isNewDriver=true;
            System.out.println("load isNewDriver = "+isNewDriver);
        }
    }

    public static WebDriver getWebDriverInstance()
    {
        //WebDriver webdriverInstance=null;
        //public static WebDriver driver=null;
        if(webDriver==null) {
            String testBrowser=properties.getProperty("browser");
            System.out.println("testBrowser :"+testBrowser);
            if (testBrowser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome_driver_location"));
                ChromeOptions options = new ChromeOptions();
                options.setCapability("useAutomationExtension", false);
                webDriver = new ChromeDriver(options);
            } else if (testBrowser.equals("edge")) {
                System.setProperty("webdriver.edge.driver", properties.getProperty("edge_driver_location"));
                EdgeOptions options = new EdgeOptions();
                options.setCapability("useAutomationExtension", false);
                webDriver = new EdgeDriver();
             }
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        return  webDriver;
    }

    public static WebDriver getWebDriver()

    {
        return webDriver=getWebDriverInstance();
    }

    public Properties getProperties()
    {
        return properties;
    }

}
