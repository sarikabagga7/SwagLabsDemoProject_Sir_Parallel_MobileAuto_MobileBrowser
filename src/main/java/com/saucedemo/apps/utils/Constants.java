package com.saucedemo.apps.utils;

import static com.saucedemo.apps.utils.PropertyUtils.Config;

public class Constants {
    private static final ThreadLocal<Constants> threadLocal = new ThreadLocal<>();

    // iOS App Details
    public String BUNDLEID;
    public String APP_FILENAME;

    // Android App Details
    public String PKGNAME;
    public String ACTIVITY;
    public String APK_FILENAME;

    // Appium Server Details
    public String APPIUMJS_FILEPATH;
    public String NODE_FILEPATH;
    public String APPIUM_SERVER_ADDRESS;
    public int APPIUM_SERVER_PORT;

    // Remote Server Details
    public boolean ENABLE_REMOTE;
    public String REMOTE_USERNAME;
    public String REMOTE_ACCESSKEY;

    // Test Configuration
    public PlatformType PLATFORM_TYPE ;
    public String DEVICE_PROFILE;
    
    //Sarika Added Chrome
    public String URL;
    public String BROWSER;
    public String CHOROMEDRIVER_FILEPATH;

    public static Constants get() {
        if (threadLocal.get() == null) {
            threadLocal.set(new Constants());
        }
        return threadLocal.get();
    }

    private Constants() {
        // iOS App Details
        BUNDLEID = Config.getProperty("app.bundle.id").trim();
        APP_FILENAME = Config.getProperty("app.file.path").trim();

        // Android App Details
        PKGNAME = Config.getProperty("apk.app.package").trim();
        ACTIVITY = Config.getProperty("apk.app.activity").trim();
        APK_FILENAME = Config.getProperty("apk.file.path").trim();

        // Appium Server Details
        APPIUMJS_FILEPATH = Config.getProperty("appium.js.path").trim();
        NODE_FILEPATH = Config.getProperty("appium.node.path").trim();
        APPIUM_SERVER_ADDRESS = Config.getProperty("appium.ip.address").trim();
        APPIUM_SERVER_PORT = Integer.parseInt(Config.getProperty("appium.port").trim());

        // Remote Server Details
        ENABLE_REMOTE = Boolean.parseBoolean(Config.getProperty("remote.enable").trim());
        REMOTE_USERNAME = Config.getProperty("remote.username").trim();
        REMOTE_ACCESSKEY = Config.getProperty("remote.accessKey").trim();

        // Test Configuration
        PLATFORM_TYPE = null;
        DEVICE_PROFILE = Config.getProperty("device.profile").trim();
        
        //Sarika added 
        CHOROMEDRIVER_FILEPATH=Config.getProperty("android.chromedriver.path").trim();
        URL=Config.getProperty("url").trim();
        BROWSER=null;
    }

    public static String getPlatformName() {
        return JsonUtils.getProfileValue("platform.name");
    }

    public static String getPlatformVersion() {
        return JsonUtils.getProfileValue("platform.version");
    }

    public static String getDeviceName() {
        return JsonUtils.getProfileValue("device.name");
    }

    public static String getDeviceType() {
        return JsonUtils.getProfileValue("device.type");
    }

    public static String getAutomationName() {
        return JsonUtils.getProfileValue("automation_name");
    }

    public static Boolean getNoResetValue() {
        return Boolean.valueOf(JsonUtils.getProfileValue("no_reset"));
    }
}
