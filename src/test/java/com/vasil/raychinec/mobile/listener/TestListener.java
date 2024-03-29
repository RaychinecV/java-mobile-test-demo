package com.vasil.raychinec.mobile.listener;


import com.vasil.raychinec.mobile.driver.Driver;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test {} is passed.", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test {} is failed!!!", result.getName());
        this.takeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test {} is skipped!!!", result.getName());
        this.takeScreenshot();
    }

    @Attachment(value = "ScreenshotAttachment", type = "image/png")
    public byte[] takeScreenshot() {
        byte[] imageInByte = null;
        try {
            final BufferedImage image = ImageIO.read(((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE));
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            log.info("Exception log", e);
        }
        return imageInByte;
    }
}
