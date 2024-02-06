package pages;

import com.codeborne.selenide.*;
import dev.failsafe.internal.util.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class CartPage {
    private final SelenideElement checkOutButton = $("#checkout");

    ElementsCollection elements = $$(".cart_item");


    public void clickCheckoutButton() {
        checkOutButton.click();
    }

    public SelenideElement findItemByPrice(String price) {
        SelenideElement targetElement = null;
        for (SelenideElement element : elements) {
            if (element.$("div.item_pricebar > div").getText().contains(price)) {
                targetElement = element;
                break;
            }else {
                targetElement = $("div.item_pricebar > div"); //Пробуємо повернути перший елемент з корзини.
            }
        }
        return targetElement;

    }

    public void checkItemByPrice(String price) {
        findItemByPrice(price).shouldHave(Condition.text(price));
    }

}
