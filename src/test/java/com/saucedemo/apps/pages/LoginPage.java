package com.saucedemo.apps.pages;

import com.saucedemo.apps.pages.android.LoginPage_Android;
import com.saucedemo.apps.pages.interfaces.ILoginPage;
import com.saucedemo.apps.pages.ios.LoginPage_IOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
    private ILoginPage loginPage;

    private AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        if (this.driver instanceof AndroidDriver) {
            loginPage = new LoginPage_Android(driver);
        } else {
            loginPage = new LoginPage_IOS(driver);
        }
    }

    public void login(String userName, String password) {
        loginPage.login(userName, password);
    }

    public void validateNoErrorMessageDisplayed() {
        loginPage.validateNoErrorMessageDisplayed();
    }

    public void validateErrorMessage(String expectedMsg) {
        loginPage.validateErrorMessage(expectedMsg);
    }
}
