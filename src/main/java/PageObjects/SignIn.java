package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.UserManager;

public class SignIn extends Base {
    WebDriver driver;
    UserManager userManager;

    private final String url = "https://stellarburgers.nomoreparties.site/";

    private final By privateAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    private final By signInEmailInput = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By signInPasswordInput = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    private final By privateAccountMessage = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By enterLink = By.xpath(".//a[text()='Войти']");
    private final By logOutButton = By.xpath(".//button[text()='Выход']");

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

    public void setUserData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        userManager.createUser(email, password, name);
    }

    public void fillFieldsAndClickEnterButton() {
        driver.findElement(signInEmailInput).sendKeys(email);
        driver.findElement(signInPasswordInput).sendKeys(password);
        driver.findElement(enterButton).click();
        waitForLoadHomePage();
    }

    public void clickEnterLink() {
        driver.findElement(enterLink).click();
    }

    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }

    public void enterToPrivateAccount() {
        driver.findElement(privateAccountButton).click();
        waitForElement(By.xpath(".//a[text()='Профиль']"));
    }

    public boolean isEnterButtonDisplayed() {
        return driver.findElement(enterButton).isDisplayed();
    }

    public boolean isPrivateAccountOpened() {
        return driver.findElement(privateAccountMessage).isDisplayed();
    }
}
