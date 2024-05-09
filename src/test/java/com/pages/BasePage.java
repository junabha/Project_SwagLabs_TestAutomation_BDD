package com.pages;

import com.runner.WebDriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private WebDriver driver;
    private WebDriverFactory webdriverFactory;
    protected WebDriverWait wait;

    public BasePage(WebDriverFactory webdriverFactory) {
        this.webdriverFactory = webdriverFactory;
    }

    public void sendKeysToWebElement(WebElement element, String textToSend) {

        try {
            this.waitUntillElmentIsVisible(element);
            System.out.println("**sendKeysToWebElement");
            element.clear();
            element.sendKeys(textToSend);
            System.out.println("Successfully sent the following Keys: " + textToSend);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clickOnElementByName(WebElement element) {
        try {
            System.out.println("**clickOnElementByName**");
            //System.out.println("Successfully Clicked on " +element.getText());
            element.click();

        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public boolean waitUntillElmentIsVisible(WebElement element) {

        try {
            this.wait=new WebDriverWait(webdriverFactory.getWebDriver(),5);
            this.wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean waitAndClickOnElement(WebElement element) {

        try {
            this.wait=new WebDriverWait(webdriverFactory.getWebDriver(),5);
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
