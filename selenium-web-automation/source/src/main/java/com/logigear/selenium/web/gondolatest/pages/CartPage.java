package com.logigear.selenium.web.gondolatest.pages;

import org.openqa.selenium.WebElement;

/**
 * Interface POM for https://demo.gondolatest.com/cart
 */
public interface CartPage {
    String APP_URL = "https://demo.gondolatest.com/cart";
    String APP_TITLE = "LogiGear Demo Shop";

    /**
     * pay and Checkout.
     */
    public CartPage payAndCheckout();

    /**
     * fill payment info
     */
    public CartPage fillPaymentInfo(String emailValue, String cardNumberValue, String expMMYY, String cvcValue, String postalCode);

    /**
     * get successful text
     */
    public WebElement getSuccessfulText();

    /**
     * total price equal summary of item's price
     */
    public boolean checkTotalPriceCorrect();
}
