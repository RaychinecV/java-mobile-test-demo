package com.vasil.raychinec.mobile.pages;

import com.vasil.raychinec.mobile.annotations.Display;
import com.vasil.raychinec.mobile.config.driver.Platforms;
import com.vasil.raychinec.mobile.services.Displayable;
import com.vasil.raychinec.mobile.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import sun.rmi.runtime.Log;

import java.lang.reflect.Field;
import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.vasil.raychinec.mobile.utils.ApplicationProperties.PLATFORM;

@Log4j2
public abstract class BasePage implements Displayable {
    protected final AppiumDriver<MobileElement> driver;
    protected int WAIT_TIMEOUT = 20;
    protected final SoftAssert softAssert;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        softAssert = new SoftAssert();
    }

    @Step
    public MobileElement find(final By element) {
        return this.driver.findElement(element);
    }

    @Step
    public List<MobileElement> findAll(final By element) {
        return this.driver.findElements(element);
    }

    @Step
    public MobileElement waitToBeVisible(final MobileElement element) {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    @Step
    public MobileElement waitToBeVisible(final MobileElement element, final int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    @Step
    public MobileElement waitToBePresent(final MobileElement element) {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> element.getCenter());
        return element;
    }

    @Step
    public MobileElement waitToBePresent(final MobileElement element, final int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> element.getCenter());
        return element;
    }

    @Step
    public boolean isElementPresent(MobileElement element) {
        return isElementPresent(element, WAIT_TIMEOUT);
    }

    @Step
    public boolean isElementPresent(MobileElement element, int timeout) {
        try {
            waitToBePresent(element, timeout);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    @Step
    public MobileElement waitToBeClickable(final MobileElement element) {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    @Step
    public MobileElement waitToBeClickable(final MobileElement element, final int timeOut) {
        new WebDriverWait(driver, timeOut)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    @Step
    public void clickOnElement(MobileElement element) {
        element.click();
    }

    @Step
    public void enterText(final MobileElement element, final String text, final boolean isClear) {
        this.waitToBeVisible(element);
        if (isClear) {
            element.clear();
        }
        element.sendKeys(text);
        this.hideKeyboard();
    }

    @Step
    public void enterText(final MobileElement element, final String text) {
        this.enterText(element, text, true);
    }

    public void hideKeyboard() {
        if (PLATFORM.toUpperCase().equals(Platforms.ANDROID.name())) {
            if (((AndroidDriver) driver).isKeyboardShown()) {
                driver.hideKeyboard();
                log.info("Closed Android keyboard.");
            }
        } else {
            if (((IOSDriver) driver).isKeyboardShown()) {
                driver.hideKeyboard();
                log.info("Closed IOS keyboard.");
            }
        }
    }

    public boolean isAllElementsDisplayed() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Display.class)) {
                try {
                    field.setAccessible(true);
                    MobileElement el = (MobileElement) field.get(this);
                    softAssert.assertTrue(isElementPresent(el, 1), field.getAnnotation(Display.class).getName() + " isn't displayed !!!");
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

    public boolean isAllElementsDisplayed(Direction direction) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Display.class)) {
                try {
                    field.setAccessible(true);
                    MobileElement el = (MobileElement) field.get(this);
                    if (!isElementPresent(el, 1)) {
                        swipeScreen(direction, el, 2);
                    }
                    softAssert.assertTrue(isElementPresent(el, 1), field.getAnnotation(Display.class).getName() + " isn't displayed !!!");
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

    public void swipeScreen(Direction dir, MobileElement element, int endCount) {
        log.info("Swipe screen {}.", dir);

        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 1000;
        int edgeBorder = 10;

        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = driver.manage().window().getSize();
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("SwipeScreen(): dir: '" + dir + "' NOT supported!!!");
        }
        int startCount = 0;
        while (startCount != endCount) {
            log.info("Swipe {} times.", startCount);
            new TouchAction(driver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
            Utils.sleepMils(ANIMATION_TIME);
            startCount++;
            if (isElementPresent(element, 1)) {
                break;
            }
        }
    }


    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }
}

