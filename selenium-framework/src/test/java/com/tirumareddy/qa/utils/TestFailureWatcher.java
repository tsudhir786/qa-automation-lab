package com.tirumareddy.qa.utils;

import com.tirumareddy.qa.base.BaseTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestFailureWatcher
        implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isEmpty()) {
            return;
        }

        Object testInstance =
                context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest baseTest) {
            ScreenshotUtils.captureScreenshot(
                    baseTest.getDriver(),
                    context.getRequiredTestMethod().getName()
            );
        }
    }
}
