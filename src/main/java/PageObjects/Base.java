package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    WebDriver driver;
    private final By registrationForm = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Регистрация']");
    private final By signInForm = By.xpath(".//h2[text()='Вход']");
    private final By registrationButton = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private final By privateAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By signInAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By collectBurgerHeader = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/h1");

    private final By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

    public Base (WebDriver driver){
        this.driver = driver;
    }
    public void waitForLoadHomePage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h1[text()='Соберите бургер']")));
    }

    public void waitForPrivateAccountPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(signInForm));
    }

    public void waitForRegistrationPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registrationForm));
    }


    public void clickPrivateAccountButton() {
        driver.findElement(privateAccountButton).click();
        waitForPrivateAccountPage();
    }

    public void clickSignInAccountButton() {
        driver.findElement(signInAccountButton).click();
        waitForPrivateAccountPage();
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        waitForRegistrationPage();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordButton).click();
    }

    public void waitForElement(By element){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public boolean collectBurgerHeaderDisplayed() {
        return driver.findElement(collectBurgerHeader).isDisplayed();
    }
}
