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
public class LogOutContainer extends BasePage {
    @Display(name = "Header label")
    @AndroidFindBy(id = ANDROID_BASE_ID + "notice_header")
    private MobileElement actualHeaderLabel;

    @Display(name = "Body question text")
    @AndroidFindBy(id = ANDROID_BASE_ID + "notice_body_text")
    private MobileElement actualBodyQuestionText;

    @Display(name = "Button confirm log out")
    @AndroidFindBy(id = ANDROID_BASE_ID + "notice_ok")
    private MobileElement btnConfirmLogOut;

    @Display(name = "Button cancel")
    @AndroidFindBy(id = ANDROID_BASE_ID + "notice_cancel")
    private MobileElement btnCancel;

    public LogOutContainer(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    @Step
    public boolean isRequiredPageElementsAreDisplayed() {
        return isAllElementsDisplayed();
    }

    @Step
    public void clickBtnConfirmLogOut() {
        log.info("Clicking on the confirm Log Out button.");
        waitToBeClickable(btnConfirmLogOut).click();
    }
}
