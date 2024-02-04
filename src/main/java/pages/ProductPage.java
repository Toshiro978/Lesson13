package pages;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ProductPage {
    private final SelenideElement buttonAddCartSauceLabsBikeLight = $("#add-to-cart-sauce-labs-bike-light");
    private final SelenideElement butonAddCartSauceLabsOnesie = $("#add-to-cart-sauce-labs-onesie");
    private final SelenideElement buttonShoppingCart = $("#shopping_cart_container");
    public void addToCartSauceLabsBikeLight() {
        buttonAddCartSauceLabsBikeLight.click();
    }
    public void addToCartSauceLabsOnesie() {
        butonAddCartSauceLabsOnesie.click();
    }
    public void openShoppingCart() {
        buttonShoppingCart.click();
    }
}
