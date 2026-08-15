package com.tirumareddy.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class WebFormTest extends BaseTest {

    @Test
    void shouldEnterText() {

        WebElement textInput =
                driver.findElement(By.xpath("//input[@id='my-text-id']"));

        textInput.sendKeys("Selenium Framework");

        assertEquals(
                "Selenium Framework",
                textInput.getDomProperty("value")
        );
    }

    @Test
    void shouldSelectCheckbox() {

        WebElement checkbox =
                driver.findElement(By.cssSelector("#my-check-2"));

        assertFalse(checkbox.isSelected());

        checkbox.click();

        assertTrue(checkbox.isSelected());
    }

    @Test
    void shouldSelectRadioButton() {

        WebElement radioButton =
                driver.findElement(By.id("my-radio-2"));

        assertFalse(radioButton.isSelected());

        radioButton.click();

        assertTrue(radioButton.isSelected());
    }

    @Test
    void shouldSelectDropdownOption() {

        WebElement dropdownElement =
                driver.findElement(By.cssSelector(".form-select"));

        Select dropdown = new Select(dropdownElement);

        dropdown.selectByVisibleText("Two");

        assertEquals(
                "Two",
                dropdown.getFirstSelectedOption().getText()
        );
    }

    @Test
    void shouldSubmitFormSuccessfully() {

        WebElement textInput =
                driver.findElement(By.name("my-text"));

        textInput.sendKeys("Sudhir");

        WebElement submitButton =
                driver.findElement(By.tagName("button"));

        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(5));

        wait.until(
                ExpectedConditions.urlContains("submitted-form.html")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );

        WebElement message =
                driver.findElement(By.id("message"));

        assertEquals(
                "Received!",
                message.getText()
        );

    }
}
