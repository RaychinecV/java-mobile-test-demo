package com.vasil.raychinec.mobile.tests;

import com.vasil.raychinec.mobile.driver.Driver;
import com.vasil.raychinec.mobile.pages.EmptyFriendsPage;
import com.vasil.raychinec.mobile.pages.LoginPage;
import com.vasil.raychinec.mobile.pages.UserSettingsPage;
import com.vasil.raychinec.mobile.pages.WelcomePage;
import org.assertj.core.api.WithAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest implements WithAssertions {

    protected WelcomePage welcomePage;
    protected LoginPage loginPage;
    protected EmptyFriendsPage emptyFriendsPage;
    protected UserSettingsPage userSettingsPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Driver.setUp();
        initPages(Driver.getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.tearDown();
    }

    private void initPages(WebDriver driver) {
        welcomePage = new WelcomePage(driver);
        loginPage = new LoginPage(driver);
        emptyFriendsPage = new EmptyFriendsPage(driver);
        userSettingsPage = new UserSettingsPage(driver);
    }
}
