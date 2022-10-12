package praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver getBrowser() {
        String browserName;
        try {
            browserName = System.getProperty("browser");
        } catch (RuntimeException e) {
            browserName = "chrome";
        }
        switch (browserName) {
            case "chrome":
                return new ChromeDriver();
            case "yandex":
                //......
            default:
                throw new RuntimeException("Такого бразуера нет");
        }
    }
}
