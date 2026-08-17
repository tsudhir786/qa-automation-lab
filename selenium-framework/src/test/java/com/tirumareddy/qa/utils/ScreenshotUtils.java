package com.tirumareddy.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    public static void captureScreenshot(
            WebDriver driver,
            String testName) {

        try {
            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            Path destination =
                    Paths.get(
                            "target",
                            "screenshots",
                            testName + ".png"
                    );

            Files.createDirectories(
                    destination.getParent()
            );

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println(
                    "Saving screenshot to: "
                            + destination.toAbsolutePath()
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to capture screenshot",
                    e
            );
        }
    }
}