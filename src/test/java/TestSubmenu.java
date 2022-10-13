import PageObjects.Constructor;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.Driver;

import static org.junit.Assert.*;

public class TestSubmenu extends BaseTest {

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
    public void testBunsSubmenuSelectedByDefault() {
        assertEquals("Булки", constructor.checkSelectedSubMenu());
    }

    @Test
    public void testSelectSauces() {
        constructor.selectSauces();
        assertEquals("Соусы", constructor.checkSelectedSubMenu());
    }

    @Test
    public void testSelectFillings() {
        constructor.selectFillings();
        assertEquals("Начинки", constructor.checkSelectedSubMenu());
    }

    @Test
    public void testSelectBunsAfterFillings() {
        constructor.selectFillings();
        constructor.selectBuns();
        assertEquals("Булки", constructor.checkSelectedSubMenu());
    }

    @Test
    public void testSelectBunsAfterSauces() {
        constructor.selectSauces();
        constructor.selectBuns();
        assertEquals("Булки", constructor.checkSelectedSubMenu());
    }
}
