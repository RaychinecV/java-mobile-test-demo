package com.vasil.raychinec.mobile.tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void logInTest() {

        assertThat(welcomePage.isRequiredPageElementsAreDisplayed())
                .as("Welcome page should be displayed.")
                .isEqualTo(true);
        welcomePage.clickLoginBtn();

        assertThat(loginPage.isRequiredPageElementsAreDisplayed())
                .as("Login page should be displayed after login clicking.")
                .isEqualTo(true);
        loginPage
                .fillLogin()
                .clickLoginBtn();

        assertThat(emptyFriendsPage.isRequiredPageElementsAreDisplayed())
                .as("Empty friends page should be displayed after first login.")
                .isEqualTo(true);
    }
}
