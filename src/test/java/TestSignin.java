import PageObjects.Registration;
import PageObjects.SignIn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.UserManager;

import static org.junit.Assert.*;

public class TestSignin {
    private WebDriver browser;
    private UserManager userManager;
    SignIn signIn;
    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
//        browser = DriverFactory.getBrowser();
        WebDriverManager.chromedriver().setup();
        browser = new ChromeDriver();
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
        signIn.waitForLoadHomePage();
        assertTrue(signIn.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testSignInThroughSingInAccount() {

    }


}
