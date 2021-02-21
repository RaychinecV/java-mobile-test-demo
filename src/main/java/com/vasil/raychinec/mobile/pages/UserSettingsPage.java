package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import com.vasil.raychinec.mobile.pages.conteiners.FootContainer;
import com.vasil.raychinec.mobile.pages.conteiners.LogOutContainer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;

@Log4j2
public class UserSettingsPage extends BasePage {


    @Display(getName = "User name")
    @AndroidFindBy(id = ANDROID_BASE_ID + "username_text")
    private MobileElement userName;

    @Display(getName = "Settings title label")
    @AndroidFindBy(id = ANDROID_BASE_ID + "user_settings_header")
    private MobileElement actualSettingsTitle;

    @Display(getName = "Toolbar title label")
    @AndroidFindBy(id = ANDROID_BASE_ID + "toolbar_title")
    private MobileElement actualToolbarTitle;

    @Display(getName = "Button Log Out")
    @AndroidFindBy(accessibility = "Log Out")
    private MobileElement btnLogOut;

    @Display(getName = "Button Set Status")
    @AndroidFindBy(xpath = "//*[@text='Set Status']")
    private MobileElement btnSetStatus;

    @Display(getName = "Button My Account")
    @AndroidFindBy(id = ANDROID_BASE_ID + "account")
    private MobileElement btnMyAccount;

    @Display(getName = "Button Privacy & Safety")
    @AndroidFindBy(id = ANDROID_BASE_ID + "privacy")
    private MobileElement btnPrivacyAndSafety;

    @Display(getName = "Button Authorized Apps")
    @AndroidFindBy(id = ANDROID_BASE_ID + "authorized_apps")
    private MobileElement btnAuthorizedApps;

    @Display(getName = "Button Connections")
    @AndroidFindBy(id = ANDROID_BASE_ID + "connections")
    private MobileElement btnConnections;

    @Display(getName = "Button Scan QR Code")
    @AndroidFindBy(id = ANDROID_BASE_ID + "qr_scanner")
    private MobileElement btnScanQRCode;

    @Display(getName = "Button Subscribe Today")
    @AndroidFindBy(id = ANDROID_BASE_ID + "settings_nitro")
    private MobileElement btnSubscribeToday;

    @Display(getName = "Button Boosts")
    @AndroidFindBy(id = ANDROID_BASE_ID + "nitro_boosting")
    private MobileElement btnBoosts;

    @Display(getName = "Button Nitro Gifting")
    @AndroidFindBy(id = ANDROID_BASE_ID + "nitro_gifting")
    private MobileElement btnNitroGifting;

    @Display(getName = "Button Voice & Video")
    @AndroidFindBy(id = ANDROID_BASE_ID + "voice")
    private MobileElement btnVoiceAndVideo;

    @Display(getName = "Button Notifications")
    @AndroidFindBy(id = ANDROID_BASE_ID + "notifications")
    private MobileElement btnNotifications;

    @Display(getName = "Button Game Activity")
    @AndroidFindBy(id = ANDROID_BASE_ID + "game_activity")
    private MobileElement btnGameActivity;

    @Display(getName = "Button Text & Images")
    @AndroidFindBy(id = ANDROID_BASE_ID + "text_images_settings")
    private MobileElement btnTextAndImages;

    @Display(getName = "Button Appearance")
    @AndroidFindBy(id = ANDROID_BASE_ID + "appearance")
    private MobileElement btnAppearance;

    @Display(getName = "Button Behavior")
    @AndroidFindBy(id = ANDROID_BASE_ID + "behavior")
    private MobileElement btnBehavior;

    @Display(getName = "Button Language")
    @AndroidFindBy(id = ANDROID_BASE_ID + "language")
    private MobileElement btnLanguage;

    @Display(getName = "Button Change Log")
    @AndroidFindBy(id = ANDROID_BASE_ID + "changelog")
    private MobileElement btnChangeLog;

    @Display(getName = "Button Support")
    @AndroidFindBy(id = ANDROID_BASE_ID + "support")
    private MobileElement btnSupport;

    @Display(getName = "Button Upload Debug Logs")
    @AndroidFindBy(id = ANDROID_BASE_ID + "upload_debug_logs")
    private MobileElement btnUploadDebugLogs;

    private final String expectedToolbarTitle = "User Settings";
    private final String expectedSettingsTitle = "User Settings";

    public FootContainer footContainer;
    public LogOutContainer logOutContainer;

    protected final AppiumDriver<MobileElement> driver;

    public UserSettingsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        footContainer = new FootContainer(driver);
        this.driver = driver;
    }

    @Step
    @Override
    public boolean isPageDisplayed() {
        waitToBeVisible(actualToolbarTitle);
        return isAllElementsDisplayed(Direction.UP);
    }

    @Step
    public boolean isTitlesAreSame() {
        softAssert.assertEquals(actualToolbarTitle.getText(), expectedToolbarTitle);
        softAssert.assertEquals(actualSettingsTitle.getText(), expectedSettingsTitle);
        softAssert.assertAll();
        return true;
    }

    @Step
    public LogOutContainer clickOnBtnLogOut() {
        log.info("Clicking on the button log out.");
        waitToBeClickable(btnLogOut).click();
        logOutContainer = new LogOutContainer(driver);
        return logOutContainer;
    }

    @Step
    public void clickOnBtnLanguage() {
        log.info("Clicking on the button language.");
        swipeScreen(Direction.UP, btnLanguage, 1);
        waitToBeClickable(btnLanguage).click();
    }
}

