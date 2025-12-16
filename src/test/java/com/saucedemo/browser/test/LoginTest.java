package com.saucedemo.browser.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.apps.base.BaseTest;
import com.saucedemo.apps.object.AppiumDriverManager;
import com.saucedemo.apps.pages.androidBrowser.LoginPage_AndroidBrowser;

public class LoginTest extends BaseTest {
	@Test
	public void loginTest() {
		   try {
	    LoginPage_AndroidBrowser loginPage = new LoginPage_AndroidBrowser(AppiumDriverManager.getDriver());

	    loginPage.login("standard_user", "secret_sauce");
	}
	  catch (Exception e) {
         Assert.fail("Exception occurred: " + e.getMessage());
     }
}

}