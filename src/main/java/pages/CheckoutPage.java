package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    private final SelenideElement subTotalBlock = $(".summary_subtotal_label");
    private final SelenideElement taxBlock = $(".summary_tax_label");
    private final SelenideElement finishButton = $("#finish");


    public float getSubTotal() {
        return Float.parseFloat(subTotalBlock.text().substring(13));
    }

    public float getSubTax() {
        return Float.parseFloat(taxBlock.text().substring(6));
    }

    public void calculateTaxPercentage() {
        float subTotal = getSubTotal();
        float subTax = getSubTax();
        System.out.println("Відсоток податка склав - " + (100 * subTax) / subTotal);
    }

    public void clickFinishButton() {
        finishButton.click();
    }
}
