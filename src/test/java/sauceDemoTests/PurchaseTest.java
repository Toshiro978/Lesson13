package sauceDemoTests;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.conditions.Visible;
import config.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PurchaseTest {

    // private WebDriver driver;

    private final String homeUrl = "https://www.saucedemo.com/";

    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    InformationPage informationPage = new InformationPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    FinishPage finishPage = new FinishPage();

    @BeforeTest
    public void setUpBrowser() throws MalformedURLException {
         Browser.configSelenoid();
        // Browser.setBrowser();
    }
    @Test
    public void happyPathTest() {
        open(homeUrl);
        loginPage.setLogin("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        productPage.addToCartWithPrice("$9.99");
        productPage.addToCartWithPrice("$7.99");
        productPage.openShoppingCart();
        cartPage.checkItemByPrice("$9.99");
        cartPage.checkItemByPrice("$7.99");
        cartPage.clickCheckoutButton();
        informationPage.setFirstName("TestName");
        informationPage.setLastName("TestLastName");
        informationPage.setPostalCode("11111");
        informationPage.clickContinueButton();
        checkoutPage.calculateTaxPercentage();
        checkoutPage.clickFinishButton();
        finishPage.waitForCompletion("Thank you for your order!");
    }

    @AfterTest
    public void CloseUpBrowser() {
        Browser.closeBrowser();
    }

}
