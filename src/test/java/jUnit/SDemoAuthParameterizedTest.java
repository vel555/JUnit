package jUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class SDemoAuthParameterizedTest {
    static Stream<Arguments> SDemoAuth() {
        return Stream.of(
                Arguments.of("", "", "Epic sadface: Username is required"),
                Arguments.of("standard_user", "", "Epic sadface: Password is required"),
                Arguments.of("", "secret_sauce", "Epic sadface: Username is required"),
                Arguments.of("standard", "secret",
                        "Epic sadface: Username and password do not match any user in this service")
        );
    }

    @MethodSource("SDemoAuth")
    @ParameterizedTest(name = "Saucedemo auth negative test using JUnit5")
    void SDemoAuth(String login, String pass, String error){
        open("https://www.saucedemo.com/");
        $("#user-name").setValue(login);
        $("#password").setValue(pass).pressEnter();
        String textOfMessage = $x("//h3[@data-test = 'error']").getText();
        Assertions.assertEquals(error, textOfMessage, "Error!");
    }
}
