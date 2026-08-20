package com.tirumareddy.qa.base;

import com.tirumareddy.qa.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    private static String test2 = "Test initialization";

    @BeforeEach
    void setup() {
        String browser =
                System.getProperty("browser", "chrome");

        driver = DriverFactory.createDriver(browser);

        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(2));

        driver.get(
                "https://www.selenium.dev/selenium/web/web-form.html"
        );
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
            driver.close();
        }
    }
}
