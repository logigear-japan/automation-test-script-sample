package com.logigear.selenium.web.gondolatest.pages;

/**
 * Interface POM for https://demo.gondolatest.com/
 */
public interface HomePage {
    String APP_URL = "https://demo.gondolatest.com/";
    String APP_TITLE = "Logigear Demo Shop";
    /**
     * Select accessory's category (Men, Women, Sale).
     */
    public HomePage selectCategory();

    /**
     * Select price range.
     */
    public HomePage selectPrice(int value);

    /**
     * Add item to cart.
     */
    public HomePage addToCart(String item);

    /**
     * Checkout cart.
     */
    public CartPage checkout();
}
