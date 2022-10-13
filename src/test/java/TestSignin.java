import PageObjects.SignIn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.Driver;
import praktikum.UserManager;

import static org.junit.Assert.*;

public class TestSignin extends BaseTest {
    private WebDriver browser;
    private UserManager userManager;
    SignIn signIn;
    private String email;
    private String password;
    private String name;
    Driver driver;

    @Before
    public void setUp() throws Exception {
        String browserName = getParameter("browser");
        driver = new Driver(browserName);
        browser = driver.getDriver();
        signIn = new SignIn(browser);
        userManager = new UserManager();

        email = userManager.getEmail();
        password = userManager.getPassword();
        name = userManager.getName();
        signIn.setUserData(email, password, name);
    }

    @After
    public void tearDown() {
        userManager.deleteUser(email, password);
        browser.quit();
    }

    @Test
    public void testSignInThroughPersonalAccount() {
        signIn.clickPrivateAccountButton();
        signIn.fillFieldsAndClickEnterButton();
        assertTrue(signIn.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testSignInThroughSignInAccountButton() {
        signIn.clickSignInAccountButton();
        signIn.fillFieldsAndClickEnterButton();
        assertTrue(signIn.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testSignInFromRegistrationMenu() {
        signIn.clickPrivateAccountButton();
        signIn.clickRegistrationButton();
        signIn.clickEnterLink();
        signIn.fillFieldsAndClickEnterButton();
        assertTrue(signIn.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testSignInFromForgotPasswordMenu() {
        signIn.clickPrivateAccountButton();
        signIn.clickForgotPasswordLink();
        signIn.clickEnterLink();
        signIn.fillFieldsAndClickEnterButton();
        assertTrue(signIn.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testEnterPrivateAccountAfterAuth() {
        signIn.clickPrivateAccountButton();
        signIn.fillFieldsAndClickEnterButton();
        signIn.enterToPrivateAccount();
        assertTrue(signIn.isPrivateAccountOpened());
    }

    @Test
    public void testLogOut() {
        signIn.clickPrivateAccountButton();
        signIn.fillFieldsAndClickEnterButton();
        signIn.enterToPrivateAccount();
        signIn.clickLogOutButton();
        signIn.waitForPrivateAccountPage();
        assertTrue(signIn.isEnterButtonDisplayed());
    }


}
