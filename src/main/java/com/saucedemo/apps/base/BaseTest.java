package com.saucedemo.apps.base;

import com.saucedemo.apps.object.AppiumDriverManager;
import com.saucedemo.apps.object.AppiumServiceManager;
import com.saucedemo.apps.utils.Constants;
import com.saucedemo.apps.utils.JsonUtils;
import com.saucedemo.apps.utils.PlatformType;
import com.saucedemo.apps.utils.PropertyUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public abstract class BaseTest {

    @BeforeTest
    @Parameters({"profile", "port","browser"})
    public void suiteSetup(@Optional String profile, @Optional String port, @Optional String browser ) {
        try {
            loadTestConfiguration(profile, port, browser);
            AppiumServiceManager.startAppiumService();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @BeforeMethod
    public void testSetup() {
        try {
            AppiumDriverManager.getDriver();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void testTeardown() {
    	
        terminateApp();
    	 
        AppiumDriverManager.killDriver();
    }

    @AfterTest
    public void suiteTeardown() {
        AppiumServiceManager.stopAppiumService();
    }

    private void terminateApp() {
        try {
            if (Constants.get().PLATFORM_TYPE == PlatformType.ANDROID) {
            	if ("CHROME".equalsIgnoreCase(Constants.get().BROWSER)) {}
            	else {   AndroidDriver driver = (AndroidDriver) AppiumDriverManager.getDriver();
              
                if (driver.queryAppState(Constants.get().PKGNAME) != ApplicationState.NOT_RUNNING) {
                    driver.terminateApp(Constants.get().PKGNAME);
                }
                
                uninstallApp();
                }
            } else {
                System.out.println("Running test on iOS app");
                IOSDriver driver = (IOSDriver) AppiumDriverManager.getDriver();
                
                if (driver.queryAppState(Constants.get().BUNDLEID) != ApplicationState.NOT_RUNNING) {
                    driver.terminateApp(Constants.get().BUNDLEID);
                }
                
                uninstallApp();
            }
          
        } catch (RuntimeException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void uninstallApp() throws MalformedURLException {
        if (Constants.get().PLATFORM_TYPE == PlatformType.ANDROID) {
            ((AndroidDriver)AppiumDriverManager.getDriver()).removeApp(Constants.get().PKGNAME);
        } else {
            ((IOSDriver)AppiumDriverManager.getDriver()).removeApp(Constants.get().BUNDLEID);
        }
    }

    private void loadTestConfiguration(String profileFromXml, String portFromXml, String browserFromXml) throws IOException {
        PropertyUtils.loadConfigProperties();

        if (profileFromXml != null) {
            Constants.get().DEVICE_PROFILE = profileFromXml;
        }
        if (portFromXml != null) {
            Constants.get().APPIUM_SERVER_PORT = Integer.parseInt(portFromXml);
        }
        if (browserFromXml != null) {
            Constants.get().BROWSER = browserFromXml;
        }

        JsonUtils.loadDeviceProfile(Constants.get().DEVICE_PROFILE);
    }
}
