package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import pages.ProductPage;

public class CartPage {

    private final SelenideElement itemOne = $("#item_0_title_link .inventory_item_name");
    private final SelenideElement itemTwo = $("#item_2_title_link .inventory_item_name");
    private final SelenideElement itemOnePrice = $("#cart_contents_container > div > div.cart_list > div:nth-child(3) > div.cart_item_label > div.item_pricebar > div");
    private final SelenideElement itemTwoPrice = $("#cart_contents_container > div > div.cart_list > div:nth-child(4) > div.cart_item_label > div.item_pricebar > div");
    private final SelenideElement checkOutButton = $("#checkout");


    public void verifyItem1Tittle(String title) {
        itemOne.shouldHave(Condition.text(title));

    }

    public void verifyItem2Tittle(String title) {
        itemTwo.shouldHave(Condition.text(title));

    }

    public void verifyItem1Price(String price) {

        itemOnePrice.shouldHave(Condition.text(price));

    }

    public void verifyItem2Price(String price) {

        itemTwoPrice.shouldHave(Condition.text(price));

    }

    public void clickCheckoutButton() {
        checkOutButton.click();
    }
}
