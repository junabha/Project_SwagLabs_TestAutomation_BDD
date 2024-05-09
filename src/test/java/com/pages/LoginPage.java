package com.pages;

import com.runner.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

import static org.junit.Assert.assertEquals;


public class LoginPage {
    private WebDriverFactory webdriverFactory;
    private BasePage basePage;
//    public static WebDriver driver;
    private static String configFile="/projectConfig.properties";
    public static Properties properties;


    @FindBy(xpath = "//input[@data-test='username']")
    public WebElement userNameText;

    @FindBy(xpath = "//input[@data-test='password']")
    public WebElement userPasswordText;

    @FindBy(xpath = "//input[@id='login-button' and @value='Login']")
    public WebElement loginButton;


    public LoginPage(WebDriverFactory webdriverFactory) throws Exception {
        this.webdriverFactory = webdriverFactory;
        basePage = new BasePage(webdriverFactory);
        properties = new Properties();
        properties.load(properties.getClass().getResourceAsStream(configFile));
        PageFactory.initElements(getWebDriver(), this);

    }

    public void navigateToURL()
    {
        getWebDriver().get(properties.getProperty("application.url"));
        String actualTitle = getWebDriver().getTitle();
        String expectedTitle = "Swag Labs";
        assertEquals("Verified Application Title",expectedTitle,actualTitle);
    }

    public void authenticateUser(String uName)
    {
        String uPassword= properties.getProperty(uName);
//        System.out.println("userName :"+uName);
//        System.out.println("uPassword :"+uPassword);
        basePage.sendKeysToWebElement(userNameText,uName);
        basePage.sendKeysToWebElement(userPasswordText,uPassword);
    }

    public void clickButton(String buttonName)
    {
        WebElement buttonElement=null;
        try {
            buttonElement = webdriverFactory.getWebDriver().findElement(By.xpath("//input[@value='" + buttonName + "' and @type='submit']"));
        }catch(Exception ex)
        {
            buttonElement = webdriverFactory.getWebDriver().findElement(By.xpath("//a//span[@data-test='"+buttonName+"']"));
        }
        basePage.waitAndClickOnElement(buttonElement);
    }



    private WebDriver getWebDriver()
    {
        return webdriverFactory.getWebDriver();
    }

}
