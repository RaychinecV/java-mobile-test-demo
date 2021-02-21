package com.vasil.raychinec.mobile.tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void logInTest() {
        assertThat(welcomePage.isPageDisplayed())
                .as("Welcome page should be displayed.")
                .isEqualTo(true);
        assertThat(welcomePage.isTitlesAreSame())
                .as("Actual and expected titles should be same.")
                .isEqualTo(true);
        welcomePage.clickLoginBtn();
        assertThat(loginPage.isPageDisplayed())
                .as("Login page should be displayed after login clicking.")
                .isEqualTo(true);
        assertThat(loginPage.isTitlesAreSame())
                .as("Actual and expected titles should be same.")
                .isEqualTo(true);
        loginPage
                .fillLogin()
                .clickLoginBtn();
        assertThat(emptyFriendsPage.isPageDisplayed())
                .as("Empty friends page should be displayed after first login.")
                .isEqualTo(true);
        assertThat(emptyFriendsPage.isTitlesAreSame())
                .as("Actual and expected titles should be same.")
                .isEqualTo(true);
    }
}
