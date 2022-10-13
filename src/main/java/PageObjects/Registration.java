package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.UserManager;

public class Registration extends Base implements Runnable {

    WebDriver driver;

    private final UserManager userManager;

    String email;
    String password;
    String name;
    private final By mainFieldName = By.xpath(".//div[@class='Auth_login__3hAey']/h2");
    private final By registrationNameInput = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By registrationEmailInput = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By registrationPasswordInput = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    private final By applyRegistrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By notCorrectPasswordField = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By existingUserField = By.xpath(".//p[@class='input__error text_type_main-default']");

    public Registration(WebDriver driver) {
        super(driver);
        this.driver = driver;
        final String url = "https://stellarburgers.nomoreparties.site/";
        driver.get(url);
        userManager = new UserManager();
        waitForLoadHomePage();
    }

    public void fillRegistrationFields() {
        driver.findElement(registrationNameInput).sendKeys(name);
        driver.findElement(registrationEmailInput).sendKeys(email);
        driver.findElement(registrationPasswordInput).sendKeys(password);
    }

    public void clickApplyRegistration(){
        driver.findElement(applyRegistrationButton).click();
    }

    public void registerUser(String email, String password, String name) {
        setUserData(email, password, name);
        clickRegistrationButton();
        fillRegistrationFields();
        clickApplyRegistration();
    }

    private void setUserData(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getMainFieldName() {
        waitForPrivateAccountPage();
        return driver.findElement(mainFieldName).getText();
    }

    public String checkPasswordMistakeMessage(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(notCorrectPasswordField));
        return driver.findElement(notCorrectPasswordField).getText();
    }

    public String checkExistingUserMessage(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(existingUserField));
        return driver.findElement(existingUserField).getText();
    }

    @Override
    public void run() {
        // Delete user through API
        userManager.deleteUser(email, password);
    }
}
