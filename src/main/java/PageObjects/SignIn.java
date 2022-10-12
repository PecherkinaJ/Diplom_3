package PageObjects;

import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.UserManager;

public class SignIn extends Base {
    WebDriver driver;
    UserManager userManager;

    private final String url = "https://stellarburgers.nomoreparties.site/";

    private final By privateAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By signInAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By signInForm = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");

    private final By signInEmailInput = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By signInPasswordInput = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    private final By enterButton = By.xpath(".//button[text()='Войти']");

    private final By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

    private final By collectBurgerHeader = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/h1");

    private String email;
    private String password;

    public SignIn(WebDriver driver) {
        super(driver);
        this.driver = driver;
        final String url = "https://stellarburgers.nomoreparties.site/";
        driver.get(url);
        waitForLoadHomePage();
        userManager = new UserManager();
    }

    public void setUserData(String email, String password, String name){
        this.email = email;
        this.password = password;
        userManager.createUser(email, password, name);
    }

    public void fillFieldsAndClickEnterButton(){
        driver.findElement(signInEmailInput).sendKeys(email);
        driver.findElement(signInPasswordInput).sendKeys(password);
        driver.findElement(enterButton).click();
    }

    public boolean collectBurgerHeaderDisplayed(){
        return driver.findElement(collectBurgerHeader).isDisplayed();
    }


}
