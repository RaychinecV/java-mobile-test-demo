package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import com.vasil.raychinec.mobile.pages.conteiners.FootContainer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

import static com.vasil.raychinec.mobile.constants.LocatorConstants.ANDROID_BASE_ID;

public class EmptyFriendsPage extends BasePage {
    @Display(getName = "Main title")
    @AndroidFindBy(id = ANDROID_BASE_ID + "empty_friends_state_title")
    private MobileElement actualTitleLabel;

    @Display(getName = "Sub title")
    @AndroidFindBy(xpath = "(//*[@class='android.widget.TextView'])[4]")
    private MobileElement actualSubTitleLabel;

    @Display(getName = "Button add friend")
    @AndroidFindBy(id = ANDROID_BASE_ID + "empty_friends_state_add_friend")
    private MobileElement btnAddFriend;

    @Display(getName = "Field find conversation")
    @AndroidFindBy(id = ANDROID_BASE_ID + "channels_list_search")
    private MobileElement fldFindConversation;

    @Display(getName = "Button create server")
    @AndroidFindBy(accessibility = "Create a new Server")
    private MobileElement btnCreateServer;

    @Display(getName = "Button create DM")
    @AndroidFindBy(accessibility = "Create DM")
    private MobileElement btnCreateDM;

    private final String expectedTitleLabel = "Wumpus is waiting on friends. You don’t have to though!";
    private final String expectedSubTitleLabel = "Try adding a friend with their username, or scan to see who is nearby.";

    public FootContainer footContainer;

    protected final AppiumDriver<MobileElement> driver;

    public EmptyFriendsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        footContainer = new FootContainer(driver);
        this.driver = driver;
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

