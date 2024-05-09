package com.stepDefinition;

import com.runner.WebDriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Hooks extends WebDriverFactory {

    @Before
    public void setup()
    {
        System.out.println("Inside setup");
        webDriver=getWebDriverInstance();
    }


    @After
    public void tearDown(Scenario scenario)
    {
        System.out.println("Inside tearDown");
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) webDriver;
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.embed(src, "image/png");
        }

        if(webDriver!=null)
        {
            webDriver.manage().deleteAllCookies();
            webDriver.quit();
            webDriver=null;
        }
    }
}
