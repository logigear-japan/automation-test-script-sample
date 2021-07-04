package com.logigear.selenium.web.gondolatest.pages;

import com.google.common.base.Preconditions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.Console;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Implementation POM for https://demo.gondolatest.com/
 */
public class HomePageImpl extends LoadableComponent<HomePageImpl> implements HomePage {

    // Page Elements.
    @FindBy(css = "a:nth-child(1) > li")
    private WebElement womanCategory;
    @FindBy(id = "pricerange")
    private WebElement priceRange;
    @FindBy(className = "cartitem")
    private WebElement checkoutCart;
    @FindBy(className = "cartcount")
    private WebElement cartCount;

    // WebDriver instance.
    private WebDriver driver;

    public HomePageImpl(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
    @Override
    public HomePage selectCategory() {
        womanCategory.click();
        return this;
    }

    /**
     * Select price range.
     */
    @Override
    public HomePage selectPrice(int value) {
        ExpectedConditions.elementToBeClickable(priceRange);
        Dimension sliderSize = priceRange.getSize();
        int sliderWidth = sliderSize.getWidth();
        Actions builder = new Actions(driver);
        builder.moveToElement(priceRange)
                .click()
                .dragAndDropBy(priceRange,(value-50)*sliderWidth/100, 0)
                .build()
                .perform();

        return this;
    }

    /**
     * Add item to cart.
     */
    @Override
    public HomePage addToCart(String item) {
        Preconditions.checkNotNull(item, "Item parameter is null");
        WebElement button = driver.findElement (By.xpath(String.format("//*[text() = '%s']/following-sibling::button", item)));
        assertNotNull(button);
        ExpectedConditions.elementToBeClickable(button);
        button.click();
        return this;
    }

    /**
     * Get number of item in cart.
     */
    public int getCartNumber() {
        Preconditions.checkNotNull(cartCount, "Can not find cart number. No item in cart");
        return Integer.valueOf(cartCount.getText());
    }

    /**
     * Checkout cart.
     */
    @Override
    public CartPage checkout() {
        ExpectedConditions.elementToBeClickable(checkoutCart);
        checkoutCart.click();
        return new CartPageImpl(driver);
    }
}
