package com.vasil.raychinec.mobile.config.driver;

import com.vasil.raychinec.mobile.exeption.UnsupportedPlatformException;
import com.vasil.raychinec.mobile.services.Factory;
import com.vasil.raychinec.mobile.constants.ApplicationConstants;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;


@Log4j2
@Setter
@Accessors(chain = true)
public class CapabilityFactory implements Factory<DesiredCapabilities, CapabilityFactory> {
    private Platforms platforms = Platforms.ANDROID;

    @Override
    public DesiredCapabilities create() {
        switch (platforms) {
            case ANDROID:
                return getAndroidCapability();
            case IOS:
                return getIOSCapability();
            default:
                throw new UnsupportedPlatformException("Sorry, but that framework do not support platform", platforms.name());
        }
    }

    private DesiredCapabilities getAndroidCapability() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi 7A");
        capabilities.setCapability(MobileCapabilityType.UDID, "acab80aa0406");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ApplicationConstants.APP_PACKAGE);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ApplicationConstants.APP_ACTIVITY);
        return capabilities;
    }

    private DesiredCapabilities getIOSCapability() {
        DesiredCapabilities capabilities = DesiredCapabilities.iphone();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "");
        capabilities.setCapability(MobileCapabilityType.UDID, "");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ApplicationConstants.APP_PACKAGE);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ApplicationConstants.APP_ACTIVITY);
        return capabilities;
    }
}
