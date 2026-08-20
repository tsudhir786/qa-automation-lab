package com.tirumareddy.qa.tests;

import com.tirumareddy.qa.base.BaseTest;
import com.tirumareddy.qa.pages.WebFormPage;
import com.tirumareddy.qa.utils.TestFailureWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestFailureWatcher.class)
public class WebFormTest extends BaseTest {
    private WebFormPage webFormPage;
    private static String test1 = "Test initialization";

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
    void shouldDisplayExpectedDropdownOptions() {

        List<String> expectedOptions =
                List.of(
                        "Open this select menu",
                        "One",
                        "Two",
                        "Three"
                );

        List<String> actualOptions =
                webFormPage.getDropdownOptions();

        assertEquals(
                expectedOptions,
                actualOptions
        );
    }

    @Test
    void shouldSubmitFormSuccessfully() {
        webFormPage.enterText("Sudhir");

        webFormPage.submit();
//Testing the screenshot failure
        assertEquals(
                "Received!",
                webFormPage.getMessage()
        );
    }
}
