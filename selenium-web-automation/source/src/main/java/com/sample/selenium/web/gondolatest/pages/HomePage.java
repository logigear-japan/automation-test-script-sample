package com.sample.selenium.web.gondolatest.pages;

import com.google.common.base.Preconditions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

/**
 * Implementation POM for https://demo.gondolatest.com/
 */
public class HomePage extends LoadableComponent<HomePage> {
    String APP_URL = "https://demo.gondolatest.com/";
    String APP_TITLE = "LogiGear Demo Shop";

    By womanCategory = By.cssSelector("a:nth-child(1) > li");
    By priceRange = By.id("pricerange");
    By checkoutCart = By.className("cartitem");
    By cartCount = By.className("cartcount");

    // WebDriver instance.
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(APP_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, APP_TITLE, "Not on the gondolatest page.");
    }

    /**
     * Select accessory's category.
     */
    public HomePage selectCategory() {
        driver.findElement(womanCategory).click();
        return this;
    }

    /**
     * Select price range.
     */
    public HomePage selectPrice(int value) {
        // wait until slider clickable
        WebDriverWait wait = new WebDriverWait(driver, 5000l);
        wait.until(ExpectedConditions.elementToBeClickable(priceRange));
        WebElement priceSlider = driver.findElement(priceRange);

        Dimension sliderSize = priceSlider.getSize();
        int sliderWidth = sliderSize.getWidth();
        Actions builder = new Actions(driver);
        builder.moveToElement(priceSlider)
                .click()
                .dragAndDropBy(priceSlider,(value-50)*sliderWidth/100, 0)
                .build()
                .perform();

        return this;
    }

    /**
     * Add item to cart.
     */
    public HomePage addToCart(String item) {
        Preconditions.checkNotNull(item, "Item parameter is null");
        By xpath = By.xpath(String.format("//*[text() = '%s']/following-sibling::button", item));
        WebDriverWait wait = new WebDriverWait(driver, 5000l);
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        WebElement button = driver.findElement(xpath);
        button.click();
        return this;
    }

    /**
     * Get number of item in cart.
     */
    public int getCartNumber() {
        WebDriverWait wait = new WebDriverWait(driver, 5000l);
        wait.until(ExpectedConditions.elementToBeClickable(cartCount));
        WebElement cartNumber = driver.findElement(cartCount);
        return Integer.valueOf(cartNumber.getText());
    }

    /**
     * Checkout cart.
     */
    public CartPage checkout() {
        WebDriverWait wait = new WebDriverWait(driver, 5000l);
        wait.until(ExpectedConditions.elementToBeClickable(checkoutCart));
        WebElement cart = driver.findElement(checkoutCart);
        cart.click();
        return new CartPage(driver);
    }
}
