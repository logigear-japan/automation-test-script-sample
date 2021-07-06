package com.sample.selenium.tools.webdriver;

import com.google.common.base.Preconditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverService {
    private static WebDriver driver;

    private WebDriverService() {
    }

    // Driver executable files directory.
    private static final String DRIVERS_DIR = System.getProperty("user.dir") + File.separator +
            "config" + File.separator + "drivers";

    public static WebDriver startDriver(String browser) {
        Preconditions.checkNotNull(browser, "Target browser parameter is null");
        if (driver != null) {
            return driver;
        }

        String os = System.getProperty("os.name").toLowerCase();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new AssertionError(browser + " not supported");
        }

        driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
