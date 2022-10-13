package praktikum;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class Driver {

    WebDriver driver;
    String browserName;

    public Driver(String browserName) throws Exception {
        this.browserName = browserName;

        if (browserName.equalsIgnoreCase("chrome"))
            this.driver = new ChromeDriver();

        if (browserName.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.opera.driver", "D:\\Ya_Prakt\\projects\\Diplom_3\\operadriver.exe");
            OperaOptions options = new OperaOptions();
            options.setBinary("C:\\Users\\Julie\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            this.driver = new OperaDriver(options);
        } else throw new Exception("Используйте другой браузер");

    }

    public WebDriver getDriver(){
        return driver;
    }

    public void close() {
        this.driver.close();

    }

    public WebElement findElement(By arg0) {
        return this.driver.findElement(arg0);
    }

    public List<WebElement> findElements(By arg0) {
        return this.driver.findElements(arg0);
    }

    public void get(String arg0) {
        this.driver.get(arg0);

    }

    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    public String getPageSource() {
        return this.driver.getPageSource();
    }

    public String getTitle() {
        return this.driver.getTitle();
    }

    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }

    public Options manage() {
        return this.driver.manage();
    }

    public WebDriver.Navigation navigate() {
        return this.driver.navigate();
    }

    public void quit() {
        this.driver.quit();
    }

    public WebDriver.TargetLocator switchTo() {
        return this.driver.switchTo();
    }
}
