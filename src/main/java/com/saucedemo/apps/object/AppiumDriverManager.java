package com.saucedemo.apps.object;

import com.saucedemo.apps.utils.Constants;
import com.saucedemo.apps.utils.JsonUtils;
import com.saucedemo.apps.utils.PlatformType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumDriverManager {
    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();
    

    //Added Sarika
    private static AppiumDriver getAndroidChromeDriver(String url) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAvd(Constants.getDeviceName());
        options.setChromedriverExecutable(System.getProperty("user.dir") + Constants.get().CHOROMEDRIVER_FILEPATH);
		options.setCapability("browserName","Chrome");
        AppiumDriver driver = new AndroidDriver(new URL(url), options);
        return driver;
    }

    //Added Sarika
    private static AppiumDriver getIOSSafari(String url) throws MalformedURLException {
    	XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(Constants.getDeviceName());
        options.setPlatformVersion(Constants.getPlatformVersion());
    	options.setCapability("browserName", "Safari");
    	  AppiumDriver driver = new IOSDriver(new URL(url), options);
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
          return driver;
    }
    
    
    
    public static AppiumDriver getDriver() throws MalformedURLException {
        if (DRIVER.get() == null) {
            Constants.get().PLATFORM_TYPE =
                    PlatformType.valueOf(Constants.getPlatformName().toUpperCase());
            DRIVER.set(getDriver(Constants.get().PLATFORM_TYPE));
        }
        return DRIVER.get();
    }

    public static void killDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        } else {
            throw new RuntimeException("Driver does not exist");
        }
    }

    private static AppiumDriver getDriver(PlatformType platformType) throws MalformedURLException {
        AppiumDriver driver;
        String url = "http://" + Constants.get().APPIUM_SERVER_ADDRESS + ":" + Constants.get().APPIUM_SERVER_PORT;

        if (platformType== PlatformType.ANDROID) {
        	 if ("CHROME".equalsIgnoreCase(Constants.get().BROWSER)) {
        		 System.out.println("Running test on Android mobile Chrome browser");
        		 driver = getAndroidChromeDriver(url);
        	 }
        	 else {
            driver = getAndroidDriver(url);
        	 }
        } else if (platformType == PlatformType.IOS) {
        	 if ("SAFARI".equalsIgnoreCase(Constants.get().BROWSER)) {
        		 System.out.println("Running test on IOS mobile Safari browser");
        		 driver = getIOSSafari(url);
        	 }
        	 else {
            driver = getIOSDriver(url);
        	 }
        } else {
            throw new RuntimeException("Unsupported Platform Type");
        }

        return driver;
    }

    private static AppiumDriver getAndroidDriver(String url) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAvd(Constants.getDeviceName());
        options.setApp(System.getProperty("user.dir") + "/src/test/resources/apps/" + Constants.get().APK_FILENAME);
        options.setAppPackage(Constants.get().PKGNAME);
        options.setAppActivity(Constants.get().ACTIVITY);

        AppiumDriver driver = new AndroidDriver(new URL(url), options);
        return driver;
    }

    private static AppiumDriver getIOSDriver(String url) throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(Constants.getDeviceName());
        options.setPlatformVersion(Constants.getPlatformVersion());
        options.setApp(System.getProperty("user.dir") + "/src/test/resources/apps/" + Constants.get().APP_FILENAME);
        options.setBundleId(Constants.get().BUNDLEID);

        AppiumDriver driver = new IOSDriver(new URL(url), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
