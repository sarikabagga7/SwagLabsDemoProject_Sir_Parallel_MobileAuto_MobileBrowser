package com.saucedemo.apps.pages.androidBrowser;

import com.saucedemo.apps.base.BasePage;
import com.saucedemo.apps.pages.interfaces.ILoginPage;
import com.saucedemo.apps.utils.AndroidGestures;
import com.saucedemo.apps.utils.Constants;

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

public class LoginPage_AndroidBrowser extends BasePage implements ILoginPage  {
   

    public LoginPage_AndroidBrowser(AppiumDriver driver) {
        super(driver);
    }

    @Override
    public void login(String userName, String password) {
    	//DRIVER.get("https://www.saucedemo.com/");
    	DRIVER.get(Constants.get().URL);
		
		DRIVER.findElement(By.id("user-name")).sendKeys("standard_user");
		DRIVER.findElement(By.id("password")).sendKeys("secret_sauce");
		DRIVER.findElement(By.id("login-button")).click();
		
		System.out.println("Title: " + DRIVER.getTitle());
    }

    @Override
    public void validateErrorMessage(String expectedMsg) {
        
    	System.out.println("Not implemnetd yet");
    }

    @Override
    public void validateNoErrorMessageDisplayed() {
    	System.out.println("Not implemnetd yet");
    }
}
