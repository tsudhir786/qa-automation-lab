package com.tirumareddy.qa.tests;

import com.tirumareddy.qa.base.BaseTest;
import com.tirumareddy.qa.pages.WebFormPage;
import com.tirumareddy.qa.utils.TestFailureWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestFailureWatcher.class)
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
//Testing the screenshot failure
        assertEquals(
                "Received Wrong!",
                webFormPage.getMessage()
        );
    }
}
