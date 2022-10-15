import pageObjects.ConstructorPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class TestSubmenu extends BaseTest {

    ConstructorPage constructorPage;
    private WebDriver browser;
    Driver driver;

    @Before
    public void setUp() throws Exception {
        String browserName = getParameter("browser");
        driver = new Driver(browserName);
        browser = driver.getDriver();
        constructorPage = new ConstructorPage(browser);
    }

    @After
    public void tearDown() {
        browser.quit();
    }

    @Test
    public void testBunsSubmenuSelectedByDefault() {
        assertEquals("Булки", constructorPage.checkSelectedSubMenu());
    }

    @Test
    public void testSelectSauces() {
        constructorPage.selectSauces();
        assertEquals("Соусы", constructorPage.checkSelectedSubMenu());
    }

    @Test
    public void testSelectFillings() {
        constructorPage.selectFillings();
        assertEquals("Начинки", constructorPage.checkSelectedSubMenu());
    }

    @Test
    public void testSelectBunsAfterFillings() {
        constructorPage.selectFillings();
        constructorPage.selectBuns();
        assertEquals("Булки", constructorPage.checkSelectedSubMenu());
    }

    @Test
    public void testSelectBunsAfterSauces() {
        constructorPage.selectSauces();
        constructorPage.selectBuns();
        assertEquals("Булки", constructorPage.checkSelectedSubMenu());
    }
}
