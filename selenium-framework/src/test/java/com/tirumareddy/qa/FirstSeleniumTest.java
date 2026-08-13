package com.tirumareddy.qa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstSeleniumTest {
    @Test
    void shouldOpenBrowser() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        String actualTitle = driver.getTitle();

        String expectedTitle = "Google";

        assertEquals(expectedTitle, actualTitle);

        WebElement searchBox =
                driver.findElement(By.name("q"));

        searchBox.sendKeys("Selenium Webdriver");

  /*      WebElement feelingLuckyButton =
                driver.findElement(By.name("btnI"));*/

        WebElement feelingLuckyButton =
                driver.findElement(By.name("btnI"));

        System.out.println(
                "Displayed: " + feelingLuckyButton.isDisplayed()
        );

        System.out.println(
                "Enabled: " + feelingLuckyButton.isEnabled()
        );

        List<WebElement> feelingLuckyButtons =
                driver.findElements(By.name("btnI"));

        System.out.println(
                "Number of matches: " + feelingLuckyButtons.size()
        );

        for (WebElement element : feelingLuckyButtons) {
            System.out.println(
                    "Displayed = " + element.isDisplayed()
                            + ", Enabled = " + element.isEnabled()
            );
        }

        WebElement visibleFeelingLuckyButton =
                feelingLuckyButtons.stream()
                        .filter(WebElement::isDisplayed)
                        .findFirst()
                        .orElseThrow();

        visibleFeelingLuckyButton.click();

//        feelingLuckyButton.click();

        driver.quit();
    }
}
