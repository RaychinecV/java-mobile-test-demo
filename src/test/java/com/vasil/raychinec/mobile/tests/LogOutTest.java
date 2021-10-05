package com.vasil.raychinec.mobile.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogOutTest extends BaseTest {

    @BeforeMethod
    public void logInByEmail() {
        welcomePage.clickLoginBtn();
        loginPage
                .fillLogin()
                .clickLoginBtn();
    }

    @Test
    public void logOutTest() {
        emptyFriendsPage
                .footContainer
                .clickOnBtnSettings();

        assertThat(userSettingsPage.isRequiredPageElementsAreDisplayed())
                .as("User settings page should be displayed.")
                .isEqualTo(true);
        userSettingsPage.clickOnBtnLogOut();
        assertThat(userSettingsPage
                .logOutContainer
                .isRequiredPageElementsAreDisplayed())
                .as("Log out container should displayed")
                .isEqualTo(true);
        userSettingsPage
                .logOutContainer
                .clickBtnConfirmLogOut();

        assertThat(welcomePage.isRequiredPageElementsAreDisplayed())
                .as("Welcome page should displayed after log out.")
                .isEqualTo(true);
    }
}
