import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.Registration;
import praktikum.UserManager;

import static org.junit.Assert.*;

public class TestRegistration {
    private WebDriver browser;
    private Registration reg;
    private UserManager userManager;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
//        browser = DriverFactory.getBrowser();
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
        reg = new Registration(browser);
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

        //Delete user from DataBase in new Thread
        reg.run();

        // Check that registration field changed to signin field
        assertEquals("Вход", reg.getMainFieldName());
    }

    @Test
    public void testRegistrationThroughSignInAccountButton() {
        reg.clickPrivateAccountButton();
        reg.registerUser(email, password, name);

        //Delete user from DataBase in new Thread
        reg.run();

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

        // No message, page stays the same
        assertEquals("Некорректный пароль", reg.checkPasswordMistakeMessage());
    }

    @Test
    public void testRegistrationWithExistingUser() {
        reg.clickPrivateAccountButton();
        reg.registerUser(email, password, name);

        // Check that registration field closed and opened signin field
        assertEquals("Вход", reg.getMainFieldName());

        // register with the same data
        reg.clickPrivateAccountButton();
        reg.registerUser(email, password, name);

        //Delete user from DataBase in new Thread
        reg.run();

        assertEquals("Такой пользователь уже существует", reg.checkExistingUserMessage());
    }

}
