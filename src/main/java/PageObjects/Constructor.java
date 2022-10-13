package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Constructor extends Base {
    WebDriver driver;
    private final By stellarBurgerIcon = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']/parent::a");
    private final By orderListButton = By.xpath(".//p[text()='Лента Заказов']/parent::a");
    private final By orderListHeader = By.xpath(".//h1[text()='Лента заказов']");

    private final By bunMenu = By.xpath(".//span[text()='Булки']/parent::div");
    private final By sauceMenu = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsMenu = By.xpath(".//span[text()='Начинки']/parent::div");

    private final String containsInClassName = "tab_tab_type_current__2BEPc";

    public Constructor(WebDriver driver) {
        super(driver);
        this.driver = driver;
        final String url = "https://stellarburgers.nomoreparties.site/";
        driver.get(url);
        waitForLoadHomePage();
    }

    public void openOrderList(){
        driver.findElement(orderListButton).click();
        waitForElement(orderListHeader);
    }

    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
        waitForLoadHomePage();
    }

    public void clickStellarBurgerIcon(){
        driver.findElement(stellarBurgerIcon).click();
        waitForLoadHomePage();
    }

    public String checkSelectedSubMenu(){
        return driver.findElement(By.xpath(".//div[contains(@class, '"+
                containsInClassName
                +"')]/span")).getText();
    }

    public void selectBuns(){
        driver.findElement(bunMenu).click();
    }

    public void selectSauces(){
        driver.findElement(sauceMenu).click();
    }

    public void selectFillings(){
        driver.findElement(fillingsMenu).click();
    }
}
