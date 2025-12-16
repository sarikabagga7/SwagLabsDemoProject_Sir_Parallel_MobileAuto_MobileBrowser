package com.saucedemo.apps.pages;

import com.saucedemo.apps.pages.android.ProductsPage_Android;
import com.saucedemo.apps.pages.interfaces.IProductsPage;
import com.saucedemo.apps.pages.ios.ProductsPage_IOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ProductsPage {
    private IProductsPage productsPage;
    private AppiumDriver driver;

    public ProductsPage(AppiumDriver driver) {
        this.driver = driver;
        if (this.driver instanceof AndroidDriver) {
            productsPage = new ProductsPage_Android(driver);
        } else {
            productsPage = new ProductsPage_IOS(driver);
        }
    }

    public void validateNavigationToProductsPage() {
        productsPage.validateNavigationToProductsPage();
    }

    public void addproductToCart(String productName) {
        productsPage.addproductToCart(productName);
    }

    public void clickCartButton() {
        productsPage.clickCartButton();
    }
}
