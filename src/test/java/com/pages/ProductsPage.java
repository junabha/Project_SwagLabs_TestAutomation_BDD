package com.pages;

import com.runner.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    private WebDriverFactory webdriverFactory;
    private BasePage basePage;

    public ProductsPage(WebDriverFactory webdriverFactory) throws Exception {
        this.webdriverFactory = webdriverFactory;
        basePage = new BasePage(webdriverFactory);
        PageFactory.initElements(getWebDriver(), this);

    }

    public void verifyTextVisible(String textOnPage) {
        boolean flag = false;
        WebElement webElement=null;
        try {
             webElement = webdriverFactory.getWebDriver().findElement(By.xpath("//span[text()='" + textOnPage + "']"));
        }catch(Exception ex)
        {
            webElement = webdriverFactory.getWebDriver().findElement(By.xpath("//div[text()='" + textOnPage + "']"));
        }
        flag = basePage.waitUntillElmentIsVisible(webElement);
        if (flag) {
            Assert.assertEquals("Element is present on Page", textOnPage, webElement.getText());

        } else {
            Assert.assertEquals("Element is not present on Page", textOnPage, webElement.getText());
        }
    }

    public void verifyTextNotVisible(String textOnPage) {
        List<WebElement> list =null;
        try {
          list = webdriverFactory.getWebDriver().findElements(By.xpath("//span[text()='" + textOnPage + "']"));
        }catch(Exception ex)
        {
            list = webdriverFactory.getWebDriver().findElements(By.xpath("//div[text()='" + textOnPage + "']"));
        }
        System.out.println("list.size() = "+list.size());
        if (list.size()<=0) {
            Assert.assertEquals("Element was not present on Page", true, true);

        } else {
            Assert.assertEquals("Element is present on Page", true, false);
        }
    }

    public void addProducts(List<List<String>> productsList) {
        Actions action=new Actions(webdriverFactory.getWebDriver());

        String productName=null;
        String fieldName=null;
        WebElement webElement=null;
        for (int i = 1; i < productsList.size(); i++) {
            fieldName = productsList.get(1).get(0);
            productName = productsList.get(i).get(0);

            System.out.println("Product in cart page :"+productName);
            webElement = webdriverFactory.getWebDriver().findElement(By.xpath("//div[text()='"+productName+"']//ancestor::div[@class='inventory_item_description']//button[text()='Add to cart']"));
            action.moveToElement(webElement).click().build().perform();

        }


    }

 private WebDriver getWebDriver()
    {
        return webdriverFactory.getWebDriver();
    }
}
