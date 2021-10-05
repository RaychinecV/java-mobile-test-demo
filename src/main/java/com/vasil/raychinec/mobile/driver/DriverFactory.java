package com.vasil.raychinec.mobile.driver;

import com.vasil.raychinec.mobile.constants.ApplicationProperties;
import com.vasil.raychinec.mobile.exeption.UnsupportedMobilePlatformException;

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

import static com.vasil.raychinec.mobile.constants.ApplicationConstants.BROSWERSTACK_URL;
import static com.vasil.raychinec.mobile.constants.ApplicationConstants.LOCAL_URL;
import static com.vasil.raychinec.mobile.constants.ApplicationProperties.IS_BROWSERSTACK;

@Log4j2
@Setter
@Accessors(chain = true)
public class DriverFactory implements Factory<AppiumDriver<MobileElement>, DriverFactory> {

    private Platforms platforms = ApplicationProperties.PLATFORM;
    private URL url;

    @Override
    public AppiumDriver<MobileElement> create() {
        final DesiredCapabilities capabilities = new CapabilityFactory().create();
        switch (platforms) {
            case ANDROID:
                return new AndroidDriver<>(getAppiumURL(), capabilities);
            case IOS:
                return new IOSDriver<>(getAppiumURL(), capabilities);
            default:
                throw new UnsupportedMobilePlatformException("Sorry, but that framework do not support platform", platforms.name());
        }
    }

    private URL getAppiumURL() {
        try {
            url = new URL(IS_BROWSERSTACK ? BROSWERSTACK_URL : LOCAL_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
