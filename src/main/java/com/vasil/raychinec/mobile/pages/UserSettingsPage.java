package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import com.vasil.raychinec.mobile.annotations.SwipeTo;
import com.vasil.raychinec.mobile.constants.SwipeDirection;
import com.vasil.raychinec.mobile.pages.conteiners.FootContainer;
import com.vasil.raychinec.mobile.pages.conteiners.LogOutContainer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;

@Log4j2
@Getter
public class UserSettingsPage extends BasePage {

    @Display(name = "User name")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "username_text")
    private MobileElement userName;

    @Display(name = "Settings title label")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "user_settings_header")
    private MobileElement actualSettingsTitle;

    @Display(name = "Toolbar title label")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "toolbar_title")
    private MobileElement actualToolbarTitle;

    @Display(name = "Button Log Out")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(accessibility = "Log Out")
    private MobileElement btnLogOut;

    @Display(name = "Button Set Status")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(xpath = "//*[@text='Set Status']")
    private MobileElement btnSetStatus;

    @Display(name = "Button My Account")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "account")
    private MobileElement btnMyAccount;

    @Display(name = "Button Privacy & Safety")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "privacy")
    private MobileElement btnPrivacyAndSafety;

    @Display(name = "Button Authorized Apps")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "authorized_apps")
    private MobileElement btnAuthorizedApps;

    @Display(name = "Button Connections")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "connections")
    private MobileElement btnConnections;

    @Display(name = "Button Scan QR Code")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "qr_scanner")
    private MobileElement btnScanQRCode;

    @Display(name = "Button Subscribe Today")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "settings_nitro")
    private MobileElement btnSubscribeToday;

    @Display(name = "Button Boosts")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "nitro_boosting")
    private MobileElement btnBoosts;

    @Display(name = "Button Nitro Gifting")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "nitro_gifting")
    private MobileElement btnNitroGifting;

    @Display(name = "Button Voice & Video")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "voice")
    private MobileElement btnVoiceAndVideo;

    @Display(name = "Button Notifications")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "notifications")
    private MobileElement btnNotifications;

    @Display(name = "Button Activity Status")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "activity_status")
    private MobileElement btnActivityStatus;

    @Display(name = "Button Text & Images")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "text_images_settings")
    private MobileElement btnTextAndImages;

    @Display(name = "Button Appearance")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "appearance")
    private MobileElement btnAppearance;

    @Display(name = "Button Behavior")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "behavior")
    private MobileElement btnBehavior;

    @Display(name = "Button Language")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "language")
    private MobileElement btnLanguage;

    @Display(name = "Button Change Log")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "changelog")
    private MobileElement btnChangeLog;

    @Display(name = "Button Support")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "support")
    private MobileElement btnSupport;

    @Display(name = "Button Upload Debug Logs")
    @SwipeTo(swipeTo = SwipeDirection.DOWN)
    @AndroidFindBy(id = ANDROID_BASE_ID + "upload_debug_logs")
    private MobileElement btnUploadDebugLogs;

    private final String expectedToolbarTitle = "User Settings";
    private final String expectedSettingsTitle = "User Settings";

    public FootContainer footContainer;
    public LogOutContainer logOutContainer;

    public UserSettingsPage(WebDriver driver) {
        super(driver);
        footContainer = new FootContainer(driver);
    }

    @Step
    @Override
    public boolean isRequiredPageElementsAreDisplayed() {
        waitToBeVisible(actualToolbarTitle);
        return isAllElementsDisplayed();
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
        waitToBeClickable(btnLanguage).click();
    }

    @Step
    public void clickOnBtnConnections() {
        log.info("Clicking on the button Connections.");
        waitToBeClickable(btnConnections).click();
    }
}

