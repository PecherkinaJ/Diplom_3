import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class Driver{

    WebDriver driver;
    String browserName;

    public Driver(String browserName) throws Exception {
        this.browserName = browserName;

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.opera.driver", "D:\\Ya_Prakt\\projects\\Diplom_3\\operadriver.exe");
            OperaOptions options = new OperaOptions();
            options.setBinary("C:\\Users\\Julie\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            this.driver = new OperaDriver(options);
        } else throw new Exception("Используйте другой браузер");
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void quit() {
        this.driver.quit();
    }
}
