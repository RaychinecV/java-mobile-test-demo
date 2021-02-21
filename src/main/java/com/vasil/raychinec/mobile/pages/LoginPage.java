package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;
import static com.vasil.raychinec.mobile.utils.ApplicationProperties.BASE_PASSWORD;
import static com.vasil.raychinec.mobile.utils.ApplicationProperties.BASE_USER;

@Log4j2
public class LoginPage extends BasePage {

    @Display(getName = "Field login")
    @AndroidFindBy(id = ANDROID_BASE_ID + "phone_or_email_main_input")
    private MobileElement fldLogin;

    @Display(getName = "Button login")
    @AndroidFindBy(id = ANDROID_BASE_ID + "auth_login")
    private MobileElement btnLogin;

    @Display(getName = "Main title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_title")
    private MobileElement actualTitleLabel;

    @Display(getName = "Sub title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_subtitle")
    private MobileElement actualSubTitleLabel;

    @Display(getName = "Field password")
    @AndroidFindBy(xpath = "(//*[@class='android.widget.EditText'])[2]")
    private MobileElement fldPassword;

    private final String expectedTitleLabel = "Welcome back!";
    private final String expectedSubTitleLabel = "We're so excited to see you again!";

    protected final AppiumDriver<MobileElement> driver;

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        this.driver = driver;
    }

    @Step
    public LoginPage enterLogin(final String login) {
        log.info("Entering login {}.", login);
        enterText(fldLogin, login);
        return this;
    }

    @Step
    public LoginPage enterPassword(final String password) {
        log.info("Entering password {}.", password);
        enterText(fldPassword, password);
        return this;
    }

    @Step
    public LoginPage fillLogin(final String login, final String password) {
        this
                .enterLogin(login)
                .enterPassword(password);
        return this;
    }

    @Step
    public LoginPage fillLogin() {
        this
                .enterLogin(BASE_USER)
                .enterPassword(BASE_PASSWORD);
        return this;
    }

    @Step
    public LoginPage fillLogin(String login) {
        this
                .enterLogin(login)
                .enterPassword(BASE_PASSWORD);
        return this;
    }

    @Step
    public void clickLoginBtn() {
        log.info("Clicking login button.");
        waitToBeClickable(btnLogin).click();
    }

    @Step
    public boolean isTitlesAreSame() {
        waitToBeVisible(actualTitleLabel);
        softAssert.assertEquals(actualTitleLabel.getText(), expectedTitleLabel);
        softAssert.assertEquals(actualSubTitleLabel.getText(), expectedSubTitleLabel);
        softAssert.assertAll();
        return true;
    }

    @Override
    public boolean isPageDisplayed() {
        waitToBeVisible(actualTitleLabel);
        return isAllElementsDisplayed();
    }
}
