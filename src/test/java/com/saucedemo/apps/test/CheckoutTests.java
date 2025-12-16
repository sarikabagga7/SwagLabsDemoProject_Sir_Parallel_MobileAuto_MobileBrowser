package com.saucedemo.apps.test;

import com.saucedemo.apps.base.BaseTest;
import com.saucedemo.apps.object.AppiumDriverManager;
import com.saucedemo.apps.pages.LoginPage;
import com.saucedemo.apps.pages.ProductsPage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class CheckoutTests extends BaseTest {
    @Test
    public void checkoutUsingSingleProduct() {
        try {
            AppiumDriver driver = AppiumDriverManager.getDriver();

            LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
            
            
            loginPage.login("standard_user", "secret_sauce");
            loginPage.validateNoErrorMessageDisplayed();

            //Add Products to Cart
            ProductsPage productPage = new ProductsPage(driver);
            productPage.addproductToCart("Sauce Labs Fleece Jacket");
            productPage.clickCartButton();


        } catch (Exception e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }
    
   
    @Test
    public void login() {
        try {
            //AppiumDriver driver = AppiumDriverManager.getDriver();

            LoginPage loginPage = new LoginPage(AppiumDriverManager.getDriver());
            loginPage.login("standard_user", "secret_sauce");
            loginPage.validateNoErrorMessageDisplayed();


        } catch (Exception e) {
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }

    //@Test
    /*public void checkoutUsingMultipleProducts() {
        System.out.println("In test 2");
    }*/
}
