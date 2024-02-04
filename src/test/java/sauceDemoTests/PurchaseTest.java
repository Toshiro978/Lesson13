package sauceDemoTests;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.conditions.Visible;
import config.Browser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class PurchaseTest {

    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    InformationPage informationPage = new InformationPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    FinishPage finishPage = new FinishPage();

    @BeforeTest
    public void setUpBrowser() {
        Browser.setBrowser();
    }

    @Test
    public void LoginTest(){
        loginPage.openLoginPage();
        loginPage.setLogin("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    @Test(dependsOnMethods = "LoginTest")
    public void AddItemInCartTest(){
        productPage.addToCartSauceLabsBikeLight();
        productPage.addToCartSauceLabsOnesie();
    }

    @Test(dependsOnMethods = "AddItemInCartTest")
    public void CartItemTest(){
        productPage.openShoppingCart();
        cartPage.verifyItem1Tittle("Sauce Labs Bike Light");
        cartPage.verifyItem2Tittle("Sauce Labs Onesie");
        cartPage.verifyItem1Price( "$9.99");
        cartPage.verifyItem2Price("$7.99");
    }

    @Test(dependsOnMethods = "CartItemTest")
    public void InformationPageTest(){
        cartPage.clickCheckoutButton();
        informationPage.setFirstName("TestName");
        informationPage.setLastName("TestLastName");
        informationPage.setPostalCode("11111");

    }

    @Test(dependsOnMethods = "InformationPageTest")
    public void CheckoutTaxTest(){
        informationPage.clickContinueButton();
        checkoutPage.calculateTaxPercentage();

    }

    @Test(dependsOnMethods = "CheckoutTaxTest")
    public void FinalPageTest(){
        checkoutPage.clickFinishButton();
        finishPage.waitForCompletion("Thank you for your order!");
    }
}
