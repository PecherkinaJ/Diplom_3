import PageObjects.Constructor;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.Driver;

import static org.junit.Assert.*;

public class TestOpenMainPage extends BaseTest {

    Constructor constructor;
    private WebDriver browser;
    Driver driver;

    @Before
    public void setUp() throws Exception {
        String browserName = getParameter("browser");
        driver = new Driver(browserName);
        browser = driver.getDriver();
        constructor = new Constructor(browser);
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromPrivateAccount() {
        constructor.clickPrivateAccountButton();
        constructor.clickStellarBurgerIcon();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromOrderList() {
        constructor.openOrderList();
        constructor.clickStellarBurgerIcon();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromRegistrationField() {
        constructor.clickPrivateAccountButton();
        constructor.clickRegistrationButton();
        constructor.clickStellarBurgerIcon();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromForgotPasswordField() {
        constructor.clickPrivateAccountButton();
        constructor.clickForgotPasswordLink();
        constructor.clickStellarBurgerIcon();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromPrivateAccount() {
        constructor.clickPrivateAccountButton();
        constructor.clickConstructorButton();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromOrderList() {
        constructor.openOrderList();
        constructor.clickConstructorButton();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromRegistrationField() {
        constructor.clickPrivateAccountButton();
        constructor.clickRegistrationButton();
        constructor.clickConstructorButton();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromForgotPasswordField() {
        constructor.clickPrivateAccountButton();
        constructor.clickForgotPasswordLink();
        constructor.clickConstructorButton();
        assertTrue(constructor.collectBurgerHeaderDisplayed());
    }
}
