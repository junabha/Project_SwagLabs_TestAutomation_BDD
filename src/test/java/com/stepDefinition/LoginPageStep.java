package com.stepDefinition;

import com.pages.CartPage;
import com.pages.CheckoutPage;
import com.pages.LoginPage;
import com.pages.ProductsPage;
import com.runner.WebDriverFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPageStep {
    public LoginPage loginPage;
    public ProductsPage productPage;
    public CheckoutPage checkoutPage;
    public CartPage cartPage;
    private WebDriverFactory webdriverFactory;
    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    public WebElement subTotalLabel;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    public WebElement taxLabel;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    public WebElement totalSumLabel;

    public LoginPageStep(WebDriverFactory webdriverFactory) throws Exception {
        this.webdriverFactory=webdriverFactory;
        this.loginPage = new LoginPage(webdriverFactory);
        this.productPage = new ProductsPage(webdriverFactory);
        this.checkoutPage=new CheckoutPage(webdriverFactory);
        this.cartPage=new CartPage(webdriverFactory);
    }

    @Given("User Navigate to Swag Labs application")
    public void user_Navigate_to_Swag_Labs_application() {
        loginPage.navigateToURL();
    }

    @When("login with {string} role on Swag Labs application with user {string}")
    public void authenticateUser(String userRole, String userName) {
        loginPage.authenticateUser(userName);
    }

    @When("User clicks on {string} Button")
    public void clicksOnButton(String string) {
        loginPage.clickButton(string);
    }

    @When("User should see {string} on page")
    public void verifyText(String textOnPage) {
        productPage.verifyTextVisible(textOnPage);
    }

    @When("user add below products on Product Page")
    public void addProducts(DataTable dataTable) throws Throwable{
        List<List<String>> rows = dataTable.asLists(String.class);
        productPage.addProducts(rows);
    }

    @When("User should see the below product added on the cart page")
    public void verifyProductAdded(DataTable dataTable) throws Throwable{
        List<List<String>> rows = dataTable.asLists(String.class);
        cartPage.verifyProductAddedInCart(rows);
    }

    @When("User enter following details on Checkout page")
    public void enterDeatils(DataTable dataTable) throws Throwable{
        List<List<String>> rows = dataTable.asLists(String.class);
        checkoutPage.enterDetails(rows);
    }

    @Given("User clicks on {string} Button on page")
    public void clicksOnButtonByName(String string) {
        checkoutPage.clickButtonByName(string);
    }


    @When("User verify price Total of the product on checkout page")
    public void verifyTotalAmount() throws Throwable{
        checkoutPage.verifyProductTotalAmount();
    }

    @When("user remove below products on cart Page")
    public void removeProducts(DataTable dataTable) throws Throwable{
        List<List<String>> rows = dataTable.asLists(String.class);
        cartPage.removeProduct(rows);
    }

}
