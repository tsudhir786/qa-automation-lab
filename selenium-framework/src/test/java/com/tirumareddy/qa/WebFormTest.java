package com.tirumareddy.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class WebFormTest extends BaseTest {
    private WebFormPage webFormPage;

    @BeforeEach
    void initializePage() {
        webFormPage = new WebFormPage(driver);
    }

    @Test
    void shouldEnterText() {

        webFormPage.enterText("Selenium Framework");

        assertEquals(
                "Selenium Framework",
                webFormPage.getTextValue()
        );
    }

    @Test
    void shouldSelectCheckbox() {

        assertFalse(webFormPage.isCheckboxSelected());

        webFormPage.selectCheckbox();

        assertTrue(webFormPage.isCheckboxSelected());
    }

    @Test
    void shouldSelectRadioButton() {

        assertFalse(webFormPage.isRadiButtonSelected());

        webFormPage.selectRadiButton();

        assertTrue(webFormPage.isRadiButtonSelected());
    }

    @Test
    void shouldSelectDropdownOption() {

        webFormPage.selectDropdown("Two");
        assertEquals(
                "Two",
                webFormPage.getSelectedDropdownOption()
        );
    }

    @Test
    void shouldSubmitFormSuccessfully() {
        webFormPage.enterText("Sudhir");

        webFormPage.submit();

        assertEquals(
                "Received!",
                webFormPage.getMessage()
        );
    }
}
