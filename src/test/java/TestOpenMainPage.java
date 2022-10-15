import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ConstructorPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class TestOpenMainPage extends BaseTest {

    ConstructorPage constructorPage;
    private WebDriver browser;
    Driver driver;

    @Before
    public void setUp() throws Exception {
        String browserName = getParameter("browser");
        driver = new Driver(browserName);
        browser = driver.getDriver();
//        WebDriverManager.chromedriver().setup();
//        browser = new ChromeDriver();
        constructorPage = new ConstructorPage(browser);
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromPrivateAccount() {
        constructorPage.clickPrivateAccountButton();
        constructorPage.clickStellarBurgerIcon();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromOrderList() {
        constructorPage.openOrderList();
        constructorPage.clickStellarBurgerIcon();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromRegistrationField() {
        constructorPage.clickPrivateAccountButton();
        constructorPage.clickRegistrationButton();
        constructorPage.clickStellarBurgerIcon();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithStellarBurgerIconFromForgotPasswordField() {
        constructorPage.clickPrivateAccountButton();
        constructorPage.clickForgotPasswordLink();
        constructorPage.clickStellarBurgerIcon();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromPrivateAccount() {
        constructorPage.clickPrivateAccountButton();
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromOrderList() {
        constructorPage.openOrderList();
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromRegistrationField() {
        constructorPage.clickPrivateAccountButton();
        constructorPage.clickRegistrationButton();
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }

    @Test
    public void testOpenPageWithConstructorButtonFromForgotPasswordField() {
        constructorPage.clickPrivateAccountButton();
        constructorPage.clickForgotPasswordLink();
        constructorPage.clickConstructorButton();
        assertTrue(constructorPage.collectBurgerHeaderDisplayed());
    }
}
