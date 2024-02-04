package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class InformationPage {
    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameField = $("#last-name");
    private final SelenideElement postCode = $("#postal-code");
    private final SelenideElement continueButton =  $("#continue");


    public void setFirstName(String firstName) {
        firstNameField.setValue(firstName);
    }
    public void setLastName(String lastName) {
        lastNameField.setValue(lastName);
    }
    public void setPostalCode(String postalCode) {
        postCode.setValue(postalCode);
    }
    public void clickContinueButton() {
        continueButton.click();

    }

}
