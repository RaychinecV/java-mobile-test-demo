package com.vasil.raychinec.mobile.driver;

import com.vasil.raychinec.mobile.exeption.UnsupportedMobilePlatformException;
import com.vasil.raychinec.mobile.services.Factory;
import com.vasil.raychinec.mobile.constants.ApplicationConstants;
import com.vasil.raychinec.mobile.constants.ApplicationProperties;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.vasil.raychinec.mobile.constants.ApplicationProperties.IS_BROWSERSTACK;


@Log4j2
@Setter
@Accessors(chain = true)
public class CapabilityFactory implements Factory<DesiredCapabilities, CapabilityFactory> {
    private Platforms platform = ApplicationProperties.PLATFORM;

    @Override
    public DesiredCapabilities create() {
        switch (platform) {
            case ANDROID:
                return IS_BROWSERSTACK ? getAndroidBrowserstackCapability() : getAndroidCapability();
            case IOS:
                return getIOSCapability();
            default:
                throw new UnsupportedMobilePlatformException("Sorry, but that framework do not support platform", platform.name());
        }
    }

    private DesiredCapabilities getAndroidCapability() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, ApplicationProperties.APK_PATH);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ApplicationConstants.APP_PACKAGE);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ApplicationConstants.APP_ACTIVITY);
        return capabilities;
    }

    private DesiredCapabilities getAndroidBrowserstackCapability() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserstack.user", "vladhaypel_3Y12qk");
        capabilities.setCapability("browserstack.key", "1DYcPySqtHTHQaSmjWzo");
        capabilities.setCapability("app", "bs://b1a19d735128a32b192ad96607930fe1e88271c4");
        capabilities.setCapability("device", "Google Pixel 3");
        capabilities.setCapability("os_version", "9.0");
        capabilities.setCapability("project", "Discord");
        capabilities.setCapability("build", "Java Android");
        capabilities.setCapability("name", "Regression suite");
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
