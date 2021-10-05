package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import com.vasil.raychinec.mobile.pages.conteiners.FootContainer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;

public class EmptyFriendsPage extends BasePage {

    @Display(name = "Main title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "empty_friends_state_title")
    private MobileElement actualTitleLabel;

    @Display(name = "Empty friends image")
    @AndroidFindBy(id = ANDROID_BASE_ID + "empty_friends_state_image")
    private MobileElement imageEmptyFriends;

    @Display(name = "Sub title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "subtitle")
    private MobileElement actualSubTitleLabel;

    @Display(name = "Button add friend")
    @AndroidFindBy(id = ANDROID_BASE_ID + "empty_friends_state_add_friend")
    private MobileElement btnAddFriend;

    @Display(name = "Direct Messages")
    @AndroidFindBy(accessibility = "Direct Messages")
    private MobileElement iconMessage;

    @Display(name = "Button create server")
    @AndroidFindBy(accessibility = "Create a new Server")
    private MobileElement btnCreateServer;

    private final String expectedTitleLabel = "Wumpus is waiting on friends. You don’t have to though!";
    private final String expectedSubTitleLabel = "Try adding a friend with their username, or scan to see who is nearby.";

    public FootContainer footContainer;

    public EmptyFriendsPage(WebDriver driver) {
        super(driver);
        footContainer = new FootContainer(driver);
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

