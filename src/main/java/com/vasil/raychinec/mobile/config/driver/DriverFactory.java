package com.vasil.raychinec.mobile.config.driver;

import com.vasil.raychinec.mobile.exeption.UnsupportedPlatformException;

import com.vasil.raychinec.mobile.services.Factory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

@Log4j2
@Setter
@Accessors(chain = true)
public class DriverFactory implements Factory<AppiumDriver<MobileElement>, DriverFactory> {

    private Platforms platforms = Platforms.ANDROID;
    private URL url;

    @Override
    public AppiumDriver<MobileElement> create() {
        final DesiredCapabilities capabilities = new CapabilityFactory().setPlatforms(platforms).create();
        switch (platforms) {
            case ANDROID:
                return new AndroidDriver<MobileElement>(getAppiumURL(), capabilities);
            case IOS:
                return new IOSDriver<MobileElement>(getAppiumURL(), capabilities);
            default:
                throw new UnsupportedPlatformException("Sorry, but that framework do not support platform", platforms.name());
        }
    }

    private URL getAppiumURL() {
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
