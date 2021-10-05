package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import com.vasil.raychinec.mobile.annotations.SwipeTo;
import com.vasil.raychinec.mobile.driver.Platforms;
import com.vasil.raychinec.mobile.services.Displayable;
import com.vasil.raychinec.mobile.services.Swipe;
import com.vasil.raychinec.mobile.services.SwipeService;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;
import java.util.List;

import static com.vasil.raychinec.mobile.constants.ApplicationProperties.PLATFORM;

@Log4j2
public abstract class BasePage implements Displayable {

    protected WebDriver driver;
    @Getter
    protected SwipeService swipe;
    private final Dimension winSize;
    private final TouchAction touchAction;

    protected final SoftAssert softAssert;
    protected int defaultWaitTimeout = 20;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.touchAction = new TouchAction((AppiumDriver) driver);
        this.winSize = driver.manage().window().getSize();
        this.swipe = new Swipe(driver, winSize, touchAction);
        this.softAssert = new SoftAssert();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step
    protected MobileElement find(final By element) {
        return this.driver.findElement(element);
    }

    @Step
    protected List<MobileElement> findAll(final By element) {
        return this.driver.findElements(element);
    }

    @Step
    protected MobileElement waitToBeVisible(final MobileElement element) {
        new WebDriverWait(driver, defaultWaitTimeout)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    @Step
    protected MobileElement waitToBeVisible(final MobileElement element, final int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    @Step
    protected MobileElement waitToBePresent(final MobileElement element) {
        new WebDriverWait(driver, defaultWaitTimeout)
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> element.getCenter());
        return element;
    }

    @Step
    protected MobileElement waitToBePresent(final MobileElement element, final int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> element.getCenter());
        return element;
    }

    @Step
    protected boolean isElementPresent(MobileElement element) {
        return isElementPresent(element, defaultWaitTimeout);
    }

    @Step
    protected boolean isElementPresent(MobileElement element, int timeout) {
        try {
            waitToBePresent(element, timeout);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    @Step
    protected MobileElement waitToBeClickable(final MobileElement element) {
        new WebDriverWait(driver, defaultWaitTimeout)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    @Step
    protected MobileElement waitToBeClickable(final MobileElement element, final int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    @Step
    protected void clickOnElement(MobileElement element) {
        element.click();
    }

    @Step
    protected void enterText(final MobileElement element, final String text, final boolean isClear) {
        this.waitToBeVisible(element);
        if (isClear) {
            element.clear();
        }
        element.sendKeys(text);
        this.hideKeyboard();
    }

    @Step
    protected void enterText(final MobileElement element, final String text) {
        this.enterText(element, text, true);
    }

    @Step
    protected void hideKeyboard() {
        if (PLATFORM == Platforms.ANDROID) {
            if (((AndroidDriver) driver).isKeyboardShown()) {
                ((AndroidDriver) driver).hideKeyboard();
                log.info("Closed Android keyboard.");
            }
        } else {
            if (((IOSDriver) driver).isKeyboardShown()) {
                ((IOSDriver) driver).hideKeyboard();
                log.info("Closed IOS keyboard.");
            }
        }
    }

    @Step
    protected boolean isAllElementsDisplayed() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Display.class)) {
                try {
                    field.setAccessible(true);
                    MobileElement el = (MobileElement) field.get(this);
                    if (!isElementPresent(el, 0)) {
                        swipe.swipeTo(field.getAnnotation(SwipeTo.class).swipeTo());
                    }
                    softAssert.assertTrue(isElementPresent(el, 0), field.getAnnotation(Display.class).name() + " isn't displayed !!!");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(false);
            }
        }
        softAssert.assertAll();
        log.info("All elements on the page {} are displayed.", this.getClass().getSimpleName());
        return true;
    }
}

