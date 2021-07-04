package com.logigear.selenium.web.gondolatest.test_scripts;

import com.logigear.selenium.web.gondolatest.abstract_scripts.AbstractTest;
import com.logigear.selenium.web.gondolatest.pages.CartPage;
import com.logigear.selenium.web.gondolatest.pages.HomePage;
import com.logigear.selenium.web.gondolatest.pages.HomePageImpl;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class E2EDemoTest extends AbstractTest {

    @Override
    public void testSetup() {
    }

    @Test(description = "User can checkout the shopping cart with valid information")
    @Parameters({"price_range_percent", "item1", "item2", "email", "card_number", "exp_date", "cvc", "zip_code"})
    public void buyAndCheckoutCart(int range, String item1, String item2, String email, String card, String expDate, String cvc, String postal) throws InterruptedException {
        // Navigate to home page
        HomePage homePage = new HomePageImpl(driver).get();

        // Select category
        homePage.selectCategory();

        // Set price range
        assertTrue(0 <= range && range <= 100);
        homePage.selectPrice(range);

        // Keep a bit for user see UI change
        Thread.sleep(2000);

        // Select item 1,2
        homePage.addToCart(item1);
        homePage.addToCart(item2);

        // Verify 2 item was add
        assertEquals(2, homePage.getCartNumber(), "Item does not select as expect");

        // click checkout cart
        CartPage cartPage = homePage.checkout();

        // Verify total price equal summary of item's price
        assertTrue(cartPage.checkTotalPriceCorrect());

        // Check out
        cartPage.fillPaymentInfo(email, card, expDate, cvc, postal);
        cartPage.payAndCheckout();

        // Verify checkout successful
        assertNotNull(cartPage.getSuccessfulText(), "Checkout not success");
    }
}
