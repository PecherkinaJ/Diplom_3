package praktikum;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserManager {

    private final String[] email = {"bestMail_2", "worstMail_2", "justAMail_2", "noMail_2", "MailMail_2", "funnyMail_2", "sadMail_2"};
    private final String[] password = {"bestPass", "Password", "pAssworD", "YouShallNotPass", "boringpassword", "123456798", "qwerty"};
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
        User user = new User(email, password);
        Response resp = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(loginUserAPI);

        String tempToken = resp.then().extract().path("accessToken");
        String token = tempToken.replace("Bearer ", "");

        given()
                .auth().oauth2(token)
                .delete(deleteUserAPI);
        System.out.println("Пользователь удален");
    }

    @Step("Create user with API")
    public void createUser(String email, String password, String name) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        String createAPI = "/api/auth/register";
        System.out.println("Данные пользователя: " + email + ", " + password + ", " + name);
        CreateUser user = new CreateUser(email, password, name);
        Response resp = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(createAPI);
        if (resp.getStatusCode()==200) System.out.println("Пользователь создан");
        else System.out.println("Пользователь не создан, код ответа: " + resp.getStatusCode() +
                ", ответ: " + resp.getBody().asString());
    }

}
