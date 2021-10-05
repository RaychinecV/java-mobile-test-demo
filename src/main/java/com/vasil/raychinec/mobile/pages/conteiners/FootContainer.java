package com.vasil.raychinec.mobile.pages.conteiners;

import com.vasil.raychinec.mobile.annotations.Display;
import com.vasil.raychinec.mobile.pages.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;

@Log4j2
public class FootContainer extends BasePage {

    @Display(name = "Footer menu")
    @AndroidFindBy(id = ANDROID_BASE_ID + "tabs_host_bottom_nav_tabs_container")
    private MobileElement footerNavigationContainer;

    @Display(name = "Footer menu button home")
    @AndroidFindBy(id = ANDROID_BASE_ID + "tabs_host_bottom_nav_home_item")
    private MobileElement btnFooterHome;

    @Display(name = "Footer menu button friends")
    @AndroidFindBy(id = ANDROID_BASE_ID + "tabs_host_bottom_nav_friends_item")
    private MobileElement btnFooterFriends;

    @Display(name = "Footer menu settings")
    @AndroidFindBy(id = ANDROID_BASE_ID + "tabs_host_bottom_nav_user_settings_item")
    private MobileElement btnFooterSettings;

    public FootContainer(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step
    public boolean isRequiredPageElementsAreDisplayed() {
        isAllElementsDisplayed();
        return false;
    }

    @Step
    public void clickOnBtnHome() {
        log.info("Clicking on the footer button Home.");
        waitToBeClickable(btnFooterHome).click();
    }

    @Step
    public void clickOnBtnSettings() {
        log.info("Clicking on the footer button Settings.");
        waitToBeClickable(btnFooterSettings).click();
    }

    @Step
    public void clickOnBtnFriends() {
        log.info("Clicking on the footer button Friends.");
        waitToBeClickable(btnFooterFriends).click();
    }

}
