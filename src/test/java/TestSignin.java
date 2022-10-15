import pageObjects.SignInPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.UserManager;

import static org.junit.Assert.*;

public class TestSignin extends BaseTest {
    private WebDriver browser;
    private UserManager userManager;
    SignInPage signInPage;
    private String email, password, name;
    Driver driver;

    @Before
    public void setUp() throws Exception {
        String browserName = getParameter("browser");
        driver = new Driver(browserName);
        browser = driver.getDriver();

//        WebDriverManager.chromedriver().setup();
//        browser = new ChromeDriver();

        signInPage = new SignInPage(browser);
        userManager = new UserManager();

        email = userManager.getEmail();
        password = userManager.getPassword();
        name = userManager.getName();
        signInPage.setUserData(email, password, name);
    }

    @After
    public void tearDown() {
        userManager.deleteUser(email, password);
        browser.quit();
    }

    @Test
    public void testSignInThroughPersonalAccount() {
        signInPage.clickPrivateAccountButton();
        signInPage.fillFieldsAndClickEnterButton();
        assertTrue(signInPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testSignInThroughSignInAccountButton() {
        signInPage.clickSignInAccountButton();
        signInPage.fillFieldsAndClickEnterButton();
        assertTrue(signInPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testSignInFromRegistrationMenu() {
        signInPage.clickPrivateAccountButton();
        signInPage.clickRegistrationButton();
        signInPage.clickEnterLink();
        signInPage.fillFieldsAndClickEnterButton();
        assertTrue(signInPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testSignInFromForgotPasswordMenu() {
        signInPage.clickPrivateAccountButton();
        signInPage.clickForgotPasswordLink();
        signInPage.clickEnterLink();
        signInPage.fillFieldsAndClickEnterButton();
        assertTrue(signInPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testEnterPrivateAccountAfterAuth() {
        signInPage.clickPrivateAccountButton();
        signInPage.fillFieldsAndClickEnterButton();
        signInPage.enterToPrivateAccount();
        assertTrue(signInPage.isPrivateAccountOpened());
    }

    @Test
    public void testLogOut() {
        signInPage.clickPrivateAccountButton();
        signInPage.fillFieldsAndClickEnterButton();
        signInPage.enterToPrivateAccount();
        signInPage.clickLogOutButton();
        signInPage.waitForPrivateAccountPage();
        assertTrue(signInPage.isEnterButtonDisplayed());
    }
}
