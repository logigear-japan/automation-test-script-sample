package com.sample.selenium.web.gondolatest.abstract_scripts;

import com.sample.selenium.tools.webdriver.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class AbstractTest {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true, description = "Start the Browser")
    @Parameters("browser")
    public void startBrowser(@Optional("chrome") String browser) {
        driver = WebDriverService.startDriver(browser);
        driver.manage().window().maximize();
        testSetup();
    }

    @AfterClass(description = "Stop the Browser", alwaysRun = true)
    public void stopBrowser() {
        WebDriverService.stopDriver();
    }

    public abstract void testSetup();
}
