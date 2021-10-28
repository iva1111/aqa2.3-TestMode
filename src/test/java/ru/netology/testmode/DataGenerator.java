package ru.netology.testmode;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.Locale;
import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static Faker faker = new Faker(new Locale("fr"));

    private DataGenerator() {
    }

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static void setNewUser(User registration) {
        given()
                .spec(requestSpecification) // указываем, какую спецификацию используем
                .body(registration) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200);
    }


    public static User getRegisteredUser(String status) {
        String login = faker.name().fullName();
        String password = faker.internet().password();
        User registration = new User(login, password, status);
        setNewUser(registration);
        return registration;
    }

    public static String getNewLogin(){
        return faker.name().fullName();
    }

    public static String getNewPassword(){
        return faker.internet().password();
    }

}