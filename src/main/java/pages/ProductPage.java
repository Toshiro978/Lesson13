package pages;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ProductPage {
    ElementsCollection elements = $$(".inventory_item_description");

    private final SelenideElement buttonShoppingCart = $("#shopping_cart_container");
    public void addToCartWithPrice(String price) {

       $("#"+takeButtonIdByprice(price)+"").click();
    }

    public void openShoppingCart() {
        buttonShoppingCart.click();
    }
    public String takeButtonIdByprice(String price){
        SelenideElement targetElement = null;
        String id =null;
        for (SelenideElement element : elements){
            if(element.getText().contains(price)){
                targetElement = element;
                 id = targetElement.$(".btn.btn_primary").getAttribute("id");
                break;
            }
        }
        return id;
    }
}
