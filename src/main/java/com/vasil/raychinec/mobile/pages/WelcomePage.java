package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;

@Log4j2
public class WelcomePage extends BasePage {

    @Display(getName = "Main title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_title")
    private MobileElement actualTitleLabel;

    @Display(getName = "Sub title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_subtitle")
    private MobileElement actualSubTitleLabel;

    @Display(getName = "Register button")
    @AndroidFindBy(id = ANDROID_BASE_ID + "auth_landing_register")
    private MobileElement btnRegister;

    @Display(getName = "Login button")
    @AndroidFindBy(id = ANDROID_BASE_ID + "auth_landing_login")
    private MobileElement btnLogin;

    private final String expectedTitleLabel = "Welcome to Discord";
    private final String expectedSubTitleLabel = "Join over 100 million people who use Discord to talk with communities and friends.";

    protected final AppiumDriver<MobileElement> driver;

    public WelcomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
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

    @Step
    public boolean isTitlesAreSame() {
        softAssert.assertEquals(actualTitleLabel.getText(), expectedTitleLabel);
        softAssert.assertEquals(actualSubTitleLabel.getText(), expectedSubTitleLabel);
        softAssert.assertAll();
        return true;
    }

    @Override
    @Step
    public boolean isPageDisplayed() {
        waitToBeVisible(actualTitleLabel);
        return isAllElementsDisplayed();
    }
}
