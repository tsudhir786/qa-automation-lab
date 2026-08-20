package com.tirumareddy.qa.pages;

import com.tirumareddy.qa.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class WebFormPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private static String test3 = "Test initialization";

    public WebFormPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils =
                new WaitUtils(
                        this.driver,
                        Duration.ofSeconds(5));
    }

    private final By textInput =
            By.id("my-text-id");

    private final By checkbox =
            By.id("my-check-2");

    private final By radioButton =
            By.id("my-radio-2");

    private final By dropdownLocator =
            By.cssSelector(".form-select");

    private final By submitButton =
            By.tagName("button");

    private final By message =
            By.id("message");

    public void selectCheckbox() {
        driver.findElement(checkbox)
                .click();
    }

    public void submit() {
        waitUtils
                .waitForClickable(submitButton)
                .click();
    }

    public String getMessage() {
        return waitUtils
                .waitForVisibility(message)
                .getText();
    }

    public String getTextValue() {
        return driver.findElement(textInput)
                .getDomProperty("value");
    }

    public void selectDropdown(String option) {
        Select select =
                new Select(driver.findElement(dropdownLocator));
        select.selectByVisibleText(option);
    }

    public List<String> getDropdownOptions() {
        Select select =
                new Select(driver.findElement(dropdownLocator));

        return select.getOptions()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public void selectRadiButton() {
        driver.findElement(radioButton)
                .click();
    }

    public String getSelectedDropdownOption() {
        Select select =
                new Select(driver.findElement(dropdownLocator));
        return select.getFirstSelectedOption().getText();
    }

    public void enterText(String value) {
        driver.findElement(textInput)
                .sendKeys(value);
    }

    public boolean isCheckboxSelected() {
        return driver.findElement(checkbox)
                .isSelected();
    }

    public boolean isRadiButtonSelected() {
        return driver.findElement(radioButton)
                .isSelected();
    }
}
