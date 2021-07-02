package com.logigear.selenium.web.gondolatest.abstract_scripts;

import com.logigear.selenium.tools.webdriver.WebDriverWrapper;
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
        driver = WebDriverWrapper.startDriver(browser);
        testSetup();
    }

    @AfterClass(description = "Stop the Browser", alwaysRun = true)
    public void stopBrowser() {
        WebDriverWrapper.stopDriver();
    }

    public abstract void testSetup();
}