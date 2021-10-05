package com.vasil.raychinec.mobile.constants;

import com.vasil.raychinec.mobile.driver.Platforms;

public class ApplicationProperties {

    public static final Platforms PLATFORM = Platforms.valueOf(System.getProperty("platform").toUpperCase());
    public static final String BASE_USER = System.getProperty("user.login");
    public static final String BASE_PASSWORD = System.getProperty("user.password");
    public static final String APK_PATH = System.getProperty("apk.path");
    public static final boolean IS_BROWSERSTACK = Boolean.parseBoolean(System.getProperty("is.browserstack"));

    private ApplicationProperties() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }
}
