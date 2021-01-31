package com.vasil.raychinec.mobile.tests;

import com.vasil.raychinec.mobile.config.driver.DriverFactory;
import com.vasil.raychinec.mobile.config.driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.Getter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static com.vasil.raychinec.mobile.utils.ApplicationProperties.PLATFORM;

@Getter
public class BaseTest {
    public static AppiumDriver<MobileElement> driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        final String platform = PLATFORM.toUpperCase();

        driver = new DriverFactory().setPlatform(Platform.valueOf(platform)).create();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
