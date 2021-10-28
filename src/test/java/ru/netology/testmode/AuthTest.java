package ru.netology.testmode;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    private static Faker faker = new Faker(new Locale("fr"));

    @BeforeEach
    void shouldOpenWeb() {
        open("http://localhost:9999");

    }

    @Test
    void shouldRegisteredActiveUser() {
        User userInfo = DataGenerator.getRegisteredUser("active");
        $("[data-test-id=login] [class = input__control]").setValue(userInfo.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(userInfo.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldLoginInvalidLogin() {
        User userInfo = DataGenerator.getRegisteredUser("active");
        $("[data-test-id=login] [class = input__control]").setValue(DataGenerator.getNewLogin());
        $("[data-test-id=password] [class = input__control]").setValue(userInfo.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldLoginInvalidPassword() {
        User userInfo = DataGenerator.getRegisteredUser("active");
        $("[data-test-id=login] [class = input__control]").setValue(userInfo.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(DataGenerator.getNewPassword());
        $(byText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldLoginBlockedUser() {
        User userInfo = DataGenerator.getRegisteredUser("blocked");
        $("[data-test-id=login] [class = input__control]").setValue(userInfo.getLogin());
        $("[data-test-id=password] [class = input__control]").setValue(userInfo.getPassword());
        $(byText("Продолжить")).click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}