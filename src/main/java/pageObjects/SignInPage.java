package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.UserManager;

public class SignInPage extends BasePage {
    WebDriver driver;
    UserManager userManager;

    private final By signInEmailInput = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By signInPasswordInput = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    private final By privateAccountMessage = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By enterLink = By.xpath(".//a[text()='Войти']");
    private final By logOutButton = By.xpath(".//button[text()='Выход']");
    private final By profileButton = By.xpath(".//a[text()='Профиль']");

    private String email;
    private String password;

    public SignInPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
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
        waitForElement(profileButton);
    }

    public boolean isEnterButtonDisplayed() {
        return driver.findElement(enterButton).isDisplayed();
    }

    public boolean isPrivateAccountOpened() {
        return driver.findElement(privateAccountMessage).isDisplayed();
    }
}
