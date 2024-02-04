package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class FinishPage {
    private final SelenideElement completeHeader = $(".complete-header");

    public void waitForCompletion(String header) {
        completeHeader.shouldBe(Condition.visible)
                      .shouldHave(Condition.text(header));
    }
}
