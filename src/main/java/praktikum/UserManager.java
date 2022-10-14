package praktikum;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserManager {

//    private final String[] email = {"bestMail", "worstMail"};
    private final String[] email = {"33bestMail", "33worstMail", "33justAMail", "33noMail", "33MailMail", "33funnyMail", "33sadMail"};
    private final String[] password = {"Password", "qwerty"};
    private final String[] name = {"NoName", "HaveName", "JustAName", "Nameless", "BestName", "Sam", "Dean"};

    @Step("Get one of the email")
    public String getEmail() {
        return email[new Random().nextInt(email.length)] + "@yandex.ru";
    }

    @Step("Get one of the passwords")
    public String getPassword() {
        return password[new Random().nextInt(password.length)];
    }

    @Step("Get one of the names")
    public String getName() {
        return name[new Random().nextInt(name.length)];
    }

    @Step("Delete current user from DataBase with API")
    public void deleteUser(String email, String password) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        String loginUserAPI = "/api/auth/login";
        String deleteUserAPI = "/api/auth/user";
        System.out.println("УДАЛЕНИЕ Данные пользователя: " + email + ", " + password);
        User user = new User(email, password);
        System.out.println("USER = " + user.getEmail() + ",  " + user.getPassword());
        Response resp = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(loginUserAPI);
        if (resp.getStatusCode() == 200) {
            System.out.println("RESPONSE = " + resp.getBody().asString());
            String tempToken = resp.then().extract().path("accessToken");
            System.out.println("TEMPtoken = " + tempToken);
            String token = tempToken.replace("Bearer ", "");
            System.out.println("token = " + token);

            resp = given()
                    .auth().oauth2(token)
                    .delete(deleteUserAPI);
            if (resp.getStatusCode() == 202) System.out.println("Пользователь удален");
            else System.out.println(email + " " + password + " не может быть удален");
        } else System.out.println("Пользователь не найден в системе! ОШИБКА "+ resp.getStatusCode() + " " + resp.getBody().asString());
    }

    @Step("Create user with API")
    public void createUser(String email, String password, String name) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        String createAPI = "/api/auth/register";
        System.out.println("РЕГИСТРАЦИЯ Данные пользователя: " + email + ", " + password);
        CreateUser user = new CreateUser(email, password, name);
        Response resp = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(createAPI);
        if (resp.getStatusCode() == 200) System.out.println("Пользователь создан");
        else System.out.println("Пользователь не создан, код ответа: " + resp.getStatusCode() +
                ", ответ: " + resp.getBody().asString());
    }

}
