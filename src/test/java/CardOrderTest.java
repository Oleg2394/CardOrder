import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void shouldSumbitRequest() {
        open("http://localhost:9999/");
//        SelenideElement form = $("form[class='form form_size_m form_theme_alfa-on-white']");
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(By.className("paragraph")).shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSendIncorrectName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Vasiliy Ivanov");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendIncorrectPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79270000000123");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendIncorrectCheckbox() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $(By.className("button")).click();
        $(".checkbox__text").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldNotSendEmptyName() {
        open("http://localhost:9999/");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendEmptyPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Василий Иванов");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
