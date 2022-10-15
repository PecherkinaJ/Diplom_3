import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.RegistrationPage;
import praktikum.UserManager;

import static org.junit.Assert.*;

public class TestRegistration extends BaseTest {
    private WebDriver browser;
    private RegistrationPage reg;
    private String email;
    private String password;
    private String name;
    Driver driver;
    UserManager userManager;

    @Before
    public void setUp() throws Exception {
        String browserName = getParameter("browser");
        driver = new Driver(browserName);
        browser = driver.getDriver();

        reg = new RegistrationPage(browser);

        userManager = new UserManager();
        email = userManager.getEmail();
        password = userManager.getPassword();
        name = userManager.getName();
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    @Test
    public void testRegistrationThroughPrivateAccountButton() {
        reg.clickPrivateAccountButton();
        reg.registerUser(email, password, name);
        userManager.deleteUser(email, password);
        // Check that registration field changed to signin field
        assertEquals("Вход", reg.getMainFieldName());
    }

    @Test
    public void testRegistrationThroughSignInAccountButton() {
        reg.clickSignInAccountButton();
        reg.registerUser(email, password, name);
        userManager.deleteUser(email, password);
        // Check that registration field closed and opened signin field
        assertEquals("Вход", reg.getMainFieldName());
    }

    @Test
    public void testRegistrationLessThanSixLettersInPassword() {
        reg.clickSignInAccountButton();
        reg.registerUser(email, "pass", name);
        // Check message
        assertEquals("Некорректный пароль", reg.checkPasswordMistakeMessage());
    }

    @Test
    public void testRegistrationZeroLettersInPassword() {
        reg.clickSignInAccountButton();
        reg.registerUser(email, " ", name);
        // Check message
        assertEquals("Некорректный пароль", reg.checkPasswordMistakeMessage());
    }

    @Test
    public void testRegistrationWithExistingUser() {
        userManager.createUser(email, password, name);

        // register with the same data
        reg.clickPrivateAccountButton();
        reg.registerUser(email, password, name);
        userManager.deleteUser(email, password);

        assertEquals("Такой пользователь уже существует", reg.checkExistingUserMessage());
    }

}
