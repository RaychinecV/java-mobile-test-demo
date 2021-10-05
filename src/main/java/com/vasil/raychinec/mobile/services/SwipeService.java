package com.vasil.raychinec.mobile.services;

import com.vasil.raychinec.mobile.constants.SwipeDirection;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Point;

public interface SwipeService {

    void swipeUpUntilVisible(MobileElement element);

    void swipeDownUntilVisible(MobileElement element);

    void swipeLeftUntilVisible(MobileElement element);

    void swipeRightUntilVisible(MobileElement element);

    void swipeUntilVisible(SwipeDirection scrollDirection, MobileElement element);

    void swipeUp();

    void swipeDown();

    void swipeLeftToRight();

    void swipeRightToLeft();

    void swipeVerticallyByPercent(int startPercent, int endPercent);

    void swipeVerticallyByInt(int startPercent, int endPercent);

    void swipeHorizontallyByPercent(int startPercent, int endPercent);

    void swipeByPercent(int startXPercent, int endXPercent, int startYPercent, int endYPercent);

    void swipeByInt(int startXPercent, int endXPercent, int startYPercent, int endYPercent);

    void swipeToTop(Point start);

    void swipeTo(SwipeDirection direction);

    void swipeTo(Point start, Point end);

}
