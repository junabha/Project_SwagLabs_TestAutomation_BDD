package com.pages;

import com.runner.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    private WebDriverFactory webdriverFactory;
    private BasePage basePage;
    public CartPage(WebDriverFactory webdriverFactory) throws Exception {
        this.webdriverFactory = webdriverFactory;
        basePage = new BasePage(webdriverFactory);
        PageFactory.initElements(getWebDriver(), this);

    }

    public void verifyProductAddedInCart(List<List<String>> productsList) {
        Actions action = new Actions(webdriverFactory.getWebDriver());

        String productName = null;
        String fieldName = null;
        WebElement webElement = null;
        for (int i = 1; i < productsList.size(); i++) {
            fieldName = productsList.get(1).get(0);
            productName = productsList.get(i).get(0);

            System.out.println("Product in checkout page :" + productName);
            webElement = webdriverFactory.getWebDriver().findElement(By.xpath("//div[text()='"+productName+"']//ancestor::div[@class='cart_item']//button[text()='Remove']"));

            if (webElement.isDisplayed()) {
                Assert.assertEquals("Prouct got added in a list", true,true);
            } else {
                Assert.assertEquals("Prouct was not added in a list",true, false);
            }
        }
    }


    public void removeProduct(List<List<String>> productsList) {
        Actions action = new Actions(webdriverFactory.getWebDriver());

        String productName = null;
        String fieldName = null;
        WebElement webElement = null;
        for (int i = 1; i < productsList.size(); i++) {
            fieldName = productsList.get(1).get(0);
            productName = productsList.get(i).get(0);

            System.out.println("Remove Product on cart Page :" + productName);
            webElement = webdriverFactory.getWebDriver().findElement(By.xpath("//div[text()='"+productName+"']//ancestor::div[@class='cart_item']//button[text()='Remove']"));

            basePage.clickOnElementByName(webElement);
        }
    }



    private WebDriver getWebDriver()
    {
        return webdriverFactory.getWebDriver();
    }
}
