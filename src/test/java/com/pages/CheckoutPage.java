package com.pages;

import com.runner.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage {
    private WebDriverFactory webdriverFactory;
    private BasePage basePage;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    public WebElement subTotalLabel;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    public WebElement totalLabel;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    public WebElement taxLabel;

    public CheckoutPage(WebDriverFactory webdriverFactory) throws Exception {
        this.webdriverFactory = webdriverFactory;
        basePage = new BasePage(webdriverFactory);
        PageFactory.initElements(getWebDriver(), this);

    }

    public void clickButtonByName(String buttonName)
    {
        WebElement buttonElement=null;
        buttonElement = webdriverFactory.getWebDriver().findElement(By.xpath("//button[text()='" + buttonName + "']"));
        basePage.clickOnElementByName(buttonElement);
    }

    public void verifyProductTotalAmount() throws Exception
    {
        WebElement buttonElement=null;
        double sumAmount=0;
        String subTotalString = subTotalLabel.getText();
        String actualtotalAmountString = totalLabel.getText();
        String taxString = taxLabel.getText();
        subTotalString = subTotalString.replaceAll("[a-zA-Z-$:^]*", "").trim();
        actualtotalAmountString = actualtotalAmountString.replaceAll("[a-zA-Z-$:^]*", "").trim();
        taxString = taxString.replaceAll("[a-zA-Z-$:^]*", "").trim();

        System.out.println(" subTotalLabel = "+subTotalString);
        System.out.println("totalLabel= "+actualtotalAmountString);
        System.out.println("taxLabel= "+taxString);
        List<WebElement> webElementList = webdriverFactory.getWebDriver().findElements(By.xpath("//div[@class='inventory_item_price']"));
        for(WebElement element:webElementList)
        {
            String productAmountText=element.getText().trim();
            productAmountText = productAmountText.replaceAll("[a-zA-Z-$:^]*", "");
            double productAmountdouble = Double.parseDouble(productAmountText);
            sumAmount=sumAmount+productAmountdouble;
         }

        System.out.println("Sum "+sumAmount);
        double expectedSumAmount=sumAmount+Double.parseDouble(taxString);
        double actualSumAmount=Double.parseDouble(actualtotalAmountString);
        System.out.println("Actual Sum Amount "+actualtotalAmountString);
        System.out.println("expected Sum Amount "+expectedSumAmount);

        Assert.assertEquals("Verify Expected and Actual total amount",expectedSumAmount,actualSumAmount,0);
    }

    public void enterDetails(List<List<String>> dataList) {

        System.out.println("***************************************");
        String value = null;
        String fieldName = null;
        WebElement field=null;

        for (int i = 1; i < dataList.size(); i++) {
            fieldName = dataList.get(i).get(0);
            value =  dataList.get(i).get(1);
            System.out.println("fieldName Name :" + fieldName);
            System.out.println("value Name :" + value);
            field = webdriverFactory.getWebDriver().findElement(By.xpath("//input[@placeholder='"+fieldName+"']"));
            basePage.sendKeysToWebElement(field,value);
        }

    }
    private WebDriver getWebDriver()
    {
        return webdriverFactory.getWebDriver();
    }

}
