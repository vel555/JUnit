package jUnit;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class JUnitTest {
    @BeforeEach
    void openGoogle(){
        open("https://www.google.com/");
    }

    @Test
    @DisplayName("открыть страницу https://selenide.org")
    void openPageSelenide() {
        $("[name = q]").setValue("selenide").pressEnter();
        $("#search").shouldHave(text("https://selenide.org"));
    }

    @CsvSource({
            "selenide, https://selenide.org",
            "Allure testops, https://qameta.io"
    })
    @ParameterizedTest(name = "url {1} must be on page {0}")
    void demoCsvSourceTest(String prodName, String url) {
        $("[name = q]").setValue(prodName).pressEnter();
        $("#search").shouldHave(text(url));
    }

    @CsvFileSource(resources = "/data.csv")
    @ParameterizedTest(name = "url {1} must be on page {0}")
    void demoCsvFileSourceTest(String prodName, String url) {
        $("[name = q]").setValue(prodName).pressEnter();
        $("#search").shouldHave(text(url));
    }

    @ValueSource(
            strings = {"allure testops", "selenide"}
    )
    @ParameterizedTest(name = "collection must be > 3")
    void demoValueSourceTest(String prodName) {
        $("[name = q]").setValue(prodName).pressEnter();
        $$("div[class = g]").shouldHave(CollectionCondition.sizeGreaterThan(3));
    }
}
