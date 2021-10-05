package com.vasil.raychinec.mobile.services;

import com.vasil.raychinec.mobile.constants.SwipeDirection;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

@Log4j2
public class Swipe implements SwipeService {

    private final Wait<WebDriver> wait;
    private final Dimension winSize;
    private final TouchAction touchAction;

    public Swipe(WebDriver driver, Dimension winSize, TouchAction touchAction) {
        Objects.requireNonNull(driver);
        Objects.requireNonNull(winSize);
        Objects.requireNonNull(touchAction);

        this.winSize = winSize;
        this.touchAction = touchAction;
        wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .ignoreAll(Arrays.asList(
                        ElementNotVisibleException.class,
                        NoSuchElementException.class,
                        StaleElementReferenceException.class,
                        WebDriverException.class));
    }

    @Override
    public void swipeUpUntilVisible(MobileElement element) {
        this.swipeUntilVisible(SwipeDirection.UP, element);
    }

    @Override
    public void swipeDownUntilVisible(MobileElement element) {
        this.swipeUntilVisible(SwipeDirection.DOWN, element);
    }

    @Override
    public void swipeLeftUntilVisible(MobileElement element) {
        this.swipeUntilVisible(SwipeDirection.LEFT, element);
    }

    @Override
    public void swipeRightUntilVisible(MobileElement element) {
        this.swipeUntilVisible(SwipeDirection.RIGHT, element);
    }

    @Override
    public void swipeUntilVisible(SwipeDirection direction, MobileElement element) {
        wait.until(driver -> {
            swipeTo(direction);
            return element.getCenter();
        });
        log.info("Successfully scrolled to mobile element");
    }

    @Override
    public void swipeTo(SwipeDirection direction) {
        switch (direction) {
            case UP:
                swipeUp();
                break;
            case DOWN:
                swipeDown();
                break;
            case RIGHT:
                swipeLeftToRight();
                break;
            case LEFT:
                swipeRightToLeft();
                break;
            case NO:
                break;
        }
    }

    @Override
    public void swipeUp() {
        this.swipeVerticallyByPercent(20, 90);
    }

    @Override
    public void swipeDown() {
        this.swipeVerticallyByPercent(90, 20);
    }

    @Override
    public void swipeLeftToRight() {
        this.swipeHorizontallyByPercent(10, 90);
    }

    @Override
    public void swipeRightToLeft() {
        this.swipeHorizontallyByPercent(90, 10);
    }

    @Step
    @Override
    public void swipeVerticallyByPercent(final int startPercent, final int endPercent) {
        final int startY = (winSize.height * startPercent) / 100;
        final int endY = (winSize.height * endPercent) / 100;
        final int stepX = (winSize.width * 50) / 100;

        final Point startPoint = new Point(stepX, startY);
        final Point endPoint = new Point(stepX, endY);
        this.swipeTo(startPoint, endPoint);
    }

    @Step
    @Override
    public void swipeVerticallyByInt(final int start, final int end) {
        final int startY = start > winSize.height ? winSize.height - winSize.height / 2 : winSize.height - start;
        final int endY = end > winSize.width ? winSize.width - winSize.width / 2 : winSize.width - end;
        final int stepX = winSize.width / 2;

        final Point startPoint = new Point(stepX, startY);
        final Point endPoint = new Point(stepX, endY);
        this.swipeTo(startPoint, endPoint);
    }

    @Step
    @Override
    public void swipeHorizontallyByPercent(final int startPercent, final int endPercent) {
        final int startX = (winSize.width * startPercent) / 100;
        final int endX = (winSize.width * endPercent) / 100;
        final int stepY = (winSize.height * 50) / 100;

        final Point startPoint = new Point(startX, stepY);
        final Point endPoint = new Point(endX, stepY);
        this.swipeTo(startPoint, endPoint);
    }

    @Step
    @Override
    public void swipeByPercent(final int startXPercent, final int endXPercent, final int startYPercent, final int endYPercent) {
        final int startX = (winSize.width * startXPercent) / 100;
        final int endX = (winSize.width * endXPercent) / 100;
        final int startY = (winSize.height * startYPercent) / 100;
        final int endY = (winSize.height * endYPercent) / 100;

        final Point startPoint = new Point(startX, startY);
        final Point endPoint = new Point(endX, endY);
        this.swipeTo(startPoint, endPoint);
    }

    @Step
    @Override
    public void swipeByInt(final int startXint, final int endXint, final int startYint, final int endYint) {
        final int startX = winSize.width - startXint;
        final int endX = winSize.width - endXint;
        final int startY = winSize.height - startYint;
        final int endY = winSize.height - endYint;

        final Point startPoint = new Point(startX, startY);
        final Point endPoint = new Point(endX, endY);
        this.swipeTo(startPoint, endPoint);
    }

    @Step
    @Override
    public void swipeToTop(final Point start) {
        final Point endPoint = new Point(start.x, 0);
        swipeTo(start, endPoint);
    }


    @Override
    public void swipeTo(Point start, Point end) {
        touchAction
                .longPress(PointOption.point(start))
                .moveTo(PointOption.point(end))
                .release()
                .perform();
    }
}
