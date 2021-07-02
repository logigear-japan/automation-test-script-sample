package com.logigear.selenium.tools.webdriver;

import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverWrapper {
    private static WebDriver driver;

    private WebDriverWrapper() {
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
                if (os.startsWith("mac")) {
                    System.setProperty("webdriver.chrome.driver",
                            DRIVERS_DIR + File.separator + "mac" + File.separator + "chromedriver");
                } else if (os.startsWith("win")) {
                    System.setProperty("webdriver.chrome.driver",
                            DRIVERS_DIR + File.separator + "win" + File.separator + "chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver",
                            DRIVERS_DIR + File.separator + "linux" + File.separator + "chromedriver");
                }
                driver = new ChromeDriver();
                break;
            case "firefox":
                if (os.startsWith("mac")) {
                    System.setProperty("webdriver.gecko.driver",
                            DRIVERS_DIR + File.separator + "mac" + File.separator + "geckodriver");
                } else if (os.startsWith("win")) {
                    System.setProperty("webdriver.gecko.driver",
                            DRIVERS_DIR + File.separator + "win" + File.separator + "geckodriver.exe");
                } else {
                    System.setProperty("webdriver.gecko.driver",
                            DRIVERS_DIR + File.separator + "linux" + File.separator + "geckodriver");
                }
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
