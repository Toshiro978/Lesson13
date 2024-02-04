package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {


    private final SelenideElement userNameField =  $("#user-name");
    private final SelenideElement passwordUserField =  $("#password");
    private final SelenideElement loginButton =  $("#login-button");


    public void setLogin(String username) {
        userNameField.setValue(username);
    }
    public void setPassword(String password) {
        passwordUserField.setValue(password);
    }
    public void clickLoginButton() {
        loginButton.click();
    }


}