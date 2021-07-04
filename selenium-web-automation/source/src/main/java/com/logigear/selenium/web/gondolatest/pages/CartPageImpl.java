package com.logigear.selenium.web.gondolatest.pages;

import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Implementation POM for https://demo.gondolatest.com/cart
 */
public class CartPageImpl extends LoadableComponent<HomePageImpl> implements CartPage {

    By email = By.id("email");
    By cardNumber = By.name("cardnumber");
    By expireDate = By.name("exp-date");
    By cvc = By.name("cvc");
    By postal = By.name("postal");
    By checkout = By.className("pay-with-stripe");
    By success = By.xpath("//*[text() = 'Success!']");
    By totalPrice = By.className("total");
    By item = By.className("cartitems");

    // WebDriver instance.
    private WebDriver driver;

    public CartPageImpl(WebDriver driver) {
        this.driver = driver;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(checkout));
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, APP_TITLE, "Not on the gondolatest page.");
    }

    /**
     * pay and Checkout.
     */
    public CartPage payAndCheckout() {
        // Check button enable or not
        WebElement button = driver.findElement(checkout);
        ExpectedConditions.elementToBeClickable(button);
        button.click();
        return this;
    }

    /**
     * fill payment info
     */
    public CartPage fillPaymentInfo(String emailValue, String cardNumberValue, String expMMYY, String cvcValue, String postalCode) {
        Preconditions.checkNotNull(emailValue, "Email parameter is null");
        Preconditions.checkNotNull(cardNumberValue, "Card number parameter is null");
        Preconditions.checkNotNull(expMMYY, "Expired date parameter is null");
        Preconditions.checkNotNull(cvcValue, "cvc parameter is null");
        Preconditions.checkNotNull(postalCode, "Postal code parameter is null");

        driver.findElement(email).sendKeys(emailValue);
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='Secure card payment input frame']")));
        driver.findElement(cardNumber).sendKeys(cardNumberValue);
        driver.findElement(expireDate).sendKeys(expMMYY);
        driver.findElement(cvc).sendKeys(cvcValue);
        driver.findElement(postal).sendKeys(postalCode);
        driver.switchTo().defaultContent();
        return this;
    }

    /**
     * get successful text
     */
    public WebElement getSuccessfulText() {
        return driver.findElement(success);
    }

    /**
     * total price equal summary of item's price
     */
    public boolean checkTotalPriceCorrect() {
        WebElement totalElement = driver.findElement(totalPrice);
        Float totalPrice = Float.parseFloat(totalElement.findElement(By.tagName("h3")).getText().split("\\$")[1]);
        List<WebElement> items = driver.findElements(item);
        Float allPrice = Float.valueOf(0);
        for (WebElement webElement : items) {
            allPrice += Float.parseFloat(webElement.findElement(By.tagName("strong")).getText());
        }
        return (Float.compare(totalPrice, allPrice) == 0);
    }

}
