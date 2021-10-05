package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;

@Log4j2
public class WelcomePage extends BasePage {

    @Display(name = "Main title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_title")
    private MobileElement actualTitleLabel;

    @Display(name = "Sub title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_subtitle")
    private MobileElement actualSubTitleLabel;

    @Display(name = "Register button")
    @AndroidFindBy(id = ANDROID_BASE_ID + "auth_landing_register")
    private MobileElement btnRegister;

    @Display(name = "Login button")
    @AndroidFindBy(id = ANDROID_BASE_ID + "auth_landing_login")
    private MobileElement btnLogin;

    private final String expectedTitleLabel = "Welcome to Discord";
    private final String expectedSubTitleLabel = "Join over 100 million people who use Discord to talk with communities and friends.";

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void clickRegisterBtn() {
        log.info("Clicking on the register button.");
        waitToBeClickable(btnRegister).click();
    }

    @Step
    public LoginPage clickLoginBtn() {
        log.info("Clicking on the login button.");
        waitToBeClickable(btnLogin).click();
        return new LoginPage(driver);
    }

    @Override
    @Step
    public boolean isRequiredPageElementsAreDisplayed() {
        waitToBeVisible(actualTitleLabel);
        softAssert.assertEquals(actualTitleLabel.getText(), expectedTitleLabel);
        softAssert.assertEquals(actualSubTitleLabel.getText(), expectedSubTitleLabel);
        softAssert.assertAll();
        return isAllElementsDisplayed();
    }
}
