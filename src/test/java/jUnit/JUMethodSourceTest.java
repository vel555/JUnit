package jUnit;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.sun.tools.javac.resources.CompilerProperties.Fragments.Local;

public class JUMethodSourceTest {
    static Stream<Arguments> testSelenide() {
        return Stream.of(
                Arguments.of(Local.EN, List.of("Quick start", "Docs","FAQ","Blog","Javadoc","Users","Quotes")),
                Arguments.of(Local.RU, List.of("С чего начать?", "Док","ЧАВО","Блог","Javadoc","Пользователи","Отзывы"))
        );
    }

    @MethodSource("testSelenide")
    @ParameterizedTest(name = " name")
    void testSelenideOne(Local local, List<String> buttons){
        open("https://ru.selenide.org/");
        $$("#languages a").find(Condition.text(local.name())).click();
        $$(".main-menu-pages a")
                .filter(Condition.visible)
                .shouldHave(CollectionCondition.texts(buttons));
    }
}
