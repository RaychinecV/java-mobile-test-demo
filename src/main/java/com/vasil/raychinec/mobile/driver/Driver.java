package com.vasil.raychinec.mobile.driver;

import org.openqa.selenium.WebDriver;

public class Driver {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void setUp() {
        WebDriver driver = new DriverFactory().create();
        DRIVER.set(driver);
    }

    public static void tearDown() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }
}
