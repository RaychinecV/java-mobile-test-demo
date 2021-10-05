package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;
import static com.vasil.raychinec.mobile.constants.ApplicationProperties.BASE_PASSWORD;
import static com.vasil.raychinec.mobile.constants.ApplicationProperties.BASE_USER;

@Log4j2
public class LoginPage extends BasePage {

    @Display(name = "Field login")
    @AndroidFindBy(id = ANDROID_BASE_ID + "phone_or_email_main_input")
    private MobileElement fldLogin;

    @Display(name = "Button login")
    @AndroidFindBy(id = ANDROID_BASE_ID + "auth_login")
    private MobileElement btnLogin;

    @Display(name = "Main title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_title")
    private MobileElement actualTitleLabel;

    @Display(name = "Sub title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "screen_title_subtitle")
    private MobileElement actualSubTitleLabel;

    @Display(name = "Field password")
    @AndroidFindBy(xpath = "(//*[@class='android.widget.EditText'])[2]")
    private MobileElement fldPassword;

    private final String expectedTitleLabel = "Welcome back!";
    private final String expectedSubTitleLabel = "We're so excited to see you again!";

    public LoginPage(WebDriver driver) {
        super(driver);
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
