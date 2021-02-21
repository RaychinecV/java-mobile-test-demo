package com.vasil.raychinec.mobile.tests;

import com.vasil.raychinec.mobile.config.driver.DriverFactory;
import com.vasil.raychinec.mobile.config.driver.Platforms;
import com.vasil.raychinec.mobile.pages.EmptyFriendsPage;
import com.vasil.raychinec.mobile.pages.LoginPage;
import com.vasil.raychinec.mobile.pages.UserSettingsPage;
import com.vasil.raychinec.mobile.pages.WelcomePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.Getter;
import org.assertj.core.api.WithAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static com.vasil.raychinec.mobile.utils.ApplicationProperties.PLATFORM;

@Getter
public class BaseTest implements WithAssertions {
    public static AppiumDriver<MobileElement> driver;
    protected WelcomePage welcomePage;
    protected LoginPage loginPage;
    protected EmptyFriendsPage emptyFriendsPage;
    protected UserSettingsPage userSettingsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        final String platform = PLATFORM.toUpperCase();

        driver = new DriverFactory().setPlatforms(Platforms.valueOf(platform)).create();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        initPages();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected void initPages() {
        welcomePage = new WelcomePage(driver);
        loginPage = new LoginPage(driver);
        emptyFriendsPage = new EmptyFriendsPage(driver);
        userSettingsPage = new UserSettingsPage(driver);
    }
}
