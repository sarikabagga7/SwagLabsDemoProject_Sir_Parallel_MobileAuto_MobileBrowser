package com.saucedemo.apps.pages.android;

import com.saucedemo.apps.base.BasePage;
import com.saucedemo.apps.pages.interfaces.ILoginPage;
import com.saucedemo.apps.utils.AndroidGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage_Android extends BasePage implements ILoginPage, AndroidGestures {
    private By userName = AppiumBy.accessibilityId("test-Username");
    private By password = AppiumBy.accessibilityId("test-Password");
    private By loginBtn = AppiumBy.accessibilityId("test-LOGIN");
    private By errorMsg = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView");

    public LoginPage_Android(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void login(String userName, String password) {
        Assert.assertTrue(isElementDisplayed(this.userName), "Username field is not displayed.");
        enterText(this.userName, userName);
        Assert.assertTrue(isElementDisplayed(this.password), "Password field is not displayed.");
        enterText(this.password, password);
        Assert.assertTrue(isElementClickable(this.loginBtn), "Login Button is not displayed.");
        clickGestureById(DRIVER, this.loginBtn);
    }

    @Override
    public void validateErrorMessage(String expectedMsg) {
        Assert.assertTrue(isElementDisplayed(errorMsg, 5), "No error message found.");
        String actualMsg = getElementAttribute(errorMsg, "text");
        Assert.assertTrue(actualMsg.trim().equals(expectedMsg),
                String.format("Error message does not match. Expected: %s. Actual: %s.", expectedMsg, actualMsg));
    }

    @Override
    public void validateNoErrorMessageDisplayed() {
        Assert.assertFalse(isElementDisplayed(errorMsg, 2), "Error message found during login.");
    }
}
