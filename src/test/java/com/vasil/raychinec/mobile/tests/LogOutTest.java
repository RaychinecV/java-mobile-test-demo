package com.vasil.raychinec.mobile.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogOutTest extends BaseTest {

    @BeforeMethod
    public void logInByEmail() {
        assertThat(welcomePage.isPageDisplayed())
                .as("Welcome page should be displayed.")
                .isEqualTo(true);
        welcomePage.clickLoginBtn();
        assertThat(loginPage.isPageDisplayed())
                .as("Login page should be displayed after login clicking.")
                .isEqualTo(true);
        loginPage
                .fillLogin()
                .clickLoginBtn();
        assertThat(emptyFriendsPage.isPageDisplayed())
                .as("Empty friends page should be displayed after first login.")
                .isEqualTo(true);
    }

    @Test
    public void logOutTest() {
        emptyFriendsPage
                .footContainer
                .clickOnBtnSettings();
        assertThat(userSettingsPage.isPageDisplayed())
                .as("User settings page should be displayed.")
                .isEqualTo(true);
        userSettingsPage.clickOnBtnLogOut();
        assertThat(userSettingsPage
                .logOutContainer
                .isPageDisplayed())
                .as("Log out container should displayed")
                .isEqualTo(true);
        userSettingsPage.logOutContainer.clickBtnConfirmLogOut();
        assertThat(welcomePage.isPageDisplayed())
                .as("Welcome page should displayed after log out.")
                .isEqualTo(true);
    }
}
